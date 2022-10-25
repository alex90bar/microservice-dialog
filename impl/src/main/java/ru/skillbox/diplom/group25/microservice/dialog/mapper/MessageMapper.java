package ru.skillbox.diplom.group25.microservice.dialog.mapper;

import java.time.ZonedDateTime;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.skillbox.diplom.group25.microservice.dialog.dto.MessageDto;
import ru.skillbox.diplom.group25.microservice.dialog.model.MessageEntity;

/**
 * MessageMapper
 *
 * @author alex90bar
 */

@Mapper(componentModel = "spring")
public interface MessageMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "time", defaultExpression = "java(newTime())")
  @Mapping(target = "isDelete", defaultValue = "false")
  MessageEntity toEntity(MessageDto dto);


  MessageDto toDto(MessageEntity entity);


  default ZonedDateTime newTime() {
    return ZonedDateTime.now();
  }

}


