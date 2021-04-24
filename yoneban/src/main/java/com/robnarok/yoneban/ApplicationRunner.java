package com.robnarok.yoneban;

import com.robnarok.yoneban.dto.MatchhistoryDTO;
import com.robnarok.yoneban.dto.SummonerDTO;
import com.robnarok.yoneban.model.PersistentMatch;
import com.robnarok.yoneban.services.ApiFetcher;
import com.robnarok.yoneban.services.BanService;
import com.robnarok.yoneban.wrapper.Matchdata;
import com.robnarok.yoneban.wrapper.MatchdataList;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
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

    @Value("${summonerName}")
    String summonerName;

    @Value("${championId}")
    String championId;

    @Override
    public void run(String... args) throws Exception {
        SummonerDTO summoner = apiFetcher.requestSummonerDTO(summonerName);
        MatchhistoryDTO matchhistoryDTO = apiFetcher.requestLastFiveMatches(summoner.puuid);

        //Somehow filter which Matches are already checked


        Matchdata matchdata;
        MatchdataList matchdataList= new MatchdataList();

        for (String match : matchhistoryDTO.getMatches()) {
            matchdata = apiFetcher.requestBansFromMatch(match);
            matchdataList.addMatchdata(matchdata);
        }


        matchdataList = banService.removeInvalid(matchdataList);


        //ToDo: PersistenData
        PersistentMatch persistentMatch = new PersistentMatch("EUW1_5229069076",true, 3);
        PersistentMatch persistentMatch2 = new PersistentMatch("EUW1_5229069035",true, 3);

        List<PersistentMatch> persistentMatchList = new ArrayList<>();
        persistentMatchList.add(persistentMatch);
        persistentMatchList.add(persistentMatch2);

        matchdataList = banService.filterWithPersistenMatch(persistentMatchList, matchdataList);
        //At this pont the matchdataList should only contain unchecked data

        // turn Matchdata to PersistenMatch

        List<PersistentMatch> newPersistenMatches = new ArrayList<PersistentMatch>();

        for (Matchdata matchdata1: matchdataList.getMatchdata()){
            newPersistenMatches.add(new PersistentMatch(
                    matchdata1,
                    championId,
                    54));
        }

        // Print new Bans

        banService.printNewBanEvents(newPersistenMatches);

    }
}
