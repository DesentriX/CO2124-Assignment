package com.example.part2.domain;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.part2.domain.Student;
import com.example.part2.domain.StudentViewHolder;

public class StudentListAdapter extends ListAdapter<Student, StudentViewHolder> {

    public StudentListAdapter(@NonNull DiffUtil.ItemCallback<Student> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return StudentViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = getItem(position);
        holder.bind(student);
    }

    public static class StudentDiff extends DiffUtil.ItemCallback<Student> {
        @Override
        public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
            return oldItem.getStudentId() == newItem.getStudentId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getEmail().equals(newItem.getEmail()) &&
                    oldItem.getUserName().equals(newItem.getUserName());
        }
    }
}