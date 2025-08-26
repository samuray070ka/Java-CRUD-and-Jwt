package com.startup.FirstStartUp.controller;
import com.startup.FirstStartUp.model.User;
import com.startup.FirstStartUp.repository.UserRepository;
import com.startup.FirstStartUp.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> body) {
        Map<String, Object> response = new HashMap<>();
        try {
            String ism = Optional.ofNullable(body.get("ism")).orElse("").toLowerCase();
            String familya = Optional.ofNullable(body.get("familya")).orElse("").toLowerCase();
            String parol = Optional.ofNullable(body.get("parol")).orElse("");

            if(ism.isEmpty() || familya.isEmpty() || parol.isEmpty()){
                response.put("success", false);
                response.put("message", "Iltimos, barcha maydonlarni to‘ldiring!");
                return ResponseEntity.badRequest().body(response);
            }

            Optional<User> user = userRepository.findUser(ism, familya, parol);

            if (user.isPresent()) {
                String token = jwtUtil.generateToken(user.get().getIsm() + " " + user.get().getFamilya());
                response.put("success", true);
                response.put("token", token);
                response.put("user", user.get());
            } else {
                response.put("success", false);
                response.put("message", "Login yoki parol noto‘g‘ri");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Server xatoligi: " + e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        try {
            user.setIsm(user.getIsm().toLowerCase());
            user.setFamilya(user.getFamilya().toLowerCase());
            User savedUser = userRepository.save(user);

            response.put("success", true);
            response.put("user", savedUser);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Server xatoligi: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/protected")
    public ResponseEntity<?> protectedPage(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            if (jwtUtil.validateToken(token)) {
                return ResponseEntity.ok("Siz himoyalangan sahifaga kirdingiz ✅");
            } else {
                return ResponseEntity.status(401).body("❌ Token noto‘g‘ri yoki muddati tugagan!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body("❌ Token xatolik: " + e.getMessage());
        }
    }    // ALL USERS
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}