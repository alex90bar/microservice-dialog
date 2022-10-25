package ru.skillbox.diplom.group25.microservice.dialog.dto;

import java.time.ZonedDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * DialogDto
 *
 * @author alex90bar
 */

@Data
@RequiredArgsConstructor
public class DialogDto {

  private Long id;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime time;
  private MessageDto lastMessage;

}


