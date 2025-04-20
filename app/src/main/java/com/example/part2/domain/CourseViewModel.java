package com.example.part2.domain;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {

    private CourseRepository crepo;
    private final LiveData<List<Course>> allCourses;

    public CourseViewModel(Application application) {
        super(application);
        crepo = new CourseRepository(application);
        allCourses = crepo.getAllCourses();
    }

    LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    public void insert(Course course) {
        crepo.insert(course);
    }

    public void delete(Course course) {
        crepo.delete(course);
    }

}
