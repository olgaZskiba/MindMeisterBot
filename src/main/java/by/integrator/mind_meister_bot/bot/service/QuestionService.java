package by.integrator.mind_meister_bot.bot.service;

import by.integrator.mind_meister_bot.bot.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public String findQuestionTextByQuestionId(Long id){
        String textQuestion = questionRepository.findByQuestionId(id).getTextQuestion();
        return textQuestion;
    }

    }
