package com.example.shopping_list;

import com.example.shopping_list.config.ConfigurationBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class ShoppingListBot extends TelegramLongPollingBot{

    private final ConfigurationBot config;

    @Autowired
    public ShoppingListBot(ConfigurationBot config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Иди нахуй");
            sendMessage.setChatId(update.getMessage().getChatId());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Иди нахуй");
        sendMessage.setChatId(update.getMessage().getChatId());
    }
}
