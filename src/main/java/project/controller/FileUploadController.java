package project.controller;

import java.io.File;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;

@Controller
public class FileUploadController {

	@PostMapping("/upload")
  public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
      String fileName = file.getOriginalFilename();

      try {
          File dir = new File("tmp/");
          dir.mkdirs();
          File tmp = new File(dir, fileName);
          tmp.createNewFile();
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
      return ResponseEntity.ok("File uplaoded.");
  }
}
