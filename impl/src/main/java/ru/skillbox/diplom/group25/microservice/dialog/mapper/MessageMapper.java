package ru.skillbox.diplom.group25.microservice.dialog.mapper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.skillbox.diplom.group25.microservice.dialog.dialogs.DialogMessageDto;
import ru.skillbox.diplom.group25.microservice.dialog.dialogs.MessageDto;
import ru.skillbox.diplom.group25.microservice.dialog.dialogs.MessageShortDto;
import ru.skillbox.diplom.group25.microservice.dialog.model.DialogEntity;
import ru.skillbox.diplom.group25.microservice.dialog.model.MessageEntity;

/**
 * MessageMapper
 *
 * @author alex90bar
 */

@Mapper(componentModel = "spring")
public interface MessageMapper {

//  @Mapping(target = "id", ignore = true)
//  @Mapping(target = "time", defaultExpression = "java(newTime())")
//  @Mapping(target = "isDelete", defaultValue = "false")
//  MessageEntity toEntity(MessageDto dto);



  @Mapping(target = "id", ignore = true)
  @Mapping(target = "time", expression = "java(setTime(dto.getTime()))")
  @Mapping(target = "readStatus", constant = "SENT")
  @Mapping(target = "dialog", source = "dialog")
  @Mapping(target = "authorId", source = "dto.authorId")
  MessageEntity toEntity(DialogMessageDto dto, DialogEntity dialog);


  @Mapping(target = "status", source = "readStatus")
  @Mapping(target = "time", expression = "java(getTime(entity.getTime()))")
  MessageDto toDto(MessageEntity entity);

  MessageShortDto toShortDto(MessageEntity entity);

  default Long getTime(ZonedDateTime time){
    return time.toEpochSecond();
  }

  default ZonedDateTime setTime(Long time){
    return ZonedDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
  }

  default ZonedDateTime newTime() {
    return ZonedDateTime.now();
  }

}


