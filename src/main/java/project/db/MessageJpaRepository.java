package project.db;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageJpaRepository extends JpaRepository<MessageEntity, Long> {
}
