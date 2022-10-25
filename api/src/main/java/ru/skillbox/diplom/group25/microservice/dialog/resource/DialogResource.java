package ru.skillbox.diplom.group25.microservice.dialog.resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.diplom.group25.microservice.dialog.dto.DialogDto;
import ru.skillbox.diplom.group25.microservice.dialog.dto.MessageDto;

/**
 * DialogResource
 *
 * @author alex90bar
 */

@RequestMapping("api/v1/dialogs")
public interface DialogResource {

  @GetMapping("/{id}/messages")
  ResponseEntity<Page<MessageDto>> getAllMessages(@PathVariable("id") Long id, Pageable pageable);

  @GetMapping
  ResponseEntity<Page<DialogDto>> getAllDialogs(Pageable pageable);



}
