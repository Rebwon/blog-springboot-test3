package ko.maeng.jpatestpart3.service;

import ko.maeng.jpatestpart3.domain.Account;
import ko.maeng.jpatestpart3.dto.AccountDto;
import ko.maeng.jpatestpart3.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account create(AccountDto accountDto){
        return accountRepository.save(accountDto.getEntity());
    }

    @Transactional
    public boolean findByAccount(String username, String password){
        Account account = accountRepository.findByUsername(username);
        if(account == null){
            return false;
        }
        if(!account.getPassword().equals(password)){
            return false;
        }
        return true;
    }
}
