package ru.skillbox.diplom.group25.microservice.dialog.repository;

import java.util.List;
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

  Page<DialogEntity> findAllByAuthorIdOrRecipientIdOrderByLastMessageDesc(Long authorId, Long recipientId, Pageable pageable);

  DialogEntity findByAuthorIdAndRecipientId(Long authorId, Long recipientId);

  List<DialogEntity> findAllByAuthorIdOrRecipientId(Long authorId, Long recipientId);

}
