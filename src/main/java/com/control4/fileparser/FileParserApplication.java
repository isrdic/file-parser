package com.control4.fileparser;

import com.control4.fileparser.dto.Type;
import com.control4.fileparser.util.FileParserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class FileParserApplication {

    static Logger LOGGER = LoggerFactory.getLogger(FileParserApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FileParserApplication.class, args);
        FileParserUtil fileParserUtil = context.getBean(FileParserUtil.class);
        try {
            fileParserUtil.engine(null, Type.TreeSet);
        } catch (IOException e) {
            LOGGER.error("Error with parsing file" + Arrays.toString(e.getStackTrace()));
        }
    }

}
