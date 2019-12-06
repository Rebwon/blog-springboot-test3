package ko.maeng.jpatestpart3.controller;

import ko.maeng.jpatestpart3.dto.AccountDto;
import ko.maeng.jpatestpart3.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class HomeController {

    private final AccountService accountService;

    public HomeController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String hello(){
        return "Hello Spring!";
    }

    @PostMapping("/create")
    public void create(@Valid AccountDto accountDto){
        accountService.create(accountDto);
    }

    @PostMapping("/login")
    public void login(@Valid String username, @Valid String password) {
        accountService.findByAccount(username, password);
    }
}
