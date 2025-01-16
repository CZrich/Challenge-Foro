package com.foro.domain.topic;

import com.foro.domain.answer.Answer;
import com.foro.domain.course.Course;
import com.foro.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topics")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;

    private LocalDateTime dateCreation;
    private Boolean status;

    @ManyToOne
    private User author;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "topic",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Answer> answers;

   public LocalDateTime getTime(){
       return LocalDateTime.now();
   }


}
