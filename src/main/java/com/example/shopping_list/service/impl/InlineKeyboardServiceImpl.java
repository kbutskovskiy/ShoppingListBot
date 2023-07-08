package com.example.shopping_list.service.impl;

import com.example.shopping_list.service.InlineKeyboardService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Service
public class InlineKeyboardServiceImpl implements InlineKeyboardService {

    @Override
    public InlineKeyboardButton createInlineKeyboardButton(String text, String callbackData) {
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setCallbackData(callbackData);
        inlineKeyboardButton.setText(text);
        return inlineKeyboardButton;
    }
}
