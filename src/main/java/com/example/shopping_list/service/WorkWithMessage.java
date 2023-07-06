package com.example.shopping_list.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface WorkWithMessage {

    SendMessage createMessageForSend(String text, Long chatId);
}
