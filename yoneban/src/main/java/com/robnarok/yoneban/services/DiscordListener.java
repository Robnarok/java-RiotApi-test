package com.robnarok.yoneban.services;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Service;

@Service
public class DiscordListener extends ListenerAdapter {

    String prefix;

    public DiscordListener(String prefix) {
        this.prefix = prefix;
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent guildMessageReceivedEvent){
        String[] args = guildMessageReceivedEvent.getMessage().getContentRaw().split(" ");
        if(args[0].equalsIgnoreCase(prefix + "oi")){
            guildMessageReceivedEvent.getChannel().sendTyping().queue();
            guildMessageReceivedEvent.getChannel().sendMessage("Hello there").queue();
        }



    }
}
