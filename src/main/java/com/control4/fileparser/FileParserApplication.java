package com.control4.fileparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class FileParserApplication { //extends SpringBootServletInitializer

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FileParserApplication.class, args);
        FileBean fileBean = context.getBean(FileBean.class);
        try {
            fileBean.printTop50ArrayList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
