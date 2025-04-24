package com.example.part2.domain;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.part2.R;

public class EditStudentActivity extends AppCompatActivity {
    private EditText nameEdit, emailEdit, matricEdit;
    private StudentViewModel studentViewModel;
    private int studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        nameEdit = findViewById(R.id.editStudentName);
        emailEdit = findViewById(R.id.editStudentEmail);
        matricEdit = findViewById(R.id.editStudentMatric);
        Button saveButton = findViewById(R.id.saveStudentButton);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        studentId = getIntent().getIntExtra("studentId", -1);
        studentViewModel.getStudentById(studentId).observe(this, student -> {
            if (student != null) {
                nameEdit.setText(student.getName());
                emailEdit.setText(student.getEmail());
                matricEdit.setText(student.getUserName());
            }
        });

        saveButton.setOnClickListener(v -> {
            String name = nameEdit.getText().toString();
            String email = emailEdit.getText().toString();
            String matric = matricEdit.getText().toString();

            Student updatedStudent = new Student();
            updatedStudent.setStudentId(studentId);
            updatedStudent.setName(name);
            updatedStudent.setEmail(email);
            updatedStudent.setUserName(matric);
            studentViewModel.update(updatedStudent);
            finish();





        });
    }

}
