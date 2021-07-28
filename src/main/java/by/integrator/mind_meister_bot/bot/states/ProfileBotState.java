package by.integrator.mind_meister_bot.bot.states;

import by.integrator.mind_meister_bot.bot.interfaces.BotState;

import by.integrator.mind_meister_bot.bot.service.SendBotMessageServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.*;

import java.util.List;

public enum ProfileBotState implements BotState<ProfileBotState, ProfileBotContext> {

    Start(false) {
        @Override
        public void enter(ProfileBotContext profileBotContext) {
            String chatId = profileBotContext.getUpdate().getMessage().getChatId().toString();
            sendBotMessageServiceImp.sendMessage(chatId, "Hello from Luda");
        }

        @Override
        public ProfileBotState nextState() {
            return EnterFirstname;
        }

        @Override
        public ProfileBotState rootState() {
            return Start;
        }
    };

    @Setter
    private static SendBotMessageServiceImp sendBotMessageServiceImp;

    private final Boolean isInputNeeded;

    ProfileBotState(Boolean isInputNeeded) {
        this.isInputNeeded = isInputNeeded;
    }

    public static ProfileBotState getInitialState() {
        return Start;
    }

    public Boolean getIsInputNeeded() {
        return isInputNeeded;
    }

    public void handleInput(ProfileBotContext botTeacherContext) {
    }

    public void handleCallbackQuery(ProfileBotContext botTeacherContext) {
    }

    public void handleContact(ProfileBotContext botTeacherContext) {
    }

    public void handlePhoto(ProfileBotContext botTeacherContext) {
    }

    public void handleVideo(ProfileBotContext profileBotContext) {
    }

    public void handleVideoNote(ProfileBotContext profileBotContext) {
    }

    public void handleVoice(ProfileBotContext profileBotContext) {
    }

    public void handleDocument(ProfileBotContext profileBotContext) {
    }

    public abstract void enter(ProfileBotContext botTeacherContext);

    public abstract ProfileBotState nextState();

    public abstract ProfileBotState rootState();
}
