package by.integrator.mind_meister_bot.bot.handler;

import by.integrator.mind_meister_bot.bot.bean.Profile;

import by.integrator.mind_meister_bot.bot.repository.ProfileRepository;
import by.integrator.mind_meister_bot.bot.service.ProfileService;
import by.integrator.mind_meister_bot.bot.service.SendBotMessageServiceImp;
import by.integrator.mind_meister_bot.bot.states.ProfileBotContext;
import by.integrator.mind_meister_bot.bot.states.ProfileBotState;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
public class ProfileCommandHandler {



    private final ProfileService profileService;

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

        ProfileBotContext botContext = null;
        ProfileBotState botState = null;

        //User user = userService.findByChatId(chatId);
        Profile profile = profileService.findByChatId(chatId);

        if (profile == null) {

            profile = profileService.createProfile(update);

            botContext = ProfileBotContext.of(this, profile, update);
            botState = profile.getProfileBotState();

            try {
                botState.enter(botContext);

                while (!botState.getIsInputNeeded()) {
                    if (botState.nextState() != null) {
                        botState = botState.nextState();

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
            }
        }
        else {
            botContext = ProfileBotContext.of(this, profile, update);
            botState = profile.getProfileBotState();

            //logger.info("Update received from profile: " + chatId + ", in state: " + botState + ", with text: " + update.getMessage().getText());

            try {
                botState.handleInput(botContext);

                do {
                    if (botState.nextState() != null) {
                        botState = botState.nextState();

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
        }

        user.getProfile().setProfileBotState(botState);
        userService.save(user);
    
    }
}
