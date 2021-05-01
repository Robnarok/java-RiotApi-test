package com.robnarok.yoneban.services;

import com.robnarok.yoneban.model.PersistentMatch;
import com.robnarok.yoneban.wrapper.Player;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class DiscordPrint {
    @Autowired
    JDA jda;

    @Value("${discordChannel}")
    String discordChannel;

    String championName;

    public void printToDiscord(String input){
        TextChannel textChannelById = jda.getTextChannelById(discordChannel);
        textChannelById.sendMessage(input).queue();
    }

    public void printEmbeded(PersistentMatch persistentMatch, List<Player> playersInGame){
        TextChannel textChannelById = jda.getTextChannelById(discordChannel);
        championName = persistentMatch.getChampionName();

        EmbedBuilder embeded = new EmbedBuilder();
        embeded.setTitle("Banreport");
        embeded.setDescription("fooo");

        if (persistentMatch.isGotBanned()){
            embeded.setColor(0x09e589);
            embeded.setDescription(championName + " wurde gebannt! Das war das " + persistentMatch.getCounter() + ". mal!");
        }
        else {
            embeded.setColor(0xe5092a);
            embeded.setDescription(championName + " wurde nicht gebannt!");
        }


        long timestamp = Long.parseLong(persistentMatch.getMatchdata().getDate());
        Date date = new Date(timestamp);
        LocalDateTime localDateTime = new java.sql.Timestamp(date.getTime()).toLocalDateTime();
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("HH:mm:ss - dd.LL.yyyy");

        embeded.addField("Uhrzeit", localDateTime.format(customFormatter), false);

        for (Player player : playersInGame) {
            embeded.addField("Mitspieler", player.getSummonerName(), true);
        }


        String matchId = persistentMatch.getMatchID();
        matchId = matchId.split("_")[1];

        embeded.addField("League of Graphs","https://www.leagueofgraphs.com/match/euw/" + matchId,false);


        textChannelById.sendMessage(embeded.build()).queue();
        embeded.clear();
    }
}
