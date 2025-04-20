package com.example.part2.domain;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.part2.R;

public class CreateCourseActivity extends AppCompatActivity {
    private EditText editCourseCode, editCourseName, editLecturerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

        editCourseCode = findViewById(R.id.editCourseCode);
        editCourseName = findViewById(R.id.editCourseName);
        editLecturerName = findViewById(R.id.editLecturerName);

        Button createButton = findViewById(R.id.createCourseButton);
        createButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("courseCode", editCourseCode.getText().toString());
            resultIntent.putExtra("courseName", editCourseName.getText().toString());
            resultIntent.putExtra("lecturerName", editLecturerName.getText().toString());
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

}
