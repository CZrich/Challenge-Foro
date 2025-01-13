package com.foro.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {


    @Query(""" 
            SELECT t FROM Topic t
            WHERE LOWER(t.title)
            LIKE LOWER(:title)
            AND LOWER(t.message) LIKE LOWER(:message)
            """)
    Optional<Topic> findByTitleAndMessage(@Param("title") String title, @Param("message") String message);

    @Query("""
              SELECT t
              FROM Topic t
              JOIN Course c on
              c.id=t.course.id
              WHERE YEAR(t.dateCreation) = :year
              AND LOWER(c.name) = LOWER(:name)
              """)
    Page<Topic> listTopicsForCourseAndYear(@Param("pageable")Pageable pageable,@Param("year") Integer year, @Param("name") String name);


    //Page<DetailTopic> listTopicsForCourseAndYear(Pageable pageable, String s, Integer year);
}
