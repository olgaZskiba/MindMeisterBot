package by.integrator.mind_meister_bot.web.controller;

import by.integrator.mind_meister_bot.bot.bean.Profile;
import by.integrator.mind_meister_bot.bot.bean.Role;
import by.integrator.mind_meister_bot.bot.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.Column;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addProfile(Profile profile, Map<String, Object> model){
    Profile profileFromDb = profileRepository.findByUserName(profile.getUserName());

        if (profileFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }
        profile.setActive(true);
        profile.setRoles(Collections.singleton(Role.USER));
        profileRepository.save(profile);

        return "redirect:/login";
    }
}
