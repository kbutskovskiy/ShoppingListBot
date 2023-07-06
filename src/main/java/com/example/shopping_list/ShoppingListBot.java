package com.example.shopping_list;

import com.example.shopping_list.config.ConfigurationBot;
import com.example.shopping_list.service.SupermarketService;
import com.example.shopping_list.service.WorkWithMessage;
import lombok.RequiredArgsConstructor;
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
    private String previousChoice = null;

    private WorkWithMessage workWithMessage;
    private SupermarketService supermarketService;

    @Autowired
    public ShoppingListBot(ConfigurationBot config, WorkWithMessage workWithMessage, SupermarketService supermarketService) {
        this.config = config;
        this.workWithMessage = workWithMessage;
        this.supermarketService = supermarketService;
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
            Long chatId = update.getMessage().getChatId();

            if (update.getMessage().getText().equals("/start")) {
                SendMessage messageToUser = workWithMessage.createMessageForSend("Нажми на одну из кнопок ниже", chatId);
                messageToUser.setText("Нажми на одну из кнопок ниже.");

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
                messageToUser.setReplyMarkup(markupInline);
                try {
                    execute(messageToUser);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                previousChoice = null;
            }

            //#TODO: Пофиксить тут NPE вылезает, продумать.
            assert this.previousChoice != null;
            if (this.previousChoice.equals("Супермаркет")) {
                SendMessage successMessage = workWithMessage.createMessageForSend("Отлично! Вы добавили покупку", chatId);
                supermarketService.createBuyInSupermarket(update);

                try {
                    execute(successMessage);
                } catch (TelegramApiException exception) {
                    throw new RuntimeException(exception);
                }
            }

        }

        /**
         * Нажали на кнопку, обработка нажатия на кнопку. В инлайн-клавиатуре
         * callback методы, которые по-своему называются. Например "Супермаркет". Каждый нужно обработать отдельно.
         */
        if (update.hasCallbackQuery()) {
            if (Objects.equals(update.getCallbackQuery().getData(), "Супермаркет")) {
                Long chatId = update.getCallbackQuery().getMessage().getChatId();
                SendMessage messageToUser = workWithMessage.createMessageForSend("Введите, что нужно купить", chatId);
                this.previousChoice = update.getCallbackQuery().getData();
                try {
                    execute(messageToUser);
                } catch (TelegramApiException e) {
                    SendMessage errorMessage = workWithMessage.createMessageForSend("Произошла ошибка, попробуйте ещё раз", chatId);
                }
            }
        }
    }
}
