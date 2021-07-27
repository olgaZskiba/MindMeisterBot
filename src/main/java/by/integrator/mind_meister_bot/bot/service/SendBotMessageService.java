package by.integrator.mind_meister_bot.bot.service;

import org.springframework.stereotype.Service;

@Service
public interface SendBotMessageService {
    void sendMessage(String chatId, String message);
}
