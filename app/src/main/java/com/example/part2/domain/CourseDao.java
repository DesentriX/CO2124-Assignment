package com.example.part2.domain;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert
    long insertCourse(Course course);


    @Transaction
    @Query("SELECT * FROM courses WHERE courseId = :courseId")
    CourseWithStudents getCourseWithStudents(int courseId);

    @Query("DELETE FROM CourseStudentCrossRef WHERE courseId = :courseId")
    void deleteCourseEnrollments(int courseId);

    @Query("SELECT * FROM courses ORDER BY courseCode ASC")
    LiveData<List<Course>> getAllCourses();

    @Delete
    void deleteCourse(Course course);


}
