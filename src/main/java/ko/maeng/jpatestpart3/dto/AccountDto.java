package ko.maeng.jpatestpart3.dto;

import ko.maeng.jpatestpart3.domain.Account;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NonNull
    private LocalDateTime signInDate;

    public Account getEntity(){
        return Account.builder().username(this.username)
                .password(this.password).signInDate(this.signInDate)
                .build();
    }
}
