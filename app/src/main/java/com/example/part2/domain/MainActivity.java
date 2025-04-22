package com.example.part2.domain;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.R;
import com.example.part2.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CourseViewModel courseViewModel;
    private StudentViewModel studentViewModel;
    private StudentCourseDao studentCourseDao;

    // Create an ActivityResultLauncher to handle the result from CreateCourseActivity
    private final ActivityResultCallback<ActivityResult> activityResultCallback = new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            // Check if the result is OK
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    // Retrieve the course data from the intent
                    String courseCode = data.getStringExtra("courseCode");
                    String courseName = data.getStringExtra("courseName");
                    String lecturerName = data.getStringExtra("lecturerName");

                    // Create a new Course object and insert it into the database
                    Course newCourse = new Course();
                    newCourse.setCourseCode(courseCode);
                    newCourse.setCourseName(courseName);
                    newCourse.setLecturerName(lecturerName);

                    // Insert the new course into the database through the ViewModel
                    courseViewModel.insert(newCourse);
                }
            }
        }
    };

    // Register the activity result launcher to handle results
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), activityResultCallback);

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StudentViewModel studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        Course newCourse = new Course();
        newCourse.setCourseName("Ko");
        newCourse.setCourseCode("CO2012");
        newCourse.setLecturerName("Timothy");
        newCourse.setCourseId(12);

        Student newStudent = new Student();
        newStudent.setName("John Doe");
        newStudent.setEmail("john@example.com");
        newStudent.setUserName("JD124");
        newStudent.setStudentId(9);

        studentViewModel.insert(newStudent);


        // Insert the student and the course - test data
        studentViewModel.insert(newStudent);
        courseViewModel.insert(newCourse);

        // Associates the student with a course using the CrossRef table
        CourseStudentCrossRef crossRef = new CourseStudentCrossRef();
        crossRef.studentId = 9;
        crossRef.courseId = 12;
        studentViewModel.insertCrossRef(crossRef);

        RecyclerView recyclerView = findViewById(R.id.courseRecyclerView);
        final CourseListAdapter courseAdapter = new CourseListAdapter(new CourseListAdapter.CourseDiff(), new CourseListAdapter.OnCourseClickListener() {
            @Override
            public void onCourseClick(Course course) {
                Intent intent = new Intent(MainActivity.this, CourseDetailsActivity.class);
                intent.putExtra("courseId", course.getCourseId());
                intent.putExtra("courseCode", course.getCourseCode());
                intent.putExtra("courseName", course.getCourseName());
                intent.putExtra("lecturerName", course.getLecturerName());
                activityResultLauncher.launch(intent);
            }
        });
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        // Observe LiveData from the ViewModel
        courseViewModel.getAllCourses().observe(this, courses -> {
            courseAdapter.submitList(courses);
        });


        // Get reference to the FAB and set up an onClickListener
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(view -> {
            // Launch CreateCourseActivity and get the result when the user finishes
            Intent intent = new Intent(MainActivity.this, CreateCourseActivity.class);
            activityResultLauncher.launch(intent);
        });
    }
}