package project.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.model.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
}
