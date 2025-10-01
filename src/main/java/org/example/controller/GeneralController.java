package org.example.controller;

import org.example.AuthRequest;
import org.example.AuthResponse;
import org.example.entity.User;
import org.example.jwt.JwtUtil;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
@RequestMapping("/app")
public class GeneralController {
    @Autowired
    private final UserService userService;
    private final JwtUtil jwtUtil;



    public GeneralController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest req) {
        if (req.getUsername() == null || req.getPassword() == null) return ResponseEntity.badRequest().build();
        userService.register(req.getUsername().trim(), req.getPassword(), req.getUsername());
        return ResponseEntity.ok().build();
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        var userOpt = userService.findByUsername(req.getUsername());
        if (userOpt.isEmpty()) return ResponseEntity.status(401).build();

        var user = userOpt.get();
        if (!userService.checkPassword(req.getPassword(), user.getPasswordHash())) return ResponseEntity.status(401).build();

        String token = jwtUtil.generateToken(user.getUsername());

        return ResponseEntity.ok(new AuthResponse(HtmlUtils.htmlEscape(token)));
    }
}
