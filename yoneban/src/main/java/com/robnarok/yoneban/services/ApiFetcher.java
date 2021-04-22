package com.robnarok.yoneban.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.robnarok.yoneban.dto.BanDTO;
import com.robnarok.yoneban.dto.MatchDTO;
import com.robnarok.yoneban.dto.MatchhistoryDTO;
import com.robnarok.yoneban.dto.SummonerDTO;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiFetcher {

    @Value("${apiSecret}")
    private String secret;



    public SummonerDTO requestSummonerDTO(String summonerName){
        ObjectMapper mapper = new ObjectMapper();

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet("https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName +"?api_key="+ secret );

            SummonerDTO response = client.execute(request, httpResponse ->
                    mapper.readValue(httpResponse.getEntity().getContent(), SummonerDTO.class));
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MatchhistoryDTO requestLastFiveMatches(String puuid){
        ObjectMapper mapper = new ObjectMapper();

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            String uri = "https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids?start=0&count=5&api_key=" + secret;
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .uri(URI.create(uri))
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            List<String> strings = mapper.readValue(response.body(), new TypeReference<List<String>>() {});
            MatchhistoryDTO matchhistoryDTO= new MatchhistoryDTO(strings);

            return matchhistoryDTO;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void requestBansFromMatch(String matchid) throws IOException, InterruptedException {

        ObjectMapper mapper = new ObjectMapper();
        String uri = "https://europe.api.riotgames.com/lol/match/v5/matches/" + matchid + "?api_key=" + secret;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(uri))
                .build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());


        JsonObject rootJson = new Gson().fromJson(response.body(),JsonObject.class);
        MatchDTO matchDTO = new MatchDTO();

        JsonArray teams = rootJson.get("info").getAsJsonObject().get("teams").getAsJsonArray();
        JsonArray bans;

        BanDTO tmp = new BanDTO();

        for (JsonElement foo : teams){
            bans = foo.getAsJsonObject().get("bans").getAsJsonArray();
            for (JsonElement ban : bans){
                tmp.setChampionId(ban.getAsJsonObject().get("championId").getAsString());
                tmp.setPickTurn(ban.getAsJsonObject().get("pickTurn").getAsString());
                matchDTO.add(tmp);
                tmp = new BanDTO();
            }
        }
        System.out.println(matchDTO);



    }
}
