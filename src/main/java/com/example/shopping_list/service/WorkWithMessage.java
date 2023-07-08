package com.example.shopping_list.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Сервис для работы с сообщениями (SendMessage)
 */
public interface WorkWithMessage {

    /**
     * Метод создает сообщение с заданным текстом
     * и заданным chatId
     *
     * @param text текст, который нужен для сообщения
     * @param chatId айди чата
     * @return созданное сообщение для отправки пользователю
     */
    SendMessage createMessageForSend(String text, Long chatId);
}
