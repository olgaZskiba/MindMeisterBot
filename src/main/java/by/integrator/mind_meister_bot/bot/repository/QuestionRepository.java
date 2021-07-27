package by.integrator.mind_meister_bot.bot.repository;

import by.integrator.mind_meister_bot.bot.bean.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
