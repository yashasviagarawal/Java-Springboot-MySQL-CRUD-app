package com.yash.SimpleCRUD.repository;

import com.yash.SimpleCRUD.entity.Students;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentsRepository extends JpaRepository<Students, Integer> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Students WHERE id = :id", nativeQuery = true)
    int deleteStudentById(@Param("id") Integer id);

}
