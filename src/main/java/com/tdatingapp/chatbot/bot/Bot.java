package com.tdatingapp.chatbot.bot;

import com.github.kshashov.telegram.api.MessageType;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.tdatingapp.chatbot.config.BotConfig;
import com.tdatingapp.chatbot.util.TelegramStringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@BotController
@RequiredArgsConstructor
public class Bot implements TelegramMvcController {
    private final BotConfig botConfig;

    @BotRequest(value = "/start", type = { MessageType.CALLBACK_QUERY, MessageType.MESSAGE })
    public BaseRequest<?, ?> start(User user, Chat chat) {
        log.info("User {} starts this ChatBot!", user.id());

        var text = botConfig.getWelcomeMessage().replaceAll("<username>", parseUsername(user));

        var message = new SendMessage(chat.id(), text);
        message.parseMode(ParseMode.HTML);

        return message;
    }

    @Override
    public String getToken() {
        return botConfig.getToken();
    }

    private static String parseUsername(User user) {
        String result = "";

        if (!TelegramStringUtils.isEmpty(user.firstName())) {
            result += " " + user.firstName();
        }

        if (!TelegramStringUtils.isEmpty(user.lastName())) {
            result += " " + user.lastName();
        }

        return result;
    }
}
