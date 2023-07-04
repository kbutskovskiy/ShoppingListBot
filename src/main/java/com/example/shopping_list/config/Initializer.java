package com.example.shopping_list.config;

import com.example.shopping_list.ShoppingListBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@Slf4j
public class Initializer {
    private final ShoppingListBot tgBot;

    @Autowired
    public Initializer(ShoppingListBot tgBot) {
        this.tgBot = tgBot;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(tgBot);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
