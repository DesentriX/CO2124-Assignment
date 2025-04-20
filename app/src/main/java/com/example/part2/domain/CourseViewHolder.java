package com.example.part2.domain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.R;

public class CourseViewHolder extends RecyclerView.ViewHolder {
    private final TextView courseCode;
    private final TextView courseName;
    private final TextView lecturerName;

    private CourseViewHolder(View itemView) {
        super(itemView);
        courseCode = itemView.findViewById(R.id.textCourseCode);
        courseName = itemView.findViewById(R.id.textCourseName);
        lecturerName = itemView.findViewById(R.id.textLecturerName);
    }

    // Bind data to the views
    public void bind(Course course) {
        courseCode.setText(course.getCourseCode());
        courseName.setText(course.getCourseName());
        lecturerName.setText(course.getLecturerName());
    }

    // Create a new ViewHolder by inflating the item layout
    static CourseViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }
}
