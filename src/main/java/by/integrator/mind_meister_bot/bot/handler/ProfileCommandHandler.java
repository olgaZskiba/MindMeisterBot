package by.integrator.mind_meister_bot.bot.handler;

import by.integrator.mind_meister_bot.bot.bean.Answer;
import by.integrator.mind_meister_bot.bot.bean.Profile;

import by.integrator.mind_meister_bot.bot.repository.ProfileRepository;
import by.integrator.mind_meister_bot.bot.service.AnswerService;
import by.integrator.mind_meister_bot.bot.service.ProfileService;
import by.integrator.mind_meister_bot.bot.service.SendBotMessageServiceImp;
import by.integrator.mind_meister_bot.bot.states.ProfileBotContext;
import by.integrator.mind_meister_bot.bot.states.ProfileBotState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static by.integrator.mind_meister_bot.bot.states.ProfileBotState.MindMap;

@Component
@RequiredArgsConstructor
public class ProfileCommandHandler {


    private final ProfileService profileService;
    private final AnswerService answerService;


    public void handle(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                processInputMessage(update);
            }
        } else if (update.hasCallbackQuery()) {
            processCallbackQuery(update);
        }
    }

    private void processCallbackQuery(Update update) {

    }

    private void processInputMessage(Update update) {
        final Long chatId = update.getMessage().getChatId();

        Profile profile = Optional.ofNullable(profileService.findByChatId(chatId)).orElse(profileService.createProfile(update));

        profile.setProfileBotState(profile.getProfileBotState());

        profileService.save(profile);

        ProfileBotContext botContext = ProfileBotContext.of(this, profile, update);
        ProfileBotState botState = profile.getProfileBotState();

        if (botState.rootState().getBotStateName().equals(MindMap.getBotStateName())) {
            answerService.saveAnswer(botContext, update);
            profile.setAmount(profile.getAmount() + 1L);
        }

        //logger.info("Update received from profile: " + chatId + ", in state: " + botState + ", with text: " + update.getMessage().getText());

        /*try {
            botState.enter(botContext);

            while (!botState.getIsInputNeeded()) {
                if (botState.nextState(botContext) != null) {
                    botState = botState.nextState(botContext);

                    try {
                        botState.enter(botContext);
                    } catch (Exception ex) {
                        botState = botState.rootState();
                        botState.enter(botContext);

                        ex.printStackTrace();
                    }
                }
            }
        }
        catch (Exception ex) {
            botState = botState.rootState();
            botState.enter(botContext);

            ex.printStackTrace();
        }*/
        try {
            botState.handleInput(botContext);

            do {
                if (botState.nextState(botContext) != null) {
                    botState = botState.nextState(botContext);

                    try {
                        botState.enter(botContext);
                    }
                    catch (Exception ex) {
                        botState = botState.rootState();
                        botState.enter(botContext);

                        ex.printStackTrace();
                    }
                }
                else {
                    break;
                }
            } while (!botState.getIsInputNeeded());
        }
        catch (Exception ex) {
            botState = botState.rootState();
            botState.enter(botContext);

            ex.printStackTrace();
        }


        profile.setProfileBotState(botState);
        profileService.save(profile);

    }
}
