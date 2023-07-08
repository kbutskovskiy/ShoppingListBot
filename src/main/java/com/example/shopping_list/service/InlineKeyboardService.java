package com.example.shopping_list.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

/**
 * Сервис для работы с инлайн-клавиатурой
 */
public interface InlineKeyboardService {

    /**
     * Метод создает кнопку для инлайн-клавиатуры, чтобы пользователь
     * мог удобно тыкать по кнопкам
     *
     * @param text -- текст для кнопки
     * @param callbackData -- информация о том, какую кнопку нажали.
     * @return созданную кнопку с заданным текстом
     */
    InlineKeyboardButton createInlineKeyboardButton(String text, String callbackData);
}
