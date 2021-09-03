package com.ibsenc.imageservice.controller;

import com.ibsenc.imageservice.data.UploadResponse;
import com.ibsenc.imageservice.service.ImageService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageServiceController {

  @Autowired
  private ImageService service;

  @PostMapping("/upload")
  public ResponseEntity<UploadResponse> uploadImage(@RequestParam(value = "file") MultipartFile file) {
    final String imageId = service.uploadImage(file);
    return new ResponseEntity<>(new UploadResponse(imageId), HttpStatus.OK);
  }

  @SneakyThrows
  @GetMapping("/file/{fileName}")
  public ResponseEntity<ByteArrayResource> getImage(@PathVariable String fileName) {
    byte[] data = service.getImage(fileName);
    ByteArrayResource resource = new ByteArrayResource(data);
    return ResponseEntity
        .ok()
        .contentLength(data.length)
        .header("Content-type", "image/jpeg")
        .body(resource);
  }

  @DeleteMapping("/{fileName}")
  public ResponseEntity<String> deleteImage(@PathVariable String fileName) {
    service.deleteImage(fileName);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
