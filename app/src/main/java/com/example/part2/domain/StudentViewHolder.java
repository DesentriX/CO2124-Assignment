package com.example.part2.domain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.R;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    private final TextView studentNameTextView;
    private final TextView studentEmailTextView;

    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        studentNameTextView = itemView.findViewById(R.id.textStudentName);
        studentEmailTextView = itemView.findViewById(R.id.textStudentEmail);
    }

    public void bind(Student student) {
        studentNameTextView.setText("Name: " + student.getName());
        studentEmailTextView.setText("Email: " + student.getEmail());
    }

    public static StudentViewHolder create(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }
}
