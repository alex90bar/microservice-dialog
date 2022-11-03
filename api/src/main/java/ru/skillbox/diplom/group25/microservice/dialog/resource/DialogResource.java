package ru.skillbox.diplom.group25.microservice.dialog.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skillbox.diplom.group25.microservice.dialog.dto.response.GetDialogsRs;
import ru.skillbox.diplom.group25.microservice.dialog.dto.response.GetMessagesRs;
import ru.skillbox.diplom.group25.microservice.dialog.dto.response.SetStatusMessageReadRs;
import ru.skillbox.diplom.group25.microservice.dialog.dto.response.UnreadCountRs;

/**
 * DialogResource
 *
 * @author alex90bar
 */

@RequestMapping("api/v1/dialogs")
public interface DialogResource {

  @GetMapping(value = "/unreaded")
  ResponseEntity<UnreadCountRs> getUnreadMessageCount();

  @GetMapping("/messages")
  ResponseEntity<GetMessagesRs> getAllMessages(@RequestParam Long companionId,
      @RequestParam(defaultValue = "0") Integer offset,
      @RequestParam(defaultValue = "20") Integer itemPerPage);

  @GetMapping
  ResponseEntity<GetDialogsRs> getAllDialogs(@RequestParam(defaultValue = "0") Integer offset,
      @RequestParam(defaultValue = "20") Integer itemPerPage);


  @PutMapping(value = "/{companionId}")
  ResponseEntity<SetStatusMessageReadRs> setStatusMessageRead(@PathVariable("companionId") Long companionId);


}
