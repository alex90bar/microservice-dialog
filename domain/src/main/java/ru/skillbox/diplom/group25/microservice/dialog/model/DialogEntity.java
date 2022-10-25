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

  @Column(name = "time", nullable = false)
  private ZonedDateTime time;

  @Column(name = "author_id", nullable = false)
  private Long authorId;

  @Column(name = "receiver_id", nullable = false)
  private Long receiverId;

  @OneToMany(mappedBy = "dialog")
  private List<MessageEntity> messages;







}


