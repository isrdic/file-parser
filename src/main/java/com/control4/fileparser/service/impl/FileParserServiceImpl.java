package com.control4.fileparser.service.impl;

import com.control4.fileparser.dto.Type;
import com.control4.fileparser.dto.WordResponse;
import com.control4.fileparser.service.FileParserService;
import com.control4.fileparser.util.FileParserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileParserServiceImpl implements FileParserService {

    Logger LOGGER = LoggerFactory.getLogger(FileParserServiceImpl.class);

    private final FileParserUtil fileParserUtil;

    @Autowired
    public FileParserServiceImpl(FileParserUtil fileParserUtil) {
        this.fileParserUtil = fileParserUtil;
    }

    @Override
    public List<WordResponse> doTheJob(MultipartFile file, Type type) {
        List<WordResponse> response = new ArrayList<>();
        try {
            response = fileParserUtil.engine(file != null ? new String(file.getBytes()) : null, type);
        } catch (IOException e) {
            LOGGER.error("Error with parsing file" + Arrays.toString(e.getStackTrace()));
        }
        return response;
    }
}
