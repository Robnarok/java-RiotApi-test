package com.robnarok.yoneban.discordbot;

import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

public abstract class MessageListener {

    public Mono<Void> processCommand(Message eventMessage) {
        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> message.getContent().equalsIgnoreCase("oi"))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage("Vergesst aber nicht, Yone zu bannen!"))
                .then();
    }



}