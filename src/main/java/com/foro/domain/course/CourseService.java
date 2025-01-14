package com.foro.domain.course;

import com.foro.domain.exceptios.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
     CourseRepository courseRepository;


    public Course  createCourse (CourseDto courseDto){
         Course course = new Course();
          course.setName(courseDto.name());
          course.setCategory(Category.fromString(courseDto.category()));

        return courseRepository.save(course);

    }

    public  Course getCourse(Long id){
        if(!courseRepository.existsById(id)){
            throw new RequestException("course doesn't exist with id "+id, HttpStatus.BAD_REQUEST);
        }
        return  courseRepository.findById(id).get();
    }

}
