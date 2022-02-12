package com.example.esr.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonInclude(Include.NON_NULL)
@Builder
@Getter
public class Problem { // RFC 7807: https://datatracker.ietf.org/doc/html/rfc7807

    private Integer status;

    private String type;

    private String title;

    private String detail;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateTime;

    private String userMessage;

}
