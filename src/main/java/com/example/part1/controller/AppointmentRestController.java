package com.example.part1.controller;


import com.example.part1.domain.Appointments;
import com.example.part1.domain.Doctor;
import com.example.part1.domain.MRecord;
import com.example.part1.domain.Patient;
import com.example.part1.repo.AppointmentsRepo;
import com.example.part1.repo.DoctorRepo;
import com.example.part1.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppointmentRestController {

    @Autowired
    AppointmentsRepo aRepo;

    @Autowired
    DoctorRepo dRepo;

    @Autowired
    PatientRepo pRepo;

    // List all appointments
    @GetMapping("/appointments")
    public List<Appointments> getAllAppointments() {
        return aRepo.findAll();
    }

    // Create a new appointment
    @PostMapping("/appointments")
    public ResponseEntity<?> createAppointment(@RequestBody Appointments appointment) {
        Long patientId = appointment.getPatient() != null ? appointment.getPatient().getId() : null;
        Long doctorId = appointment.getDoctor() != null ? appointment.getDoctor().getId() : null;

        if (patientId == null || doctorId == null) {
            return ResponseEntity.badRequest().body("Patient ID and Doctor ID must be provided.");
        }

        // Validate patient
        Patient patient = pRepo.findById(patientId).orElse(null);
        if (patient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Patient with ID " + patientId + " not found.");
        }

        // Validate doctor
        Doctor doctor = dRepo.findById(doctorId).orElse(null);
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Doctor with ID " + doctorId + " not found.");
        }

        // Links entities
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);


        Appointments saved = aRepo.save(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }



    // Retrieve a specific appointment by ID
    @GetMapping("/appointments/{id}")
    public ResponseEntity<?> getAppointment(@PathVariable Long id) {
        Appointments a = aRepo.findById(id).orElse(null);
        if (a == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found");
        }
        return new ResponseEntity<Appointments>(a, HttpStatus.OK);
    }

    //Update a specific appointment by ID
    @PutMapping("/appointments/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable Long id, @RequestBody Appointments appointment) {
        Appointments a = aRepo.findById(id).orElse(null);
        if (a == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found");
        }
        a.setStatus(appointment.getStatus());
        aRepo.save(a);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Delete a specific appointment by ID
    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        Appointments a = aRepo.findById(id).orElse(null);
        if (a == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found");
        }
        aRepo.delete(a);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Retrieve the medical record for a specific appointment
    @GetMapping("/appointments/{id}/medical-record")
    public ResponseEntity<?> getMedicalRecords(@PathVariable Long id) {
        // Find the appointment by ID
        Appointments appointment = aRepo.findById(id).orElse(null);

        // Check if the appointment exists
        if (appointment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found");
        }

        // Get the associated medical record
        MRecord medicalRecord = appointment.getRecords();

        // Check if the medical record exists
        if (medicalRecord == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medical record not found for the appointment");
        }

        // Return the medical record
        return new ResponseEntity<>(medicalRecord, HttpStatus.OK);
    }



}
