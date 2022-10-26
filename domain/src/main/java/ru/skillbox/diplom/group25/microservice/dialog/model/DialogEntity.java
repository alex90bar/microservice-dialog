package ru.skillbox.diplom.group25.microservice.dialog.model;

import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * DialogueEntity
 *
 * @author alex90bar
 */

@Getter
@Setter
@Entity
@Table(name = "dialog")
@RequiredArgsConstructor
public class DialogEntity {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "author_id", nullable = false)
  private Long authorId;

  @Column(name = "recipient_id", nullable = false)
  private Long recipientId;

  @Column(name = "last_message")
  private Long lastMessage;

  @Column(name = "unread_count")
  private Long unreadCount;

  @OneToMany(mappedBy = "dialog")
  private List<MessageEntity> messages;







}


