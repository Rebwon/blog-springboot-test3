package ko.maeng.jpatestpart3.repository;

import ko.maeng.jpatestpart3.domain.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @DisplayName("저장한 객체 동일성 확인")
    @Test
    public void accountCheck(){
        Account account = Account.builder().username("rebwon").password("1234")
                .signInDate(LocalDateTime.now()).build();
        accountRepository.save(account);

        // 미리 저장한 account 객체가 영속 상태라면
        // 식별자 컬럼인 id 값 비교를 했을때 테스트가 성공해야한다.

        Account account2 = accountRepository.getOne(1l);
        Account account3 = accountRepository.getOne(1l);
        assertThat(account2).isEqualTo(account3);
    }

    @DisplayName("객체 저장 후 검색")
    @Test
    public void saveEntityAndSearch(){
        Account account1 = Account.builder().username("marin").password("4321")
                .signInDate(LocalDateTime.now()).build();
        accountRepository.save(account1);

        Account account2 = Account.builder().username("anna").password("5678")
                .signInDate(LocalDateTime.now()).build();
        accountRepository.save(account2);

        Account account3 = Account.builder().username("kitty").password("7890")
                .signInDate(LocalDateTime.now()).build();
        accountRepository.save(account3);

        List<Account> userList = accountRepository.findAll();
        assertThat(userList).hasSize(3);
        assertThat(userList).contains(account1, account2, account2);
    }

    @DisplayName("객체 저장 후 삭제")
    @Test
    public void saveEntityAndDelete(){
        Account account1 = Account.builder().username("marin").password("4321")
                .signInDate(LocalDateTime.now()).build();
        accountRepository.save(account1);

        Account account2 = Account.builder().username("anna").password("5678")
                .signInDate(LocalDateTime.now()).build();
        accountRepository.save(account2);

        // JPA의 delete메서드들은 삭제하기전, 정보를 조회하고 한 건씩 삭제해나가기 때문에
        // 지금은 2건의 정보를 삭제했지만, 서비스가 크다면 이건 삭제 성능에 문제가 생길 가능성이 있다.
        accountRepository.deleteAll();

        assertThat(accountRepository.findAll()).isEmpty();
    }

    @DisplayName("삭제 성능 최적화")
    @Test
    public void deleteOptimizer(){
        Account account1 = Account.builder().username("marin").password("4321")
                .signInDate(LocalDateTime.now()).build();
        accountRepository.save(account1);

        Account account2 = Account.builder().username("anna").password("5678")
                .signInDate(LocalDateTime.now()).build();
        accountRepository.save(account2);

        // 삭제할 때 JPA의 기본적인 조회 후 삭제가 아니라
        // @Query를 통한 쿼리 생성으로 바로 삭제함으로써 성능 최적화.
        accountRepository.deleteAllByIdInQuery(Arrays.asList(1L, 2L));

        assertThat(accountRepository.findAll()).isEmpty();
    }
}