package ru.skillbox.diplom.group25.microservice.dialog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillbox.diplom.group25.microservice.dialog.model.DialogEntity;

/**
 * DialogRepository
 *
 * @author alex90bar
 */

@Repository
public interface DialogRepository extends JpaRepository<DialogEntity, Long> {

  Page<DialogEntity> findAllByAuthorIdOrReceiverIdOrderByTimeDesc(Long authorId, Long receiverId, Pageable page);

}
