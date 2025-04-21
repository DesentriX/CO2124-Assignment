package com.example.part2.domain;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class CourseListAdapter extends ListAdapter<Course, CourseViewHolder> {

    // Constructor that takes a DiffUtil.ItemCallback for Course
    public CourseListAdapter(@NonNull DiffUtil.ItemCallback<Course> diffCallback) {
        super(diffCallback);
    }

    // Create a new ViewHolder and inflate the item layout
    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CourseViewHolder.create(parent);
    }

    // Bind the course data to the ViewHolder
    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        Course currentCourse = getItem(position);  // Get the current course from the list
        holder.bind(currentCourse);  // Bind the course data to the ViewHolder
    }

    // DiffUtil callback for detecting item differences
    static class CourseDiff extends DiffUtil.ItemCallback<Course> {

        // Check if the items are the same based on their unique courseId
        @Override
        public boolean areItemsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            return oldItem.getCourseId() == newItem.getCourseId();
        }

        // Check if the contents of the items are the same
        @Override
        public boolean areContentsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            return oldItem.getCourseCode().equals(newItem.getCourseCode()) &&
                    oldItem.getCourseName().equals(newItem.getCourseName()) &&
                    oldItem.getLecturerName().equals(newItem.getLecturerName());
        }
    }
}
