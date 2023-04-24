package com.example.demo.user.controller;

import com.example.demo.user.model.Account;
import com.example.demo.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Void> addUser(@RequestBody Account account) {
        return userService.addUser(account);
    }

    @GetMapping(value = "/all")
    public List<Account> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/login")
    public Optional<Account> findByEmailPass(@RequestParam("email") String email, @RequestParam("password") String password) {
        return userService.findByEmailPass(email, password);
    }

    @GetMapping(value = "/search/id/{id}")
    public Optional<Account> findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping(value = "/chargeWallet")
    public ResponseEntity<Void> chargeWallet(@RequestParam("userId") Long userId, @RequestParam("amount") double amount) {
        return userService.chargeWallet(userId, amount);
    }
}