package com.robnarok.yoneban.services;

import com.robnarok.yoneban.dto.SummonerDTO;
import com.robnarok.yoneban.model.Summoner;
import com.robnarok.yoneban.repository.SummonerRepository;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Service;

@Service
public class DiscordListener extends ListenerAdapter {

    String prefix;

    String championName;

    ApiFetcher apiFetcher;

    SummonerRepository summonerRepository;

    public DiscordListener(String prefix, String championName, ApiFetcher apiFetcher, SummonerRepository summonerRepository) {
        this.prefix = prefix;
        this.championName = championName;
        this.apiFetcher = apiFetcher;
        this.summonerRepository = summonerRepository;
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent guildMessageReceivedEvent){
        String[] args = guildMessageReceivedEvent.getMessage().getContentRaw().split(" ");
        if(args[0].equalsIgnoreCase(prefix)){
            if (args.length >= 2 && args[1].equalsIgnoreCase("listen")){
                if(args.length != 5){
                    guildMessageReceivedEvent.getChannel().sendTyping().queue();
                    guildMessageReceivedEvent.getChannel().sendMessage("ERROR").queue();
                    return;
                }else{
                    newSummonerListener(args[2], args[3], args[4]);
                }
            }


            guildMessageReceivedEvent.getChannel().sendTyping().queue();
            guildMessageReceivedEvent.getChannel().sendMessage("Vergesst bitte nicht " + championName + " zu bannen!").queue();
        }
    }

    public void newSummonerListener(String summonerName, String championID, String champion){
        SummonerDTO summonerDTO = apiFetcher.requestSummonerDTO(summonerName);
        Summoner summoner = new Summoner(summonerDTO, championID, champion);
        summonerRepository.save(summoner);


    }

}
