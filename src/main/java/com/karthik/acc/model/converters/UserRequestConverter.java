package com.karthik.acc.model.converters;

import com.karthik.acc.entity.UserRequest;
import com.karthik.acc.model.UserRequestDto;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class UserRequestConverter {

    public static UserRequest convertToEntity(UserRequestDto dto) {
        UserRequest userRequest = new UserRequest();
        BeanUtils.copyProperties(dto, userRequest);
        return userRequest;
    }

    public static UserRequestDto convertToDto(UserRequest entity) {
        UserRequestDto dto = new UserRequestDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static List<UserRequest> convertToEntityList(List<UserRequestDto> userRequestDtoList) {
        List<UserRequest> entityList = new ArrayList<>();
        userRequestDtoList.stream().forEach(dto -> {
            entityList.add(convertToEntity(dto));
        });
        return entityList;
    }
}

