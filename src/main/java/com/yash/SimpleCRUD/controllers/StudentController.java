package com.yash.SimpleCRUD.controllers;

import com.yash.SimpleCRUD.entity.Students;
import com.yash.SimpleCRUD.models.ResponseObject;
import com.yash.SimpleCRUD.repository.StudentsRepository;
import com.yash.SimpleCRUD.services.StudentService;
import com.yash.SimpleCRUD.services.StudentServiceImpl;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private StudentServiceImpl studentService;


    @GetMapping("/getAllStudents")
    public ResponseEntity<ResponseObject> getAllStudents(){
        ResponseEntity<ResponseObject> response = studentService.getAllStudents();
        return new ResponseEntity<ResponseObject>(response.getBody(), HttpStatus.OK);
    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<ResponseObject> getStudent(@PathVariable int id){
        ResponseEntity<ResponseObject> response = studentService.getStudentById(id);
        return new ResponseEntity<ResponseObject>(response.getBody(), HttpStatus.OK);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<ResponseObject> addStudent(@RequestBody Students students) throws BadRequestException {

        if(Objects.isNull(students.getId())){
            throw new BadRequestException("Student Id Cannot be Empty");
        }
        if(Objects.isNull(students.getName())){
            throw new BadRequestException("Student name Cannot be Empty");
        }
        if(Objects.isNull(students.getAge())){
            throw new BadRequestException("Student age Cannot be Empty");
        }
        if(Objects.isNull(students.getPhoneNumber())){
            throw new BadRequestException("Student phone number Cannot be Empty");
        }
        if(Objects.isNull(students.getEmail())){
            throw new BadRequestException("Student email id Cannot be Empty");
        }

        ResponseObject responseObject = studentService.addStudent(students);
        if(responseObject.getMessage().equals("Error while adding Student")){
            return new ResponseEntity<ResponseObject>(responseObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            return new ResponseEntity<ResponseObject>(responseObject,HttpStatus.OK);

        }
    }

    @PostMapping("/deleteStudent/{id}")
    public ResponseEntity<ResponseObject> deleteStudent(@PathVariable int id) throws BadRequestException {

        ResponseObject responseObject = new ResponseObject();

        if(Objects.isNull(id)){
            throw new BadRequestException("Student Id Cannot be Empty");
        }

        int response = studentService.deleteStudent(id);
        log.info(response+" Rows Deleted");
        if(Objects.isNull(response)){
            responseObject.setMessage("Failed to Delete Record");
            return new ResponseEntity<ResponseObject>(responseObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(response==0){
            responseObject.setMessage("No Record Found");
            return new ResponseEntity<ResponseObject>(responseObject,HttpStatus.NOT_FOUND);
        }
        else{
            responseObject.setMessage("Record Deleted Successfully");
            return new ResponseEntity<ResponseObject>(responseObject,HttpStatus.OK);

        }
    }
}
