package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import project.model.FileDB;
import project.model.FileService;
import project.utils.ResponseFile;
import project.utils.ResponseNotification;
import project.utils.XmlParser;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

//    Upload do katalogu
	@PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {

      String fileName = file.getOriginalFilename();
      String notification ="";

      try {
          File tmp = new File("tmp/");
          if (!tmp.exists()){
              tmp.mkdirs();
          }
          file.transferTo(new File(tmp.getAbsolutePath(), fileName));

          XmlParser xml = new XmlParser();
          xml.parse(fileName);

          notification = fileName + " uploaded successfully";
          return ResponseEntity.status(HttpStatus.OK).body(new ResponseNotification(notification));
      } catch (Exception e) {
          notification = "Fail to upload file!" ;
          return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseNotification(notification));
      }
  }

//      Upload do db
    @PostMapping("/db.upload")
    public ResponseEntity<ResponseNotification> uploadFileToDB(@RequestParam("file") MultipartFile file) {
        String notification ="";

        try {
            fileService.storage(file);
            notification = file.getOriginalFilename() + " uploaded successfully";

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseNotification(notification));

        }catch (Exception e){
            notification = "Fail to upload file!" ;
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseNotification(notification));
        }

    }
// Pobranie plików z db
    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = fileService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();
            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

//    Pobranie pliku z db
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = fileService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}
