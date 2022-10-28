package ru.skillbox.diplom.group25.microservice.dialog.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.diplom.group25.microservice.dialog.dialogs.response.GetDialogsRs;
import ru.skillbox.diplom.group25.microservice.dialog.dialogs.response.GetMessagesRs;
import ru.skillbox.diplom.group25.microservice.dialog.dialogs.response.UnreadCountRs;
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
  public ResponseEntity<GetMessagesRs> getAllMessages(Long interlocutorId, Integer offset, Integer itemPerPage) {
    return ResponseEntity.ok(dialogService.getAllMessages(interlocutorId, offset, itemPerPage));
  }

  @Override
  public ResponseEntity<GetDialogsRs> getAllDialogs(Integer offset, Integer itemPerPage) {
    return ResponseEntity.ok(dialogService.getAllDialogs(offset, itemPerPage));
  }
}


