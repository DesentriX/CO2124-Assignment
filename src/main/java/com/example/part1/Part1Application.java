package com.example.part1;

import com.example.part1.domain.Appointments;
import com.example.part1.domain.Doctor;
import com.example.part1.domain.Patient;
import com.example.part1.repo.AppointmentsRepo;
import com.example.part1.repo.DoctorRepo;
import com.example.part1.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;

@SpringBootApplication

public class Part1Application  implements CommandLineRunner {


	@Autowired
	PatientRepo pR;

	@Autowired
	DoctorRepo dR;

	@Autowired
	AppointmentsRepo aR;

	public static void main(String[] args) {

		SpringApplication.run(Part1Application.class, args);

	}


	@Override
	public void run(String... args) throws Exception {
		/*
		Patient p = new Patient();
		p.setName("John Doe");
		p.setPhoneNumber("12134241412");
		pR.save(p);


		Doctor doc = new Doctor();
		doc.setName("Xavier");
		dR.save(doc);

		Appointments ap1 = new Appointments();
		ap1.setPatient(p);
		ap1.setDoctor(doc);
		ap1.setStatus("Active");
		ap1.setNotes("Nothing of note");

		// current timestamp
		Timestamp now = new Timestamp(System.currentTimeMillis());
		ap1.setAppointmentDate(now);
		aR.save(ap1);


*/


	}
}
