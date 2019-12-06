package ko.maeng.jpatestpart3.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
@Table(name = "Account")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private LocalDateTime signInDate;

    @Builder
    public Account(String username, String password, LocalDateTime signInDate){
        this.username = username;
        this.password = password;
        this.signInDate = signInDate;
    }

    public boolean matchPassword(String loginPassword) {
        if(loginPassword == null){
            return false;
        }
        return loginPassword.equals(password);
    }
}
