package com.example.part2.domain;

import androidx.room.Entity;
import androidx.room.Index;

@Entity(
        primaryKeys = {"courseId", "studentId"},
        indices = {
                @Index(value = {"studentId"}),
                @Index(value = {"courseId"})
        }
)
public class CourseStudentCrossRef {
    public int courseId;
    public int studentId;
}
