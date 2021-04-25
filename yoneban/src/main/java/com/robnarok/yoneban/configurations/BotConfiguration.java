package com.robnarok.yoneban.configurations;


import com.robnarok.yoneban.services.DiscordListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;

@Configuration
public class BotConfiguration {

    @Value("${discordToken}")
    String token;

    @Value("${discordPrefix}")
    String prefix;

    @Value("${discordActivity}")
    String discordActivity;

    @Value("${championName}")
    String championName;

    @Bean
    public JDA createJDA() throws LoginException {
        JDA jda = JDABuilder.createDefault(token).build();
        jda.getPresence().setPresence(OnlineStatus.ONLINE, Activity.playing(discordActivity));
        jda.addEventListener(new DiscordListener(prefix, championName));
        return jda;
    }

    @Bean
    public String prefix(){
        return prefix;
    }




}
