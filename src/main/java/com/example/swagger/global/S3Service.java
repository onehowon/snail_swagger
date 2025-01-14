package com.example.swagger.global;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {


    public String uploadFile(MultipartFile file) {
        try {
            File tempFile = convertMultipartFileToFile(file);

            String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            String s3Url = "https://s3.amazonaws.com/your-bucket/" + uniqueFileName;


            tempFile.delete();
            return s3Url;
        } catch (Exception e) {
            throw new RuntimeException("파일 업로드 실패: " + e.getMessage());
        }
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }
}