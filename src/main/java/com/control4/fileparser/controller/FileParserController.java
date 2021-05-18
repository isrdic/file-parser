package com.control4.fileparser.controller;

import com.control4.fileparser.dto.Type;
import com.control4.fileparser.dto.WordResponse;
import com.control4.fileparser.service.FileParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FileParserController {

    private final FileParserService fileParserService;

    @Autowired
    public FileParserController(FileParserService fileParserService) {
        this.fileParserService = fileParserService;
    }

    @GetMapping("/print")
    public List<WordResponse> uploadFile(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("type") Type type) {
        return fileParserService.doTheJob(file, type);
    }

}
