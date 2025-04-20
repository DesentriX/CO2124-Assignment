package com.example.part2.domain;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface EnrollmentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void enrollStudentInCourse(CourseStudentCrossRef crossRef);
}
