package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.AuthorityRepository;
import ru.job4j.accidents.repository.UserReposCustom;
import ru.job4j.accidents.repository.UserRepository;

@Controller
@AllArgsConstructor
public class RegControl {

    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    private final UserReposCustom userReposCustom;

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        try {
            isUserExists(user.getUsername());
        } catch (DataIntegrityViolationException exception) {
            return "redirect:/reg?error=DataIntegrityViolationException";
        }

        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage(@RequestParam(value = "error", required = false) String error, Model model) {
        String notificationMessage = null;
        if (error != null) {
            notificationMessage = switch (error) {
                case ("DataIntegrityViolationException") -> "Пользователь с таким логином уже существует!";
                default -> null;
            };
        }
        model.addAttribute("notificationMessage", notificationMessage);
        return "reg";
    }

    public void isUserExists(String name) throws DataIntegrityViolationException {
        if (userReposCustom.existByName(name) > 0) {
            throw new DataIntegrityViolationException("Username already exists");
        }
    }
}