package com.example.part1.controller;

import com.example.part1.domain.Doctor;
import com.example.part1.domain.Patient;
import com.example.part1.repo.PatientRepo;
import org.antlr.v4.runtime.atn.ErrorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientRestController {

    @Autowired
    PatientRepo pRepo;

    @GetMapping // to check that api is working
    public String welcome() {
        return "API is running";
    }

    // PATIENTS//

    // List all patients
    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return pRepo.findAll();
    }

    // Create a new patient
    @PostMapping("/patients")
        @ResponseStatus(HttpStatus.CREATED)
     public Patient createPatient(@RequestBody Patient patient) {
         return pRepo.save(patient);
    }


    // Retrieve a specific patient by ID
    @GetMapping("/patients/{id}")
    public ResponseEntity<?> getPatient(@PathVariable long id) {
        Patient p = pRepo.findById(id).orElse(null);
        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient with ID " + id + " not found.");
        }
        return new ResponseEntity<Patient>(p,HttpStatus.OK);
    }

    //Update a specific patient by ID
    @PutMapping("patients/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable long id, @RequestBody Patient patient) {
        Patient p = pRepo.findById(id).orElse(null);
        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient with ID " + id + " not found.");
        }
        p.setName(patient.getName());
        p.setPhoneNumber(String.valueOf(patient.getPhoneNumber()));
        pRepo.save(p);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Delete a specific patient by ID
    @DeleteMapping("patients/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable long id) {
        Patient p = pRepo.findById(id).orElse(null);
        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient with ID " + id + " not found.");
        }
        pRepo.delete(p);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // List all appointments for a specific patient
    @GetMapping("patients/{id}/appointments")
    public ResponseEntity<?> getAppointments(@PathVariable long id) {
        Patient p = pRepo.findById(id).orElse(null);
        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient with ID " + id + " not found.");

        }
        return new ResponseEntity<>(p.getAppointments(), HttpStatus.OK);

    }

    // List all medical records for a specific patient
    @GetMapping("patients/{id}/medical-records")
    public ResponseEntity<?> getMedicalRecords(@PathVariable long id) {
        Patient p = pRepo.findById(id).orElse(null);
        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient with ID " + id + " not found.");
        }
        return new ResponseEntity<>(p.getRecords(), HttpStatus.OK);
    }
}
