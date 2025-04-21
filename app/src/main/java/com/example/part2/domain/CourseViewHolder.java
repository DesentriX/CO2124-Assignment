package com.example.part2.domain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.R;

public class CourseViewHolder extends RecyclerView.ViewHolder {
    private final TextView courseCode;
    private final TextView courseName;
    private final TextView lecturerName;
    private final ConstraintLayout courseLayout;

    private CourseViewHolder(View itemView) {
        super(itemView);
        courseCode = itemView.findViewById(R.id.textCourseCode);
        courseName = itemView.findViewById(R.id.textCourseName);
        lecturerName = itemView.findViewById(R.id.textLecturerName);

        courseLayout = itemView.findViewById(R.id.courseLayout);
    }

    // Bind data to the views
    public void bind(Course course, CourseListAdapter.OnCourseClickListener listener) {
        String course_code = "Course Code: %s";
        String formatted_course_code = String.format(course_code, course.getCourseCode());
        courseCode.setText(formatted_course_code);

        String course_name = "Course Name: %s";
        String formatted_course_name = String.format(course_name, course.getCourseName());
        courseName.setText(formatted_course_name);

        String lecturer_name = "Lecturer Name: %s";
        String formatted_lecturer_name = String.format(lecturer_name, course.getLecturerName());
        lecturerName.setText(formatted_lecturer_name);

        courseLayout.setOnClickListener(view -> {
            listener.onCourseClick(course);
        });
    }

    // Create a new ViewHolder by inflating the item layout
    static CourseViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }
}
