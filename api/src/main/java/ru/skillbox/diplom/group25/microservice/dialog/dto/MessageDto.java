package ru.skillbox.diplom.group25.microservice.dialog.dto;

import java.time.ZonedDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * MessageDto
 *
 * @author alex90bar
 */

@Data
@RequiredArgsConstructor
public class MessageDto {

  private Long id;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime time;
  private Long authorId;
  private String messageText;
  private Boolean isDelete;

}


