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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudentCourseCrossRef(CourseStudentCrossRef crossRef);

    @Transaction
    @Query("SELECT * FROM students WHERE studentId = :studentId")
    LiveData<List<StudentWithCourses>> getStudentWithCourses(int studentId);
}
