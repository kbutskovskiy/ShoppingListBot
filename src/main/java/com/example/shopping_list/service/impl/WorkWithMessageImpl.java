package com.example.shopping_list.service.impl;

import com.example.shopping_list.service.WorkWithMessage;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class WorkWithMessageImpl implements WorkWithMessage {

    @Override
    public SendMessage createMessageForSend(String text, Long chatId) {
        SendMessage messageToSend = new SendMessage();
        messageToSend.setText(text);
        messageToSend.setChatId(chatId);
        return messageToSend;
    }
}
