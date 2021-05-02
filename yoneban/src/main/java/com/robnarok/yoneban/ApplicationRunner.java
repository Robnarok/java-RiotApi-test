package com.robnarok.yoneban;

import com.robnarok.yoneban.dto.MatchhistoryDTO;
import com.robnarok.yoneban.dto.SummonerDTO;
import com.robnarok.yoneban.model.PersistentMatch;
import com.robnarok.yoneban.model.Summoner;
import com.robnarok.yoneban.repository.PersistentMatchRepository;
import com.robnarok.yoneban.repository.SummonerRepository;
import com.robnarok.yoneban.services.ApiFetcher;
import com.robnarok.yoneban.services.BanService;
import com.robnarok.yoneban.wrapper.Matchdata;
import com.robnarok.yoneban.wrapper.MatchdataList;
import net.dv8tion.jda.api.JDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    ApiFetcher apiFetcher;

    @Autowired
    JDA jda;

    @Autowired
    BanService banService;

    @Autowired
    PersistentMatchRepository persistenMatchRepository;

    @Autowired
    SummonerRepository summonerRepository;

//    @Value("${summonerName}")
//    String summonerName;
//
//    @Value("${championId}")
//    String championId;

    @Override

    public void run(String... args){

    }

    @Scheduled(fixedRate = 20 * 60 * 1000)
    public void scheduledCheck() {

        List<Summoner> summonerList = summonerRepository.findAll();

        for (Summoner summoner : summonerList) {
            List<PersistentMatch> persistentMatchList = persistenMatchRepository.findAllByChampionID(summoner.championId);
            MatchhistoryDTO matchhistoryDTO = apiFetcher.requestLastFiveMatches(summoner.puuid);

            matchhistoryDTO.setMatches(banService.filterMatchDTOWithPersistenMatch(persistentMatchList, matchhistoryDTO));

            if (matchhistoryDTO.getMatches().size() == 0) {
                continue;
            }

            Matchdata matchdata;
            MatchdataList matchdataList = new MatchdataList();

            for (String match : matchhistoryDTO.getMatches()) {
                try {
                    matchdata = apiFetcher.requestBansFromMatch(match);
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    continue;
                }
                matchdataList.addMatchdata(matchdata);
            }


            // Removes ARAM and Feature Gamemode Games, sadly after the API Query
            matchdataList = banService.removeInvalid(matchdataList);

            List<PersistentMatch> newPersistenMatches = new ArrayList<PersistentMatch>();

            // Counter could get improved
            int counter = persistenMatchRepository.countByChampionIDAndGotBannedIsTrue(summoner.championId) + 1;

            Collections.reverse(matchdataList.getMatchdata());
            for (Matchdata matchdata1 : matchdataList.getMatchdata()) {

                newPersistenMatches.add(new PersistentMatch(
                        matchdata1,
                        summoner.championId,
                        summoner.championName,
                        counter
                ));
                if (matchdata1.didChampGetBanned(summoner.championId))
                    counter++;
            }
            persistenMatchRepository.saveAll(newPersistenMatches);

            banService.printNewBanEvents(newPersistenMatches);


        }

    }
}
