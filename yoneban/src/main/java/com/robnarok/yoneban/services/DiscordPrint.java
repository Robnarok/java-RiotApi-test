package com.robnarok.yoneban.services;

import com.robnarok.yoneban.model.PersistentMatch;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DiscordPrint {
    @Autowired
    JDA jda;

    @Value("${discordChannel}")
    String discordChannel;

    @Value("${championName}")
    String championName;

    public void printToDiscord(String input){
        TextChannel textChannelById = jda.getTextChannelById(discordChannel);
        textChannelById.sendMessage(input).queue();
    }

    public void printEmbeded(PersistentMatch persistentMatch){
        TextChannel textChannelById = jda.getTextChannelById(discordChannel);

        EmbedBuilder embeded = new EmbedBuilder();
        embeded.setTitle("Banreport");
        embeded.setDescription("fooo");

        if (persistentMatch.isGotBanned()){
            embeded.setColor(0x09e589);
            embeded.setDescription(championName + " wurde gebannt!");
        }
        else {
            embeded.setColor(0xe5092a);
            embeded.setDescription(championName + " wurde nicht gebannt!");
        }

        embeded.addField("Uhrzeit", , false);

        textChannelById.sendMessage(embeded.build()).queue();
        embeded.clear();
    }
}
