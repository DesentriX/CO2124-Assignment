package com.example.part2.domain;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.part2.domain.Student;
import com.example.part2.domain.StudentViewHolder;

public class StudentListAdapter extends ListAdapter<Student, StudentViewHolder> {
    private final OnStudentClickListener listener;

    public StudentListAdapter(@NonNull DiffUtil.ItemCallback<Student> diffCallback, OnStudentClickListener listener) {
        super(diffCallback);
        this.listener = listener;
    }

    public interface OnStudentClickListener {
        void onStudentClick(Student student);
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
        holder.itemView.setOnClickListener(v -> listener.onStudentClick(student));
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