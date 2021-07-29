package by.integrator.mind_meister_bot.bot.states;

import by.integrator.mind_meister_bot.bot.interfaces.BotState;
import by.integrator.mind_meister_bot.bot.service.QuestionService;
import by.integrator.mind_meister_bot.bot.service.SendBotMessageServiceImp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumSet;

@Getter
public enum ProfileBotState implements BotState<ProfileBotState, ProfileBotContext> {

    Start("start", true) {
        @Override
        public void enter(ProfileBotContext profileBotContext) {
            String chatId = profileBotContext.getUpdate().getMessage().getChatId().toString();
            sendBotMessageService.sendMessage(chatId, "Hello from Luda");
        }

        @Override
        public ProfileBotState nextState(ProfileBotContext profileBotContext) {
            return MindMap;
        }

        @Override
        public ProfileBotState rootState() {
            return Start;
        }
    },

    MindMap("mindMap", true) {
        @Override
        public void enter(ProfileBotContext profileBotContext) {
            String chatId = profileBotContext.getUpdate().getMessage().getChatId().toString();
            Long questionId = profileBotContext.getProfile().getAmount() + 1;
            String textQuestion = questionService.findQuestionTextByQuestionId(questionId);
            sendBotMessageService.sendMessage(chatId, textQuestion);
        }

        @Override
        public ProfileBotState nextState(ProfileBotContext profileBotContext) {
            if (profileBotContext.getProfile().getAmount() < 6) {
                return MindMap;
            }
            return Finish;
        }

        @Override
        public ProfileBotState rootState() {
            return MindMap;
        }
    },

    Finish("finish", true) {
        @Override
        public void enter(ProfileBotContext profileBotContext) {
            String chatId = profileBotContext.getUpdate().getMessage().getChatId().toString();
            sendBotMessageService.sendMessage(chatId, "Finish!");
        }

        @Override
        public ProfileBotState nextState(ProfileBotContext profileBotContext) {
            return Start;
        }

        @Override
        public ProfileBotState rootState() {
            return Finish;
        }
    };


    private final Boolean isInputNeeded;

    private final String botStateName;

    ProfileBotState(String botStateName, Boolean isInputNeeded) {
        this.botStateName = botStateName;
        this.isInputNeeded = isInputNeeded;
    }


    @Component
    @RequiredArgsConstructor
    public static class ProfileBotStateServiceInjector {

        private final SendBotMessageServiceImp sendBotMessageServiceImp;
        private final QuestionService questionService;


        @PostConstruct
        public void postConstruct() {
            for (ProfileBotState bt : EnumSet.allOf(ProfileBotState.class)) {
                bt.setSendBotMessageService(sendBotMessageServiceImp);
                bt.setQuestionService(questionService);

            }
        }
    }

    private void setSendBotMessageService(SendBotMessageServiceImp sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    private void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }


    SendBotMessageServiceImp sendBotMessageService;

    QuestionService questionService;

    public static ProfileBotState getInitialState() {
        return Start;
    }

    public Boolean getIsInputNeeded() {
        return isInputNeeded;
    }

    public void handleInput(ProfileBotContext botTeacherContext) {
    }

  /*  public void handleCallbackQuery(ProfileBotContext botTeacherContext) {
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
    }*/

    public abstract void enter(ProfileBotContext botTeacherContext);


    public abstract ProfileBotState nextState(ProfileBotContext botTeacherContext);

    public abstract ProfileBotState rootState();
}
