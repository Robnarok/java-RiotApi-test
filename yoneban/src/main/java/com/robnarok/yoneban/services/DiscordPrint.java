package com.robnarok.yoneban.services;

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

    public void printToDiscord(String input){
        TextChannel textChannelById = jda.getTextChannelById(discordChannel);
        textChannelById.sendMessage(input).queue();
    }

}
