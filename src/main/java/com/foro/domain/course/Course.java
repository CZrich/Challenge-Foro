package com.foro.domain.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foro.domain.topic.Topic;
import jakarta.persistence.*;

import lombok.*;

import java.util.List;


@Table(name = "courses")
@Entity(name = "Course")

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Topic> topicList;

    @PrePersist
    @PreUpdate
    private void convertCategoryToUpperCase() {
        if (this.category != null) {
            this.category = Category.valueOf(this.category.name().toUpperCase());
        }
    }

}
