package ru.skillbox.diplom.group25.microservice.dialog.mapper;

import java.time.ZonedDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skillbox.diplom.group25.microservice.account.model.AccountDto;
import ru.skillbox.diplom.group25.microservice.dialog.dialogs.DialogDto;
import ru.skillbox.diplom.group25.microservice.dialog.dialogs.MessageDto;
import ru.skillbox.diplom.group25.microservice.dialog.model.DialogEntity;

/**
 * DialogMapper
 *
 * @author alex90bar
 */

@Mapper(componentModel = "spring")
public interface DialogMapper {


//  @Mapping(target = "id", ignore = true)
//  @Mapping(target = "time", defaultExpression = "java(newTime())")
//  DialogEntity toEntity(DialogDto dto);


  @Mapping(target = "conversationPartner", source = "conversationPartner")
  @Mapping(target = "id", source = "entity.id")
  @Mapping(target = "lastMessage", source = "lastMessage")
  @Mapping(target = "unreadCount", source = "unreadCount")
  DialogDto toDto(DialogEntity entity, AccountDto conversationPartner, MessageDto lastMessage, Long unreadCount);



  default ZonedDateTime newTime() {
    return ZonedDateTime.now();
  }

}


