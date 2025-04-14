package com.example.part1.controller;

import com.example.part1.domain.MRecord;
import com.example.part1.repo.MRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MedicalRecordRestController {

    @Autowired
    MRecordRepo mrRepo;

    @PostMapping("/medical-records")
    @ResponseStatus(HttpStatus.CREATED)
    public MRecord createMedicalRecord(@RequestBody MRecord record) {
        return mrRepo.save(record);

    }
}
