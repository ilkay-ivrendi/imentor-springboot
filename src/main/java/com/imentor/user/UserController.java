package com.imentor.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @GetMapping()
    public String getUser() {
        return "User endpoint reached";
    }

    @GetMapping("/all")
    public List<String> getUsers() {
        return List.of("User1", "User2");
    }
}
