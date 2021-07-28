package by.integrator.mind_meister_bot.bot.bean;

import by.integrator.mind_meister_bot.bot.states.ProfileBotState;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_profile")
public class Profile implements Serializable {

    public Profile(Long chatId, String userName) {
        this.chatId = chatId;
        this.userName = userName;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "TeacherBotState", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ProfileBotState profileBotState;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "profile_role", joinColumns = @JoinColumn(name = "id_profile"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "username")
    private String userName;

    @Column(name = "amount")
    private Integer amount;
//
//    @Column(name = "percentage", columnDefinition = "0.0")
//    private Double percentage;
}
