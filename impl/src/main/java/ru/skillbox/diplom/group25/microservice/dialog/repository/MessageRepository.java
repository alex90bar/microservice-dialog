package ru.skillbox.diplom.group25.microservice.dialog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.skillbox.diplom.group25.microservice.dialog.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * MessageRepository
 *
 * @author alex90bar
 */

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

  Page<MessageEntity> findMessageEntitiesByDialog_IdOrderByTimeDesc(Long dialog_Id, Pageable pageable);

}
