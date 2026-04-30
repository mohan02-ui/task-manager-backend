package com.taskmanager.backend.Controller;


import com.taskmanager.backend.Repository.UserRepository;
import com.taskmanager.backend.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User dbUser = userRepository.findByEmail(user.getEmail());

        if (dbUser == null) {
            return ResponseEntity.status(401).body("Invalid email");
        }

        return ResponseEntity.ok(dbUser);
    }
}