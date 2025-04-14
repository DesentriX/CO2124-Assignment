package com.example.part1.controller;


import com.example.part1.domain.Appointments;
import com.example.part1.repo.AppointmentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppointmentRestController {

    @Autowired
    AppointmentsRepo aRepo;

    // List all appointments
    @GetMapping("/appointments")
    public List<Appointments> getAllAppointments() {
        return aRepo.findAll();
    }

    // Create a new appointment
    @PostMapping("/appointments")
    @ResponseStatus(HttpStatus.CREATED)
    public Appointments createAppointment(@RequestBody Appointments appointment) {
        return aRepo.save(appointment);
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
    @GetMapping("/appointments/{id}/medical-records")
    public ResponseEntity<?> getMedicalRecords(@PathVariable Long id) {
        Appointments a = aRepo.findById(id).orElse(null);
        if (a == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment or medical record not found");
        }
        return new ResponseEntity<>(a, HttpStatus.OK);
    }


}
