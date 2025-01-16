package com.foro.domain.course;

import com.foro.domain.exceptios.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
     CourseRepository courseRepository;


    public Course  createCourse (CourseRegisterDto courseDto){

        if (courseRepository.findByName(courseDto.name()).isPresent()){
            throw  new RequestException("course already exist",HttpStatus.BAD_REQUEST);
        }

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

    public Page<Course> getAllCourses(Pageable pageable){

        return  courseRepository.findAll(pageable);
    }

    public ResponseEntity<Void> deleteCourse(Long id){

        if(!courseRepository.existsById(id)){
            throw new RequestException("course doesn't exist with id "+id, HttpStatus.BAD_REQUEST);
        }
        courseRepository.deleteById(id);
        return  ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }

    public  ResponseEntity<Void>   updateCourse(Long id, CourseRegisterDto courseDto){
         var course= getCourse(id);

         course.setName(courseDto.name());
         course.setCategory(Category.fromString(courseDto.category()));
        return  ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }





}
