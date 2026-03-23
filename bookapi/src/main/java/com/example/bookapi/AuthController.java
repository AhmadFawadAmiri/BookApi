package com.example.bookapi;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsServiceImpl, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.jwtUtil = jwtUtil;
    }
    //registering
    @GetMapping("/test")
    public String test(){
        return "Ok";
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
    //login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        try{
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(user.getUsername());
            if(!passwordEncoder.matches(user.getPassword(), userDetails.getPassword())){
                return ResponseEntity.status(401).body("Invalid username or password");
            }

            String token = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));

        }catch (Exception e){
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
    // Aux login
    public static class JwtResponse{
        private String token;

        public JwtResponse(String token) {
            this.token = token;
        }
        public String getToken() {
            return token;
        }
        public void setToken(String token) {
            this.token = token;
        }
    }

}
