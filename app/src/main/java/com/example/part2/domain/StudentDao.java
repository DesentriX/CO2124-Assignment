package com.example.part2.domain;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

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

    @Query("SELECT * FROM students WHERE studentId = :studentId")
    LiveData<Student> getStudentById(int studentId);



    @Update
    void updateStudent(Student student);  // Add the update method


    @Delete
    void deleteStudent(Student student);
}
