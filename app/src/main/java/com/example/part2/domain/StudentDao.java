package com.example.part2.domain;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface StudentDao {

    @Insert
    long insertStudent(Student student);

    @Transaction
    @Query("SELECT * FROM students WHERE studentId = :studentId")
    StudentWithCourses getStudentWithCourses(int studentId);

    @Query("DELETE FROM CourseStudentCrossRef WHERE studentId = :studentId")
    void deleteStudentEnrollments(int studentId);

    @Delete
    void deleteStudent(Student student);
}
