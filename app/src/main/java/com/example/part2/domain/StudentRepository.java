package com.example.part2.domain;

import android.app.Application;
import android.util.Log;

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
            Log.d("StudentRepo", "Inserting student: " + student.getName() + ", " + student.getUserName());
            studentDao.insertStudent(student);
        });
    }

    void delete(Student student) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.deleteStudent(student);
        });
    }

    public void removeStudentFromCourse(int studentId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.deleteStudentEnrollments(studentId);  // Remove the student from the course
        });
    }



    //Q7
    public LiveData<Student> getStudentById(int studentId) {
        return studentDao.getStudentById(studentId);
    }

    public void update(Student student) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            studentDao.updateStudent(student);
        });
    }

}
