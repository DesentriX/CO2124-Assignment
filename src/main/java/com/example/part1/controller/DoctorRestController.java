package com.example.part1.controller;

import com.example.part1.domain.Doctor;
import com.example.part1.repo.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DoctorRestController {

    @Autowired
    DoctorRepo dRepo;




                                                    // DOCTORS//


    // List all doctors
    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return dRepo.findAll();
    }

    //Create a new doctor

    @PostMapping("/doctors")
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return dRepo.save(doctor);
    }

    //Retrieve a specific doctor by ID
    @GetMapping("/doctors/{id}")
    public ResponseEntity<?> getDoctor(@PathVariable Long id) {
        Doctor d = dRepo.findById(id).orElse(null);
        if (d == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
        return  new ResponseEntity<Doctor>(d,HttpStatus.OK);
    }

    //Update a specific doctor by ID
    @PutMapping("/doctors/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        Doctor d = dRepo.findById(id).orElse(null);
        if (d == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
        d.setName(doctor.getName());
        dRepo.save(d);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Delete a specific doctor by ID
    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        Doctor d = dRepo.findById(id).orElse(null);
        if (d == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
        dRepo.delete(d);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //List all appointments for a specific doctor
    @GetMapping("/doctors/{id}/appointments")
    public ResponseEntity<?> getAppointments(@PathVariable Long id) {
        Doctor d = dRepo.findById(id).orElse(null);
        if (d == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
        return new ResponseEntity<>(d.getAppointments(), HttpStatus.OK);
    }

}
