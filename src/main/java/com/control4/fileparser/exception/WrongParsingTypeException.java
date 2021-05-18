package com.control4.fileparser.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WrongParsingTypeException extends RuntimeException {

    public WrongParsingTypeException(String message) {
        super(message);
    }
}
