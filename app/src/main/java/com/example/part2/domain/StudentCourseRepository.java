package com.example.part2.domain;

import android.app.Application;

public class StudentCourseRepository {

    private final StudentCourseDao studentCourseDao;
    private final CourseDao courseDao;

    public StudentCourseRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        studentCourseDao = db.studentCourseDao();
        courseDao = db.courseDao();
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

    public Student getStudentByMatric(String matric) {
        return studentCourseDao.getStudentByMatric(matric);
    }

    public boolean isStudentEnrolledInCourse(int studentId, int courseId) {
        return studentCourseDao.isEnrolled(studentId, courseId) != null;
    }

    public long insertStudentAndReturnId(Student student) {
        return studentCourseDao.insertStudentAndReturnId(student);
    }

    public void insertCrossRef(CourseStudentCrossRef crossRef) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            studentCourseDao.insertStudentCourseCrossRef(crossRef);
        });
    }

    public void deleteCourseAndEnrollments(Course course) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            studentCourseDao.deleteCrossRefsForCourse(course.courseId);
            courseDao.deleteCourse(course);
        });
    }
}