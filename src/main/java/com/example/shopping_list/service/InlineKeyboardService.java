package com.example.shopping_list.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public interface InlineKeyboardService {

    InlineKeyboardButton createInlineKeyboardButton(String text, String callbackData);
}
