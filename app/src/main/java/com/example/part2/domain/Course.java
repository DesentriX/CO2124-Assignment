package com.example.part2.domain;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class Course {

    @PrimaryKey(autoGenerate = true)
    public int courseId;

    public String courseCode; // "CO2124" (must be unique)
    @NonNull
    @ColumnInfo(name = "courseName")
    public String courseName;

    @NonNull
    @ColumnInfo(name = "lecturerName")
    public String lecturerName;


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @NonNull
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(@NonNull String courseName) {
        this.courseName = courseName;
    }

    @NonNull
    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(@NonNull String lecturerName) {
        this.lecturerName = lecturerName;
    }


}
