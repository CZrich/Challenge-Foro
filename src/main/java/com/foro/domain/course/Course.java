package com.foro.domain.course;

import jakarta.persistence.*;

import lombok.*;


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

    @PrePersist
    @PreUpdate
    private void convertCategoryToUpperCase() {
        if (this.category != null) {
            this.category = Category.valueOf(this.category.name().toUpperCase());
        }
    }

}
