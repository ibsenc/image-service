package com.ibsenc.imageservice.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.ibsenc.imageservice.exceptions.InvalidFileTypeException;
import com.ibsenc.imageservice.exceptions.InvalidInputException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class ImageService {

  @Value("${application.bucket.name}")
  private String bucketName;

  @Autowired
  private AmazonS3 s3Client;

  public String uploadImage(MultipartFile file) {
    if (!Arrays.asList("image/png", "image/jpeg").contains(file.getContentType())) {
      throw new InvalidFileTypeException();
    }

    final String updatedFileName = UUID.randomUUID() + craftFileExtension(file.getContentType());
    final File fileObj = convertMultiPartFileToFile(file);
    s3Client.putObject(new PutObjectRequest(bucketName, updatedFileName, fileObj));
    fileObj.delete();

    return updatedFileName;
  }

  private String craftFileExtension(String type) {
    return "." + type.split("/")[1];
  }

  public byte[] getImage(String fileName) throws IOException {
    S3Object s3Object = s3Client.getObject(bucketName, fileName);
    S3ObjectInputStream inputStream = s3Object.getObjectContent();
    return IOUtils.toByteArray(inputStream);
  }

  public void deleteImage(String fileName) {
    s3Client.deleteObject(bucketName, fileName);
  }

  private File convertMultiPartFileToFile(MultipartFile file) {
    File convertedFile = new File(file.getOriginalFilename());
    try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
      fos.write(file.getBytes());
    } catch (IOException e) {
      throw new InvalidInputException("Error converting MultipartFile to file.");
    }
    return convertedFile;
  }
}
