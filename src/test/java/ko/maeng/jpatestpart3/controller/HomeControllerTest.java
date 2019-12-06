package ko.maeng.jpatestpart3.controller;

import ko.maeng.jpatestpart3.domain.Account;
import ko.maeng.jpatestpart3.dto.AccountDto;
import ko.maeng.jpatestpart3.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountService;

    @DisplayName("Get요청 테스트")
    @Test
    void hello() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(containsString("Hello Spring!")));
    }

    @DisplayName("Account 생성")
    @Test
    void create() throws Exception {
        AccountDto accountDto = AccountDto.builder()
                .username("rebwon").password("1234")
                .signInDate(LocalDateTime.now()).build();
        Account account = accountService.create(accountDto);

        assertThat(accountDto.getUsername()).isEqualTo(account.getUsername());
        assertThat(accountDto.getPassword()).isEqualTo(account.getPassword());
        assertThat(accountDto.getSignInDate()).isEqualTo(account.getSignInDate());
    }

    @DisplayName("로그인 테스트")
    @Test
    void login() throws Exception {
        String username = "rebwon";
        String password = "1234";

        boolean byAccount = accountService.findByAccount(username, password);
        assertThat(byAccount).isTrue();
    }
}