package me.douglashdezt.simanmarvelpediaws.controllers;

import jakarta.validation.Valid;
import me.douglashdezt.simanmarvelpediaws.dtos.GeneralResponse;
import me.douglashdezt.simanmarvelpediaws.dtos.LoginDTO;
import me.douglashdezt.simanmarvelpediaws.dtos.SaveUserDTO;
import me.douglashdezt.simanmarvelpediaws.models.User;
import me.douglashdezt.simanmarvelpediaws.services.UserService;
import me.douglashdezt.simanmarvelpediaws.utils.JWTTools;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JWTTools jwtTools;

    public AuthController(UserService userService, JWTTools jwtTools) {
        this.userService = userService;
        this.jwtTools = jwtTools;
    }

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse> login(@RequestBody @Valid LoginDTO info) {
        User user = userService.findUserByEmail(info.getEmail());

        if (user == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found");
        }

        if(!userService.verifyPassword(user, info.getPassword())) {
            return GeneralResponse.getResponse(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }

        String token = jwtTools.generateToken(user);
        return GeneralResponse.getResponse("Login successful", token);
    }

    @PostMapping("/register")
    public ResponseEntity<GeneralResponse> register(@RequestBody @Valid  SaveUserDTO info){
        User user = userService.findUserByEmail(info.getEmail());

        if (user != null) {
            return GeneralResponse.getResponse(HttpStatus.CONFLICT, "User already exists");
        }

        userService.createUser(info);
        return GeneralResponse.getResponse(HttpStatus.CREATED, "User registered successfully");
    }

    @GetMapping("/whoami")
    public ResponseEntity<GeneralResponse> whoami(){
        User user = userService.findAuthenticated();
        return GeneralResponse.getResponse("This is who am i", user);
    }
}
