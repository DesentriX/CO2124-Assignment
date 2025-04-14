package com.example.part1.domain;

import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
public class MRecord {
    @Id
    @GeneratedValue
    private Long id;
    private Timestamp recordDate; //date and time when the record was created
    private String diagnosis;
    private String treatment; // the treat prescribed to patient
    private String notes; //additional notes for the medical record

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointments appointments;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;


}
