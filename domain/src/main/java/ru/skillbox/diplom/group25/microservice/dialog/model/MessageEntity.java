package ru.skillbox.diplom.group25.microservice.dialog.model;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * MessageEntity
 *
 * @author alex90bar
 */

@Getter
@Setter
@Entity
@Table(name = "message")
@RequiredArgsConstructor
public class MessageEntity {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "time", nullable = false)
  private ZonedDateTime time;

  @Column(name = "author_id", nullable = false)
  private Long authorId;

  @Column(name = "post_text", nullable = false, length = 4096)
  private String messageText;

  @Column(name = "is_delete")
  private Boolean isDelete;

  @ManyToOne
  @JoinColumn(name = "dialog_id", referencedColumnName = "id")
  private DialogEntity dialog;

}


