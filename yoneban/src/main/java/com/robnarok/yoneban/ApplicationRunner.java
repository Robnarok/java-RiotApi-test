package com.robnarok.yoneban;

import com.robnarok.yoneban.dto.MatchhistoryDTO;
import com.robnarok.yoneban.dto.SummonerDTO;
import com.robnarok.yoneban.model.PersistentMatch;
import com.robnarok.yoneban.repository.PersistentMatchRepository;
import com.robnarok.yoneban.services.ApiFetcher;
import com.robnarok.yoneban.services.BanService;
import com.robnarok.yoneban.wrapper.Matchdata;
import com.robnarok.yoneban.wrapper.MatchdataList;
import net.dv8tion.jda.api.JDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


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

    @Value("${summonerName}")
    String summonerName;

    @Value("${championId}")
    String championId;

    @Override
    public void run(String... args) throws Exception {

        SummonerDTO summoner = apiFetcher.requestSummonerDTO(summonerName);
        MatchhistoryDTO matchhistoryDTO = apiFetcher.requestLastFiveMatches(summoner.puuid);
        List<PersistentMatch> persistentMatchList = persistenMatchRepository.findAll();

        matchhistoryDTO.setMatches(banService.filterMatchDTOWithPersistenMatch(persistentMatchList ,matchhistoryDTO));

        if (matchhistoryDTO.getMatches().size() == 0){
            return;
        }


        Matchdata matchdata;
        MatchdataList matchdataList= new MatchdataList();



        for (String match : matchhistoryDTO.getMatches()) {
            matchdata = apiFetcher.requestBansFromMatch(match);
            matchdataList.addMatchdata(matchdata);
        }

        // Removes ARAM and Feature Gamemode Games, sadly after the API Query
        matchdataList = banService.removeInvalid(matchdataList);


        matchdataList = banService.filterWithPersistenMatch(persistentMatchList, matchdataList);
        //At this pont the matchdataList should only contain unchecked data

        // turn Matchdata to PersistenMatch

        List<PersistentMatch> newPersistenMatches = new ArrayList<PersistentMatch>();

        // Counter could get improved
        int counter = newPersistenMatches.size();
        for (Matchdata matchdata1: matchdataList.getMatchdata()){
            counter++;
            newPersistenMatches.add(new PersistentMatch(
                    matchdata1,
                    championId, counter));

        }
        persistenMatchRepository.saveAll(newPersistenMatches);
        // Print new Bans

        banService.printNewBanEvents(newPersistenMatches);

    }
}
