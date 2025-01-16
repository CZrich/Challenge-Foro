package com.foro.controller;

import com.foro.domain.course.CourseDetailDto;
import com.foro.domain.course.CourseRegisterDto;
import com.foro.domain.course.CourseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")

public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CourseRegisterDto courseDto){
         var course= courseService.createCourse(courseDto);

         return ResponseEntity.status(HttpStatus.CREATED).body(course);

    }

    @GetMapping("/{id}")
    public  ResponseEntity<CourseDetailDto> getCourse(@PathVariable Long id){
        var course = courseService.getCourse(id);
        return ResponseEntity.status(HttpStatus.valueOf(200) ).body( new CourseDetailDto(course));
    }

    @GetMapping
    public Page<CourseDetailDto> getAllCourses(@PageableDefault(size = 10,sort={"name"})Pageable pageable){
        return  courseService.getAllCourses(pageable).map(CourseDetailDto::new);
    }


    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity<Void> updateCourse(@PathVariable Long id, @RequestBody @Valid CourseRegisterDto courseDto){
        return  courseService.updateCourse(id,courseDto);
    }

    @DeleteMapping("/{id}")

    public  ResponseEntity<Void> deleteCourse(@PathVariable Long id){
        return  courseService.deleteCourse(id);
    }
}
