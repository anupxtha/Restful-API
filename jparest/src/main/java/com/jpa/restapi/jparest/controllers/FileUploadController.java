package com.jpa.restapi.jparest.controllers;

import com.jpa.restapi.jparest.helper.FileUploadHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {

  @Autowired
  private FileUploadHelper fileUploadHelper;

  @PostMapping(path = "/upload-file")
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

    try {
      // System.out.println(file.getOriginalFilename());
      // System.out.println(file.getSize());
      // System.out.println(file.getContentType());
      // System.out.println(file.getName());

      if (file.isEmpty()) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request Must Contain File");
      }

      if (!file.getContentType().equals("image/jpeg")) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request File Must be JPG Type");
      }

      // file upload code
      boolean f = this.fileUploadHelper.uploadFile(file);

      if (f = true) {
        // Making Image URI / URL : localhost:8080/image/image-name.jpg
        return ResponseEntity.status(HttpStatus.OK).body(ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/image/").path(file.getOriginalFilename()).toUriString());
      } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong try again");
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return ResponseEntity.ok("working");
  }
}
