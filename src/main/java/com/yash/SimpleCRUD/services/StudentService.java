package com.yash.SimpleCRUD.services;

import com.yash.SimpleCRUD.entity.Students;
import com.yash.SimpleCRUD.models.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

    ResponseEntity<ResponseObject> getAllStudents();

    ResponseEntity<ResponseObject>  getStudentById(int id);

    ResponseObject addStudent(Students student);

    int deleteStudent(int id);

}
