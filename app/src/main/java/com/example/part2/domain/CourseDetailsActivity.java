package com.example.part2.domain;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.part2.R;

public class CourseDetailsActivity extends AppCompatActivity {
    private TextView courseCode, courseName, lecturerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_details);

        Intent intent = getIntent();

        // Get the data
        String course_code = intent.getStringExtra("courseCode");
        String course_name = intent.getStringExtra("courseName");
        String lecturer_name = intent.getStringExtra("lecturerName");

        // Now you can use this data in your UI
        courseCode = findViewById(R.id.textCourseCode);
        courseName = findViewById(R.id.textCourseName);
        lecturerName = findViewById(R.id.textLecturerName);

        courseCode.setText("Course Code: " + course_code);
        courseName.setText("Course Name: " + course_name);
        lecturerName.setText("Lecturer: " + lecturer_name);
    }
}
