package com.robnarok.yoneban;

import com.robnarok.yoneban.dto.MatchhistoryDTO;
import com.robnarok.yoneban.dto.SummonerDTO;
import com.robnarok.yoneban.services.ApiFetcher;
import com.robnarok.yoneban.wrapper.Matchdata;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    ApiFetcher apiFetcher;

    @Autowired
    JDA jda;

    @Value("${summonerName}")
    String summonerName;

    @Value("${discordChannel}")
    String discordChannel;

    @Value("${championId}")
    String championId;

    @Override
    public void run(String... args) throws Exception {
        SummonerDTO summoner = apiFetcher.requestSummonerDTO(summonerName);
        MatchhistoryDTO matchhistoryDTO = apiFetcher.requestLastFiveMatches(summoner.puuid);

        //Somehow filter which Matches are already checked


        Matchdata matchdata;
        for (String match : matchhistoryDTO.getMatches()) {
            matchdata = apiFetcher.requestBansFromMatch(match);
            if (matchdata.didChampGetBanned(championId)){
                System.out.println("Champion wurde gebannt!");
            }

        }

        TextChannel textChannelById = jda.getTextChannelById(discordChannel);
        textChannelById.sendMessage("hello There").queue();


    }
}
