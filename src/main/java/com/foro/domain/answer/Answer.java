package com.foro.domain.answer;

import com.foro.domain.topic.Topic;
import com.foro.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="answers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne
    private Topic topic;

    private LocalDateTime dateCreation;
    @ManyToOne
    private User author;
    private  Boolean solution;
}
