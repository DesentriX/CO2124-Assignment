package com.example.part2.domain;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface StudentCourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudent(Student student);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourse(Course course);

    @Query("SELECT * FROM students WHERE username = :matric LIMIT 1")
    Student getStudentByMatric(String matric);

    @Query("SELECT * FROM CourseStudentCrossRef WHERE studentId = :studentId AND courseId = :courseId LIMIT 1")
    CourseStudentCrossRef isEnrolled(int studentId, int courseId);

    @Insert
    long insertStudentAndReturnId(Student student);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudentCourseCrossRef(CourseStudentCrossRef crossRef);

    @Query("DELETE FROM CourseStudentCrossRef WHERE courseId = :courseId")
    void deleteCrossRefsForCourse(int courseId);

    @Transaction
    @Query("SELECT * FROM students WHERE studentId = :studentId")
    LiveData<StudentWithCourses> getStudentWithCourses(int studentId);
}
