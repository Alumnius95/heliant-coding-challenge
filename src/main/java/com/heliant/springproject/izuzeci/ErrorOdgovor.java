package com.heliant.springproject.izuzeci;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorOdgovor {
    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;
}
