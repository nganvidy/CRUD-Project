package com.example.demo.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String uplaodFile(MultipartFile file);
    String deleteByFileName(String filename);
    String deleteAllFiles();
    //load resource

    Resource loadFileResource(String filename) throws Exception;
}
