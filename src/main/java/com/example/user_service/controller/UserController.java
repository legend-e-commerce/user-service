package com.example.user_service.controller;

import com.example.user_service.dto.UserDto;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.service.UserService;
import com.example.user_service.vo.Greeting;
import com.example.user_service.vo.RequestUser;
import com.example.user_service.vo.ResponseUser;
import io.micrometer.core.annotation.Timed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final Greeting greeting;
    private final UserService userService;
    private final Environment env;

    @GetMapping("/health_check")
    @Timed(value = "users.status", longTask = true)
    public String status() {
        return String.format(
                "건강해요 PORT: %s, server port: %s, token secret: %s, token expiration time: %s",
                env.getProperty("local.server.port"), env.getProperty("server.port"),
                env.getProperty("token.secret"),env.getProperty("token.expiration_time")
        );
    }

    @GetMapping("/welcome")
    @Timed(value = "users.welcome", longTask = true)
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(
            @RequestBody @Valid RequestUser requestUser
    ) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(requestUser, UserDto.class);
        UserDto responseDto = userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(responseDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<UserEntity> userEntities = userService.getUserByAll();

        ArrayList<ResponseUser> result = new ArrayList<>();

        userEntities.forEach(user -> result.add(new ModelMapper().map(user, ResponseUser.class)));

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {
        UserDto userDto = userService.getUserByUserId(userId);

        ResponseUser result = new ModelMapper().map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
