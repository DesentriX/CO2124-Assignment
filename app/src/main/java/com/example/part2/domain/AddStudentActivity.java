package com.example.part2.domain;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.part2.R;

public class AddStudentActivity extends AppCompatActivity {

    private EditText editStudentName, editStudentEmail, editStudentMatricNumber;
    private Button addStudentButton;
    private int courseId;
    private StudentCourseRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        StudentViewModel studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        courseId = getIntent().getIntExtra("courseId", -1);
        repository = new StudentCourseRepository(getApplication());

        editStudentName = findViewById(R.id.editStudentName);
        editStudentEmail = findViewById(R.id.editStudentEmail);
        editStudentMatricNumber = findViewById(R.id.editStudentMatricNumber);
        addStudentButton = findViewById(R.id.createStudentButton);

        addStudentButton.setOnClickListener(v -> {
            String name = editStudentName.getText().toString().trim();
            String email = editStudentEmail.getText().toString().trim();
            String matricNumber = editStudentMatricNumber.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || matricNumber.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if student already exists (based on matric)
            AppDatabase.databaseWriteExecutor.execute(() -> {
                Student existing = repository.getStudentByMatric(matricNumber);

                if (existing != null) {
                    // Check if already enrolled
                    boolean isEnrolled = repository.isStudentEnrolledInCourse(existing.getStudentId(), courseId);
                    if (isEnrolled) {
                        runOnUiThread(() ->
                                Toast.makeText(this, "Student already enrolled", Toast.LENGTH_SHORT).show()
                        );
                        return;
                    }

                    // Student exists but not enrolled - just enroll
                    CourseStudentCrossRef crossRef = new CourseStudentCrossRef();
                    crossRef.studentId = existing.getStudentId();
                    crossRef.courseId = courseId;
                    repository.insertCrossRef(crossRef);
                } else {
                    // Student does not exist - insert and enroll
                    Student newStudent = new Student();
                    newStudent.setName(name);
                    newStudent.setEmail(email);
                    newStudent.setUserName(matricNumber);
                    long studentId = repository.insertStudentAndReturnId(newStudent); // custom method you’ll create

                    // Associates the student with a course using the CrossRef table
                    CourseStudentCrossRef crossRef = new CourseStudentCrossRef();
                    crossRef.studentId = (int)studentId;
                    crossRef.courseId = courseId;
                    repository.insertCrossRef(crossRef);
                }

                runOnUiThread(() -> {
                    Toast.makeText(this, "Student added", Toast.LENGTH_SHORT).show();
                    finish();
                });
            });
        });
    }
}
