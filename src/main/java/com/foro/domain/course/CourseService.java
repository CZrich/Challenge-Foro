package com.foro.domain.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
     CourseRepository courseRepository;


    public Course  createCourse (CourseDto courseDto){
         Course course = new Course();
          course.setName(courseDto.name());
          course.setCategory(Category.fromString(courseDto.category()));
          //Course course = new Course(null,courseDto.name(),Category.fromString(courseDto.category()));
        return courseRepository.save(course);
    }

}
