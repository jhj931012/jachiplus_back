package com.example.jachiplus_back.controller;

import com.example.jachiplus_back.dto.*;
import com.example.jachiplus_back.entity.UserEntity;
import com.example.jachiplus_back.exception.DuplicatedEmailExeption;
import com.example.jachiplus_back.exception.NoRegisteredArgumentsException;
import com.example.jachiplus_back.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/jachi/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Validated @RequestBody UserSignUpRequestDTO userSignUpRequestDTO, BindingResult result) {
        if(result.hasErrors()) {
            log.warn(result.toString());
            return ResponseEntity
                    .badRequest()
                    .body(result.toString());
        }

        try {
            UserSignUpResponseDTO userSignUpResponseDTO = userService.create(userSignUpRequestDTO);
            return ResponseEntity
                    .badRequest()
                    .body(userSignUpResponseDTO);
        }catch (NoRegisteredArgumentsException e){
            log.warn("가입정보를 다시 확인하세요!");
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }catch (DuplicatedEmailExeption e){
            log.warn("중복되었습니다. 다른 이메일을 작성해주세요.");
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkEmail(String email) {
        if (email == null || email.trim().equals("")) {
            return ResponseEntity.badRequest().body("이메일을 전달해 주세요");
        }
        boolean flag = userService.isDuplicate(email);
        log.info("{} 중복 여부?? - {}", email, flag);
        return ResponseEntity.ok().body(flag);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(
            @Validated @RequestBody LoginRequestDTO requestDTO) {

        try {
            LoginResponseDTO userInfo = userService.getByCredentials(
                    requestDTO.getEmail(),
                    requestDTO.getPassword()
            );
            return ResponseEntity
                    .ok()
                    .body(userInfo);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(LoginResponseDTO.builder()
                            .message(e.getMessage())
                            .build()
                    );
        }
    }


}
