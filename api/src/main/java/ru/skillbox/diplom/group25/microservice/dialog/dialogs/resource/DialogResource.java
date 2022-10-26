//package ru.skillbox.diplom.group25.microservice.dialog.dialogs.resource;
//
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import ru.skillbox.diplom.alpha.microservice.dialog.dto.request.CreateDialogRq;
//import ru.skillbox.diplom.alpha.microservice.dialog.dto.response.CreateDialogRs;
//import ru.skillbox.diplom.alpha.microservice.dialog.dto.response.DeleteDialogRs;
//import ru.skillbox.diplom.alpha.microservice.dialog.dto.response.GetDialogsRs;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import ru.skillbox.diplom.alpha.microservice.dialog.dto.response.GetMessagesRs;
//import ru.skillbox.diplom.alpha.microservice.dialog.dto.response.SetStatusMessageReadRs;
//import ru.skillbox.diplom.alpha.microservice.dialog.dto.response.UnreadCountRs;
//
///**
// * DialogResource
// *
// * @author Sergey Olshevskiy
// */
//@RequestMapping("api/v1/dialogs")
//public interface DialogResource {
//
//    @GetMapping
//    ResponseEntity<GetDialogsRs> getDialogs(@RequestParam(defaultValue = "0") Integer offset,
//                                            @RequestParam(defaultValue = "20") Integer itemPerPage);
//
//    @PostMapping
//    ResponseEntity<CreateDialogRs> createDialog(@RequestBody CreateDialogRq userIds);
//
//    @GetMapping(value = "/unreaded")
//    ResponseEntity<UnreadCountRs> getUnreadMessageCount();
//
//    @DeleteMapping(value = "/{id}")
//    ResponseEntity<DeleteDialogRs> deleteDialog(@PathVariable("id") Long id);
//
//    @GetMapping(value = "/messages")
//    ResponseEntity<GetMessagesRs> getDialogMessages(@RequestParam Long interlocutorId,
//                                                    @RequestParam(defaultValue = "0") Integer offset,
//                                                    @RequestParam(defaultValue = "20") Integer itemPerPage);
//
//    @PutMapping(value = "/{interlocutor_id}/messages/{message_id}/read")
//    ResponseEntity<SetStatusMessageReadRs> setStatusMessageRead(@PathVariable("interlocutor_id") Long interlocutorId,
//                                                                @PathVariable("message_id") Long messageId);
//}
