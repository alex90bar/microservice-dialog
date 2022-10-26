package ru.skillbox.diplom.group25.microservice.dialog.dialogs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DialogMessage
 *
 * @author alex90bar
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DialogMessage {

  private String type;
  private Long accountId;
  private DialogMessageDto data;

}


