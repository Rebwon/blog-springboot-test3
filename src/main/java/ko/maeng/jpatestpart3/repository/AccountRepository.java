package ko.maeng.jpatestpart3.repository;

import ko.maeng.jpatestpart3.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // @Modifying DDL쿼리를 만들땐 필요.
    // @Transactional 트랜잭션이 발생될 메서드에 반드시 필요.
    @Transactional
    @Modifying
    @Query("delete from Account a where a.id in :ids")
    void deleteAllByIdInQuery(@Param("ids") List<Long> ids);
}
