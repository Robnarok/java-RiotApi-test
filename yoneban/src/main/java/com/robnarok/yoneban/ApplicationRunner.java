package com.robnarok.yoneban;

import com.robnarok.yoneban.dto.MatchhistoryDTO;
import com.robnarok.yoneban.dto.SummonerDTO;
import com.robnarok.yoneban.services.ApiFetcher;
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

    @Override
    public void run(String... args) throws Exception {
        SummonerDTO dreiAugenFlappe = apiFetcher.requestSummonerDTO(summonerName);
        MatchhistoryDTO matchhistoryDTO = apiFetcher.requestLastFiveMatches(dreiAugenFlappe.puuid);
        apiFetcher.requestBansFromMatch(matchhistoryDTO.getMatches().get(1));

        TextChannel textChannelById = jda.getTextChannelById(discordChannel);
        textChannelById.sendMessage("hello There").queue();

        System.out.println("foo");
    }
}
