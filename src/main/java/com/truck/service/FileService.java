package com.truck.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by geely
 */
public interface FileService {

    String upload(MultipartFile file, String path);

    String uploadCDN(MultipartFile file, String path) throws IOException;
}
