package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String uploadFile(MultipartFile file);
}
