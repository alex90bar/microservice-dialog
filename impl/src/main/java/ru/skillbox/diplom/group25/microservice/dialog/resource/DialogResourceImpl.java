package ru.skillbox.diplom.group25.microservice.dialog.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.diplom.group25.microservice.dialog.dto.DialogDto;
import ru.skillbox.diplom.group25.microservice.dialog.dto.MessageDto;
import ru.skillbox.diplom.group25.microservice.dialog.service.DialogService;

/**
 * DialogResourceImpl
 *
 * @author alex90bar
 */

@RestController
@RequiredArgsConstructor
public class DialogResourceImpl implements DialogResource {

  private final DialogService dialogService;


  @Override
  public ResponseEntity<Page<MessageDto>> getAllMessages(Long id, Pageable pageable) {
    return ResponseEntity.ok(dialogService.getAllMessages(id, pageable));
  }

  @Override
  public ResponseEntity<Page<DialogDto>> getAllDialogs(Pageable pageable) {
    return ResponseEntity.ok(dialogService.getAllDialogs(pageable));
  }
}


