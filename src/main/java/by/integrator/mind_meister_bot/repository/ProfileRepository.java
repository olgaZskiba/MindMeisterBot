package by.integrator.mind_meister_bot.repository;

import by.integrator.mind_meister_bot.bean.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
