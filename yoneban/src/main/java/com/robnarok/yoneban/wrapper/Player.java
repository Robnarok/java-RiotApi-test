package com.robnarok.yoneban.wrapper;

import lombok.Data;

@Data
public class Player {
    String summonerName;
    int pentaKills;
    String championName;
    String teamPosition;
    String teamId;


    public Player(String summonerName, int pentaKills, String championName, String teamPosition, int teamId) {
        this.summonerName = summonerName;
        this.pentaKills = pentaKills;
        this.championName = championName;

        if(teamPosition.equals("UTILITY"))
            this.teamPosition = "SUPPORT";
        else this.teamPosition = teamPosition;

        if(teamId == 100)
            this.teamId = "Red Team";
        else this.teamId = "Blue Team";
    }

    @Override
    public String toString() {
        return  "summonerName= " + summonerName + '\n' +
                "pentaKills= " + pentaKills + '\n' +
                "championName= " + championName + '\n' +
                "teamPosition= " + teamPosition + '\n' +
                "teamId= " + teamId + '\n';
    }
}
