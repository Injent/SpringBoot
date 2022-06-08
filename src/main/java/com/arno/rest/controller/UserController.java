package com.arno.rest.controller;

import com.arno.domain.Token;
import com.arno.domain.User;
import com.arno.rest.dto.ResponseDto;
import com.arno.rest.dto.TokenDto;
import com.arno.rest.dto.UserDto;
import com.arno.service.TokenService;
import com.arno.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    @PostMapping("/user/auth")
    public ResponseDto auth(@RequestBody UserDto userDto) {
        ResponseDto response = new ResponseDto();

        if (userDto.getLogin() == null && userDto.getPassword() == null) {
            response.setCode(0);
            response.setMessage("Отправленные данные не верны");
            return response;
        }

        User user = userService.getByLoginAndPassword(userDto.getLogin(), userDto.getPassword());
        if (user == null) {
            response.setCode(99);
            response.setMessage("Пользователь не найден");
            return response;
        }

        long currentDate = Instant.now().getEpochSecond();
        long tokenExpirationDate = Long.parseLong(user.getToken().getExpiration());
        long tokenExpirationNextDate = currentDate + 300000L;

        UserDto userDtoNew = UserDto.toDto(user);
        if (currentDate > tokenExpirationDate) {
            Token token = user.getToken();
            token.setExpiration(String.valueOf(tokenExpirationNextDate));
            tokenService.updateToken(token);

            userDtoNew.setTokenDto(TokenDto.toDto(token));

            response.setMessage("Токен обновлен | ");
        }
        response.setMessage(response.getMessage() + "Авторизация успешна");
        response.setUser(userDtoNew);
        response.setCode(100);

        return response;
    }
}
