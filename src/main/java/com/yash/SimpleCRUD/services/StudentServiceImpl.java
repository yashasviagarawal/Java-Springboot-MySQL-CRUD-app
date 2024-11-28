package com.yash.SimpleCRUD.services;

import com.yash.SimpleCRUD.entity.Students;
import com.yash.SimpleCRUD.models.ResponseObject;
import com.yash.SimpleCRUD.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentsRepository studentsRepository;



    @Override
    public ResponseEntity<ResponseObject> getAllStudents() {
        List<Students> students = studentsRepository.findAll();
        ResponseObject responseObject = new ResponseObject();
        ResponseEntity<ResponseObject> response;
        if (!students.isEmpty()){
            responseObject.setStudent(students);
            responseObject.setMessage("Students Fetched Successfully!!");
            response = new ResponseEntity<ResponseObject>(responseObject, HttpStatus.OK);
        }
        else {
            responseObject.setStudent(null);
            responseObject.setMessage("Unable to Fetch Students!!");
            response = new ResponseEntity<ResponseObject>(responseObject, HttpStatus.NO_CONTENT);
        }

        return response;
    }

    @Override
    public ResponseEntity<ResponseObject>  getStudentById(int id) {
        Object students = studentsRepository.findById(id);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setStudent(students);
        responseObject.setMessage("Student Fetched Successfully!!");
        return new ResponseEntity<ResponseObject>(responseObject,HttpStatus.OK);
    }

    @Override
    public ResponseObject addStudent(Students student) {
        ResponseObject response = new ResponseObject();

        Students students = studentsRepository.save(student);
        if(Objects.nonNull(students)){
            response.setStudent(students);
            response.setMessage("Record Added Successfully!!");
        }
        else{
            response.setMessage("Error while adding Student");
        }
        return response;
    }

    @Override
    public int deleteStudent(int id) {

        return studentsRepository.deleteStudentById(id);

    }
}
