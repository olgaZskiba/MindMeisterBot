package by.integrator.mind_meister_bot.bot.service;

import by.integrator.mind_meister_bot.bot.MindMeisterBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SendBotMessageServiceImp implements SendBotMessageService{

    private final MindMeisterBot mindMeisterBot;

    @Autowired
    public SendBotMessageServiceImp(MindMeisterBot mindMeisterBot) {
        this.mindMeisterBot = mindMeisterBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.enableHtml(true);
        sendMessage.setChatId(chatId);

        try {
            mindMeisterBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
