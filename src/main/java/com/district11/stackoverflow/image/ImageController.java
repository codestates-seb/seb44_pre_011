package com.district11.stackoverflow.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@RestController
public class ImageController {

    @Autowired
    private S3Client s3Client;

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            // 파일이 없는 경우 처리
            return "파일이 없습니다.";
        }

        String bucketName = "district11";
        String objectKey = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // S3에 업로드
            s3Client.putObject(PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectKey)
                    .build(), RequestBody.fromInputStream(file.getInputStream(), file.getSize()));


            // 업로드 완료 후 추가 처리

            return "파일 업로드가 완료되었습니다.";
        } catch (IOException e) {
            // 업로드 중 에러 발생 시 처리
            return "파일 업로드 중 에러가 발생했습니다.";
        }
    }
}