package com.jpa.restapi.jparest.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

  // public final String UPLOAD_DIR =
  // "C:\\Users\\Anup-Xtha\\Documents\\workspace-spring-tool-suite-4-4.11.1.RELEASE\\jparest\\src\\main\\resources\\static\\image";

  // ClassPathResource throw exception when contructor run as calling this class
  // so to handle we did this
  public FileUploadHelper() throws IOException {
  }

  // Dynamic Image Path using ClassPathResource
  public final String UPLOAD_DIR = new ClassPathResource("static/image").getFile().getAbsolutePath();

  public boolean uploadFile(MultipartFile file) {
    boolean f = false;

    try {

      // // Old Way

      // // read file
      // InputStream is = file.getInputStream();

      // byte data[] = new byte[is.available()];

      // is.read(data);

      // // write file in path
      // FileOutputStream fos = new FileOutputStream(UPLOAD_DIR + File.separator +
      // file.getOriginalFilename());

      // fos.write(data);

      // fos.flush();

      // fos.close();

      // // ==============================================

      // New Way (read to byte from inputstream, where to write the file, CopyOption)
      Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename()),
          StandardCopyOption.REPLACE_EXISTING);

      f = true;

    } catch (Exception e) {
      e.printStackTrace();
    }

    return f;
  }

}
