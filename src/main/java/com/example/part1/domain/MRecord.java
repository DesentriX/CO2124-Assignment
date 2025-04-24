package com.example.part1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Timestamp recordDate) {
        this.recordDate = recordDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Appointments getAppointments() {
        return appointments;
    }

    public void setAppointments(Appointments appointments) {
        this.appointments = appointments;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnoreProperties({"appointments", "mRecords"})
    private Patient patient;



    @OneToOne
    @JoinColumn(name = "appointment_id")
    @JsonIgnoreProperties({"patient", "doctor", "records"})
    private Appointments appointments;



}
