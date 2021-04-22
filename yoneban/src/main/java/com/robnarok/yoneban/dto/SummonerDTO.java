package com.robnarok.yoneban.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SummonerDTO {

    public final String id;
    public final String accountID;
    public final String puuid;
    public final String name;
    public final String profileIconId;
    public final String revisionDate;
    public final String summonerLevel;

    public SummonerDTO(
        @JsonProperty("id") String id,
        @JsonProperty("accountId") String accountID,
        @JsonProperty("puuid") String puuid,
        @JsonProperty("name") String name,
        @JsonProperty("profileIconId") String profileIconId,
        @JsonProperty("revisionDate")String revisionDate,
        @JsonProperty("summonerLevel")String summonerLevel) {
        this.id = id;
        this.accountID = accountID;
        this.puuid = puuid;
        this.name = name;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.summonerLevel = summonerLevel;
    }
}
