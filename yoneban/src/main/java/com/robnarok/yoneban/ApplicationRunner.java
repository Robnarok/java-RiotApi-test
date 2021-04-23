package com.robnarok.yoneban;

import com.robnarok.yoneban.dto.MatchhistoryDTO;
import com.robnarok.yoneban.dto.SummonerDTO;
import com.robnarok.yoneban.services.ApiFetcher;
import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import discord4j.core.object.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    ApiFetcher apiFetcher;

    @Autowired
    GatewayDiscordClient client;

    @Override
    public void run(String... args) throws Exception {
        SummonerDTO dreiAugenFlappe = apiFetcher.requestSummonerDTO("DreiAugenFlappe");
        MatchhistoryDTO matchhistoryDTO = apiFetcher.requestLastFiveMatches(dreiAugenFlappe.puuid);
        apiFetcher.requestBansFromMatch(matchhistoryDTO.getMatches().get(0));
    }
}
