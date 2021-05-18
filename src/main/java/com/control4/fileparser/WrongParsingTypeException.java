package com.control4.fileparser;

import lombok.AllArgsConstructor;
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
