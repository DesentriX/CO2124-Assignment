package com.example.part2.domain;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.R;

public class StudentDetailsActivity extends AppCompatActivity {

    private StudentViewModel studentViewModel;
    private CourseListAdapter courseListAdapter;
    private int studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView matricTextView = findViewById(R.id.matricNumberTextView);
        RecyclerView courseRecyclerView = findViewById(R.id.courseRecyclerView);

        courseListAdapter = new CourseListAdapter(new CourseListAdapter.CourseDiff(), new CourseListAdapter.OnCourseClickListener() {
            @Override
            public void onCourseClick(Course course) {
                // Optional
            }

            @Override
            public void onCourseLongClick(Course course) {
                // Optional
            }
        });
        courseRecyclerView.setAdapter(courseListAdapter);
        courseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        studentId = getIntent().getIntExtra("studentId", -1);
        if (studentId != -1) {
            studentViewModel.getStudentWithCourses(studentId).observe(this, studentWithCourses -> {
                if (studentWithCourses != null) {
                    Student student = studentWithCourses.student;
                    nameTextView.setText("Name: " + student.getName());
                    emailTextView.setText("Email: " + student.getEmail());
                    matricTextView.setText("Matric Number: " + student.getUserName());

                    courseListAdapter.submitList(studentWithCourses.courses);
                }
            });
        }
    }

}
