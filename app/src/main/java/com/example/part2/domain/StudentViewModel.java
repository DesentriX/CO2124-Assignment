package com.example.part2.domain;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private StudentRepository srepo;
    private StudentCourseRepository screpo;
    private final LiveData<List<Student>> allStudents;

    public StudentViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        srepo = new StudentRepository(application);
        screpo = new StudentCourseRepository(application);
        allStudents = srepo.getAllStudents();
    }

    LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }

    public void insert(Student student) {
        srepo.insert(student);
    }

    public void insertCrossRef(CourseStudentCrossRef crossRef) {
        screpo.insertCrossRef(crossRef);
    }

    public void deleteCourseAndEnrollments(Course course) {
        screpo.deleteCourseAndEnrollments(course);
    }

    public void delete(Student student) {
        srepo.delete(student);
    }

}
