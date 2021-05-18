package com.control4.fileparser.service;

import com.control4.fileparser.dto.Type;
import com.control4.fileparser.dto.WordResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileParserService {

    List<WordResponse> doTheJob(MultipartFile file, Type type);

}
