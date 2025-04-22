package com.example.part2.domain;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {
    private StudentDao studentDao;
    private LiveData<List<Student>> allStudents;

    StudentRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        studentDao = db.studentDao();
        allStudents = studentDao.getAllStudents();
    }

    LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }

    void insert(Student student) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.insertStudent(student);
        });
    }

    void delete(Student student) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.deleteStudent(student);
        });
    }
}
