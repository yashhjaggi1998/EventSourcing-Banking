package dev.codescreen.codescreen_jl7syjim;

import dev.codescreen.codescreen_jl7syjim.model.Account;
import dev.codescreen.codescreen_jl7syjim.model.TransactionEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@SpringBootApplication
public class CodeScreenJl7syjimApplication {

    public static HashMap<String, Account>  _userAccountsDB = new HashMap<>();

    public static List<TransactionEvent> _transactionHistory = new ArrayList<>(); // Will be based on message_id
    
    public static void main(String[] args) {

        _userAccountsDB.put("user1", new Account("user1", new BigDecimal(0)));
        _userAccountsDB.put("user2", new Account("user2", new BigDecimal(0)));

        SpringApplication.run(CodeScreenJl7syjimApplication.class, args);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

}
