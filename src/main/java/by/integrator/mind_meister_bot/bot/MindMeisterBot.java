package by.integrator.mind_meister_bot.bot;

import by.integrator.mind_meister_bot.bot.service.MessageService;
import by.integrator.mind_meister_bot.bot.service.SendBotMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@PropertySource("application.properties")
public class MindMeisterBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Autowired
    SendBotMessageService sendBotMessageService;

    @Autowired
    MessageService messageService;

//    @Autowired
//    public MindMeisterBot(SendBotMessageService sendBotMessageService) {
//        this.sendBotMessageService = sendBotMessageService;
//    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = messageService.getMessage(update);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
