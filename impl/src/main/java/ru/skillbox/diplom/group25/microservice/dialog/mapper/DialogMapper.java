package ru.skillbox.diplom.group25.microservice.dialog.mapper;

import java.time.ZonedDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skillbox.diplom.group25.microservice.dialog.dto.DialogDto;
import ru.skillbox.diplom.group25.microservice.dialog.dto.MessageDto;
import ru.skillbox.diplom.group25.microservice.dialog.model.DialogEntity;
import ru.skillbox.diplom.group25.microservice.dialog.model.MessageEntity;

/**
 * DialogMapper
 *
 * @author alex90bar
 */

@Mapper(componentModel = "spring")
public interface DialogMapper {


  @Mapping(target = "id", ignore = true)
  @Mapping(target = "time", defaultExpression = "java(newTime())")
  DialogEntity toEntity(DialogDto dto);


  @Mapping(target = "id", source = "entity.id")
  @Mapping(target = "time", source = "entity.time")
  @Mapping(target = "lastMessage", source = "messageDto")
  DialogDto toDto(DialogEntity entity, MessageDto messageDto);


  default ZonedDateTime newTime() {
    return ZonedDateTime.now();
  }

}


