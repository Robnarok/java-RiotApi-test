package com.robnarok.yoneban.model;


import com.robnarok.yoneban.dto.SummonerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
public class Summoner {
    public String accountID;
    @Id
    public String puuid;
    public String name;
    public String championName;
    public String championId;

    public Summoner() {
    }

    public Summoner(SummonerDTO summonerDTO, String championId, String championName) {
        this.accountID = summonerDTO.accountID;
        this.name = summonerDTO.name;
        this.puuid = summonerDTO.puuid;
        this.championId = championId;
        this.championName = championName;
    }


}
