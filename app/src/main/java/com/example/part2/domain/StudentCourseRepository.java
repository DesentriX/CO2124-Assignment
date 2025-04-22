package com.example.part2.domain;

import android.app.Application;

public class StudentCourseRepository {

    private final StudentCourseDao studentCourseDao;

    public StudentCourseRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        studentCourseDao = db.studentCourseDao();
    }

    public void insertStudent(Student student) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            studentCourseDao.insertStudent(student);
        });
    }

    public void insertCourse(Course course) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            studentCourseDao.insertCourse(course);
        });
    }

    public void insertCrossRef(CourseStudentCrossRef crossRef) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            studentCourseDao.insertStudentCourseCrossRef(crossRef);
        });
    }
}