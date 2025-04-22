package com.example.part2.domain;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertStudent(Student student);

    @Transaction
    @Query("SELECT * FROM students WHERE studentId = :studentId")
    StudentWithCourses getStudentWithCourses(int studentId);

    @Query("DELETE FROM CourseStudentCrossRef WHERE studentId = :studentId")
    void deleteStudentEnrollments(int studentId);

    @Query("SELECT * FROM students ORDER BY name ASC")
    LiveData<List<Student>> getAllStudents();

    @Delete
    void deleteStudent(Student student);
}
