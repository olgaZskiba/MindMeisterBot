package by.integrator.mind_meister_bot.bot.states;


import by.integrator.mind_meister_bot.bot.interfaces.BotStateInjector;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ProfileBotStateInjector implements BotStateInjector<ProfileBotState, ProfileBotContext> {
    /*@Autowired private SendBotMessageServiceImp sendBotMessageServiceImp;*/
    /*@Autowired private StudentService studentService;
    @Autowired private HomeworkService homeworkService;
    @Autowired private CommentService commentService;
    @Autowired private CommunityService communityService;
    @Autowired private SubjectService subjectService;
    @Autowired private MessageService messageService;
    @Autowired private TeacherService teacherService;
    @Autowired private LessonService lessonService;
    @Autowired private LessonPartService lessonPartService;
    @Autowired private MaterialService materialService;
    @Autowired private UserService userService;
    @Autowired private MaterialPartService materialPartService;
    @Autowired private NotificationMessageSender notificationMessageSender;*/

    @Override
    @PostConstruct
    public void inject() {
        /*ProfileBotState.setTeacherMessageService(teacherMessageService);
        ProfileBotState.setStudentService(studentService);
        ProfileBotState.setHomeworkService(homeworkService);
        ProfileBotState.setCommentService(commentService);
        ProfileBotState.setCommunityService(communityService);
        ProfileBotState.setSubjectService(subjectService);*/
       /* ProfileBotState.setMessageService(sendBotMessageServiceImp);*/
        /*ProfileBotState.setTeacherService(teacherService);
        ProfileBotState.setLessonService(lessonService);
        ProfileBotState.setLessonPartService(lessonPartService);
        ProfileBotState.setMaterialService(materialService);
        ProfileBotState.setUserService(userService);
        ProfileBotState.setMaterialPartService(materialPartService);
        ProfileBotState.setNotificationMessageSender(notificationMessageSender);*/
    }
}
