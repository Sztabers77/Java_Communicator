package project.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import project.db.FileDBRepository;

import java.io.IOException;
import java.util.stream.Stream;

@Service
@CrossOrigin("http://localhost:8081")
public class FileService {

    @Autowired
    private FileDBRepository fileDBRepository;

//   storage(file) przechwytuje objekt MultipartFile do FileDB i zapisuje w bazie
    public FileDB storage(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
        return fileDBRepository.save((FileDB));
    }
//  zwraca Id objektu FileDB
    public FileDB getFile (String id) {
        return fileDBRepository.findById(id).get();

    }
//  zwraca wszystkie zapisane pliki
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
