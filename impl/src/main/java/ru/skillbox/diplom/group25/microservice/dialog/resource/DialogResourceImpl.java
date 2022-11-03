package ru.skillbox.diplom.group25.microservice.dialog.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.diplom.group25.microservice.dialog.dto.response.GetDialogsRs;
import ru.skillbox.diplom.group25.microservice.dialog.dto.response.GetMessagesRs;
import ru.skillbox.diplom.group25.microservice.dialog.dto.response.SetStatusMessageReadRs;
import ru.skillbox.diplom.group25.microservice.dialog.dto.response.UnreadCountRs;
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
  public ResponseEntity<UnreadCountRs> getUnreadMessageCount() {
    return ResponseEntity.ok(dialogService.getUnreadMessageCount());
  }

  @Override
  public ResponseEntity<GetMessagesRs> getAllMessages(Long companionId, Integer offset, Integer itemPerPage) {
    return ResponseEntity.ok(dialogService.getAllMessages(companionId, offset, itemPerPage));
  }

  @Override
  public ResponseEntity<GetDialogsRs> getAllDialogs(Integer offset, Integer itemPerPage) {
    return ResponseEntity.ok(dialogService.getAllDialogs(offset, itemPerPage));
  }

  @Override
  public ResponseEntity<SetStatusMessageReadRs> setStatusMessageRead(Long companionId) {
    return ResponseEntity.ok(dialogService.setStatusMessageRead(companionId));
  }
}


