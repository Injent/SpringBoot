package com.arno.rest.dto;

import com.arno.domain.Token;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TokenDto {

    private int id;

    private String value;

    @JsonProperty(value = "expirationDate")
    private String expiration;

    private int user;

    public static TokenDto toDto(Token token){
        return new TokenDto(
                token.getId(),
                token.getValue(),
                token.getExpiration(),
                token.getUser()
        );
    }

    public static Token toDomainObject(TokenDto tokenDto){
        return new Token(
                tokenDto.getId(),
                tokenDto.getValue(),
                tokenDto.getExpiration(),
                tokenDto.getUser()
        );
    }
}
