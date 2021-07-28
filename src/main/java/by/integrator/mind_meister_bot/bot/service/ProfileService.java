package by.integrator.mind_meister_bot.bot.service;

import by.integrator.mind_meister_bot.bot.bean.Profile;
import by.integrator.mind_meister_bot.bot.repository.ProfileRepository;
import by.integrator.mind_meister_bot.bot.states.ProfileBotState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Profile findByChatId(Long chatId){
        return profileRepository.findByChatId(chatId);
    }


    public Profile createProfile(Update update) {
        return Profile.builder()
                .chatId(update.getMessage().getChatId())
                .userName(update.getMessage().getContact().getFirstName())
                .profileBotState(ProfileBotState.getInitialState())
                .amount(0)
                .build();
    }
}
