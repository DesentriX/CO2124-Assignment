package com.example.part1.controller;

import com.example.part1.domain.Appointments;
import com.example.part1.domain.MRecord;
import com.example.part1.domain.Patient;
import com.example.part1.repo.AppointmentsRepo;
import com.example.part1.repo.MRecordRepo;
import com.example.part1.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MedicalRecordRestController {

    @Autowired
    MRecordRepo mrRepo;

    @Autowired
    AppointmentsRepo aRepo;


    @Autowired
    PatientRepo pRepo;




    @PostMapping("/medical-records")
    public ResponseEntity<MRecord> createMedicalRecord(@RequestBody MRecord record) {
        Long appointmentId = record.getAppointments() != null ? record.getAppointments().getId() : null;
        Long patientId = record.getPatient() != null ? record.getPatient().getId() : null;

        if (appointmentId == null || patientId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Appointments appointment = aRepo.findById(appointmentId).orElse(null);
        Patient patient = pRepo.findById(patientId).orElse(null);

        if (appointment == null || patient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        record.setAppointments(appointment);
        record.setPatient(patient);

        MRecord savedRecord = mrRepo.save(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecord);
    }



}
