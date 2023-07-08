package com.example.shopping_list;

import com.example.shopping_list.config.ConfigurationBot;
import com.example.shopping_list.entity.SupermarketItem;
import com.example.shopping_list.service.InlineKeyboardService;
import com.example.shopping_list.service.SupermarketService;
import com.example.shopping_list.service.WorkWithMessage;
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

/**
 * Здесь будет проходить всё взаимодействие с ботом.
 */
@Component
public class ShoppingListBot extends TelegramLongPollingBot {

    private final ConfigurationBot config;
    private String previousChoice = null;

    private final WorkWithMessage workWithMessage;
    private final SupermarketService supermarketService;
    private final InlineKeyboardService inlineKeyboardService;

    @Autowired
    public ShoppingListBot(ConfigurationBot config, WorkWithMessage workWithMessage, SupermarketService supermarketService,
                           InlineKeyboardService inlineKeyboardService) {
        this.config = config;
        this.workWithMessage = workWithMessage;
        this.supermarketService = supermarketService;
        this.inlineKeyboardService = inlineKeyboardService;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    //#TODO: Подумать над обработкой исключений, что лучше сделать, нужен фидбэк пользователю.
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();

            if (update.getMessage().getText().equals("/start")) {
                SendMessage messageToUser = workWithMessage.createMessageForSend("Выбери действие", chatId);

                // Создание инлайн-клавиатуры
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();

                rowInline.add(inlineKeyboardService.createInlineKeyboardButton("Добавить покупку", "Добавить покупку"));
                rowInline.add(inlineKeyboardService.createInlineKeyboardButton("Посмотреть список покупок", "Посмотреть список покупок"));

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

            if (this.previousChoice != null ) {
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
        }

        if (update.hasCallbackQuery()) {
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            switch (update.getCallbackQuery().getData()) {
                case ("Добавить покупку") -> {
                    SendMessage messageToUser = workWithMessage.createMessageForSend("Выбери тип магазина", chatId);

                    // Создание инлайн-клавиатуры
                    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                    List<InlineKeyboardButton> rowInline = new ArrayList<>();

                    rowInline.add(inlineKeyboardService.createInlineKeyboardButton("Супермаркет", "Супермаркет"));

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

                case ("Супермаркет") -> {
                    SendMessage messageToUser = workWithMessage.createMessageForSend("Введите, что нужно купить", chatId);
                    this.previousChoice = update.getCallbackQuery().getData();
                    try {
                        execute(messageToUser);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }

                case ("Посмотреть список покупок") -> {
                    SendMessage messageToUser = workWithMessage.createMessageForSend("Для какого магазина вы хотите посмотреть?", chatId);

                    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                    List<InlineKeyboardButton> rowInline = new ArrayList<>();
                    rowInline.add(inlineKeyboardService.createInlineKeyboardButton("Посмотреть для супермаркета", "Посмотреть для супермаркета"));
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

                case "Посмотреть для супермаркета" -> {
                    SendMessage messageToUser = workWithMessage.createMessageForSend("Хотите ли посмотреть для себя или весь список?", chatId);

                    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                    List<InlineKeyboardButton> rowInline = new ArrayList<>();
                    rowInline.add(inlineKeyboardService.createInlineKeyboardButton("Для себя", "Для себя"));
                    rowInline.add(inlineKeyboardService.createInlineKeyboardButton("Весь список", "Весь список"));

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

                case "Весь список", "Для себя" -> {
                    List<SupermarketItem> supermarketItemList = update.getCallbackQuery().getData().equals("Весь список") ?
                            supermarketService.getSupermarketItemList() : supermarketService.getSupermarketItemListByUsername(update.getCallbackQuery().getFrom().getUserName());

                    StringBuilder responseText = new StringBuilder();
                    for (SupermarketItem item : supermarketItemList) {
                        responseText.append(item.getBuy()).append("\n");
                    }
                    SendMessage messageWithList = workWithMessage.createMessageForSend(responseText.toString(), chatId);

                    try {
                        execute(messageWithList); // Отправляем сообщение
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
