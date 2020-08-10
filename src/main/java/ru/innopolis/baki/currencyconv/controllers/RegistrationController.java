package ru.innopolis.baki.currencyconv.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.baki.currencyconv.models.RegistrationForm;
import ru.innopolis.baki.currencyconv.models.User;
import ru.innopolis.baki.currencyconv.repositories.RoleRepository;
import ru.innopolis.baki.currencyconv.repositories.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepo;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    public RegistrationController(
            UserRepository userRepo, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    public String registerForm() {
        return "registration";
    }
    @PostMapping
    public String processRegistration(RegistrationForm form) {
        User user = form.toUser(passwordEncoder);
        user.setRoles(roleRepository.findAllByRole("user"));
        userRepo.save(user);
        return "redirect:/login";
    }


}
