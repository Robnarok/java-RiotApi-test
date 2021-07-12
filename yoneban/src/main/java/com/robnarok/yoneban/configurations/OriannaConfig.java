package com.robnarok.yoneban.configurations;


import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.match.Match;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.searchable.SearchableList;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class OriannaConfig {

    public static void main(String[] args) {
        Orianna.setDefaultRegion(Region.EUROPE_WEST);
        MatchHistory matchhistory = Orianna.summonerNamed("DreiAugenFlappe").get().matchHistory().withEndIndex(6).get();

        for (Match match : matchhistory) {

            SearchableList<Champion> bans = match.getRedTeam().getBans();
            List<Champion> champions = new ArrayList<>();

            for (Champion ban : bans) {
                if (ban.exists())
                    champions.add(ban);
            }
            bans = match.getBlueTeam().getBans();
            for (Champion ban : bans) {
                if (ban.exists())
                    champions.add(ban);
            }

            boolean isBanned = false;

            for (Champion champion : champions) {
                if (champion.getName().equals("Yone")) {
                    isBanned = true;
                }
            }

            String matchID = "" + match.getId();
            if (isBanned) {
                System.out.println(matchID + ": Yone wurde gebannt");
                continue;
            } else {
                System.out.println(matchID + ": Du bist eine Schande.");
                continue;
            }
        }


    }

}
