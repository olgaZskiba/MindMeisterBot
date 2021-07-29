package by.integrator.mind_meister_bot.bot.service;

import by.integrator.mind_meister_bot.bot.bean.Answer;
import by.integrator.mind_meister_bot.bot.repository.AnswerRepository;
import by.integrator.mind_meister_bot.bot.states.ProfileBotContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void saveAnswer(ProfileBotContext botContext, Update update) {
        answerRepository.save(Answer.builder()
                .answerId(botContext.getProfile().getAmount())
                .textAnswer(update.getMessage().getText())
                .profile(botContext.getProfile())
                .build());
    }
}
