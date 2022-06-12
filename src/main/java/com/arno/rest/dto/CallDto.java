package com.arno.rest.dto;

import com.arno.domain.Call;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CallDto {

    private int id;

    private int userId;

    private long callTime;

    private long editCardDate;

    private String reason;

    private String bcc;

    private String firstName;

    private String middleName;

    private String lastName;

    private long bornDate;

    private boolean sex;

    private String residence;

    private String phoneNumber;

    private String orgName;

    private long snils;

    private long polis;

    private String passport;

    private int age;

    public static CallDto toDto (Call call){
        boolean sex = call.getSex() == 1;
        long bornDate = call.getBornDate().getTime();
        long callTime = call.getCallTime().getTime();
        long editCardDate = call.getCallTime().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(call.getBornDate());
        int age = Year.now().getValue() - calendar.get(Calendar.YEAR);
        return new CallDto(
                call.getId(),
                call.getUserId(),
                callTime,
                editCardDate,
                call.getReason(),
                call.getBcc(),
                call.getFirstName(),
                call.getMiddleName(),
                call.getLastName(),
                bornDate,
                sex,
                call.getResidence(),
                call.getPhoneNumber(),
                call.getOrgName(),
                call.getSnils(),
                call.getPolis(),
                call.getPassport(),
                age
        );
    }

    public static Call toDomainObject(CallDto callDto){
        byte sex = 0;
        if (callDto.isSex())
            sex = 1;
        return new Call(
                callDto.getId(),
                callDto.getUserId(),
                new Date(callDto.getCallTime()),
                new Date(callDto.getEditCardDate()),
                callDto.getReason(),
                callDto.getBcc(),
                callDto.getFirstName(),
                callDto.getMiddleName(),
                callDto.getLastName(),
                new Date(callDto.getBornDate()),
                sex,
                callDto.getResidence(),
                callDto.getPhoneNumber(),
                callDto.getOrgName(),
                callDto.getSnils(),
                callDto.getPolis(),
                callDto.getPassport()
        );
    }
}
