package com.example.part2.domain;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseRepository {
    private CourseDao courseDao;
    private LiveData<List<Course>> allCourses;

    CourseRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        courseDao = db.courseDao();
        allCourses = courseDao.getAllCourses();
    }

    LiveData<List<Course>> getAllCourses() {
        return allCourses;
    }

    void insert(Course course) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            courseDao.insertCourse(course);
        });
    }

    void delete(Course course) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            courseDao.deleteCourse(course);
        });
    }
}
