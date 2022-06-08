package com.arno.rest.controller;

import com.arno.domain.User;
import com.arno.rest.dto.CallDto;
import com.arno.rest.dto.ResponseDto;
import com.arno.rest.dto.TokenDto;
import com.arno.service.CallService;
import com.arno.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor

public class CallController {

    private final CallService callService;
    private final TokenService tokenService;

    @PostMapping("/calls")
    public ResponseDto getCallsForUser(@RequestBody TokenDto tokenDto){
        ResponseDto response = new ResponseDto();
        Integer userId = tokenService.getUserIdByToken(tokenDto.getValue());

        if (userId != null) {
            response.setCode(100);
        } else {
            response.setMessage("Пользователь не найден");
            response.setCode(0);
            return response;
        }

        long currentDate = Instant.now().getEpochSecond();
        //long tokenExpirationDate = tokenDto.getExpiration();

        List<CallDto> calls = callService.getForUser(userId).stream().map(CallDto::toDto).collect(Collectors.toList());
        if (calls == null || calls.isEmpty()) {
            response.setCode(1);
            response.setMessage("Таблица звонков пуста");
            response.setCalls(Collections.emptyList());
            return response;
        }

        response.setCalls(calls);
        response.setMessage("Список получен");

        return response;
    }
}
