package com.example.part2.domain;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CourseDetailsActivity extends AppCompatActivity {
    private TextView courseCode, courseName, lecturerName;
    private CourseViewModel courseViewModel;
    private StudentListAdapter studentListAdapter;
    private RecyclerView studentsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_details);

        Intent intent = getIntent();

        // Get the data
        int courseId = getIntent().getIntExtra("courseId", -1);
        String course_code = intent.getStringExtra("courseCode");
        String course_name = intent.getStringExtra("courseName");
        String lecturer_name = intent.getStringExtra("lecturerName");

        // Now you can use this data in your UI
        courseCode = findViewById(R.id.textCourseCode);
        courseName = findViewById(R.id.textCourseName);
        lecturerName = findViewById(R.id.textLecturerName);
        studentsRecyclerView = findViewById(R.id.studentsList);

        courseCode.setText("Course Code: " + course_code);
        courseName.setText("Course Name: " + course_name);
        lecturerName.setText("Lecturer: " + lecturer_name);

        StudentListAdapter studentListAdapter = new StudentListAdapter(new StudentListAdapter.StudentDiff(), new StudentListAdapter.OnStudentClickListener() {
            @Override
            public void onStudentClick(Student student) {
                Intent newIntent = new Intent(CourseDetailsActivity.this, StudentDetailsActivity.class);
                newIntent.putExtra("studentId", student.getStudentId());
                startActivity(newIntent);
            }
        });
        studentsRecyclerView.setAdapter(studentListAdapter);
        studentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getCourseWithStudents(courseId).observe(this, courseWithStudents -> {
            if (courseWithStudents != null && courseWithStudents.students != null) {
                studentListAdapter.submitList(courseWithStudents.students);
            }
        });

        FloatingActionButton fabAddStudent = findViewById(R.id.fab_add_student);
        fabAddStudent.setOnClickListener(view -> {
            Intent newIntent = new Intent(CourseDetailsActivity.this, AddStudentActivity.class);
            newIntent.putExtra("courseId", courseId); // pass current course ID
            startActivity(newIntent);
        });
    }
}
