package com.example.shopping_list;

import com.example.shopping_list.config.ConfigurationBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Здесь будет проходить всё взаимодействие с ботом.
 */
@Component
public class ShoppingListBot extends TelegramLongPollingBot {

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
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());

            if (update.getMessage().getText().equals("/start")) {
                sendMessage.setText("Нажми на одну из кнопок ниже.");

                // Создание инлайн-клавиатуры
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();

                // Добавление кнопки "Старт" в инлайн-клавиатуру
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                inlineKeyboardButton.setText("Супермаркет");
                inlineKeyboardButton.setCallbackData("Супермаркет");
                rowInline.add(inlineKeyboardButton);

                rowsInline.add(rowInline);
                markupInline.setKeyboard(rowsInline);
                sendMessage.setReplyMarkup(markupInline);
            } else {
                sendMessage.setText("Круто!");
            }

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * Нажали на кнопку, обработка нажатия на кнопку. В инлайн-клавиатуре
         * callback методы, которые по-своему называются. Например "Супермаркет". Каждый нужно обработать отдельно.
         */
        if (update.hasCallbackQuery()) {
            if (Objects.equals(update.getCallbackQuery().getData(), "Супермаркет")) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
                sendMessage.setText("Вы перешли в раздел супермаркет");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
