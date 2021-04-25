package com.robnarok.yoneban.services;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DiscordListener extends ListenerAdapter {

    String prefix;

    String championName;

    public DiscordListener(String prefix, String championName) {
        this.prefix = prefix;
        this.championName = championName;
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent guildMessageReceivedEvent){
        String[] args = guildMessageReceivedEvent.getMessage().getContentRaw().split(" ");
        if(args[0].equalsIgnoreCase(prefix)){
            guildMessageReceivedEvent.getChannel().sendTyping().queue();
            guildMessageReceivedEvent.getChannel().sendMessage("Vergesst bitte nicht " + championName + " zu bannen!").queue();
        }



    }
}
