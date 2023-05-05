package com.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class EcmDto {

    @NotEmpty(message = "ecmId 값이 빈값입니다.")
    private String ecmId;
}
