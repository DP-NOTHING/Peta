package javapro.doctor_appointment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javapro.doctor_appointment.entity.Doctor;
import javapro.doctor_appointment.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public List<Doctor> getAllDoctors() {
		return doctorRepository.findAll();
	}

	@Override
	public void saveDoctor(Doctor doctor) {
		this.doctorRepository.save(doctor);
	}

	@Override
	public Doctor getDoctorById(Long id) {
		Optional<Doctor> optional = doctorRepository.findById(id);
		Doctor doctor = null;
		if (optional.isPresent()) {
			doctor = optional.get();
		} else {
			throw new RuntimeException("Doctor not Found for id :: " + id);
		}
		return doctor;
	}

	@Override
	public void deleteDoctorById(Long id) {
		this.doctorRepository.deleteById(id);

	}
	
	@Override
	public Doctor getDoctorByEmail(String email) {
		Optional<Doctor> optional = doctorRepository.findByEmail(email);
		Doctor doctor = null;
		if (optional.isPresent()) {
			doctor = optional.get();
		} else {
			throw new RuntimeException("Doctor not Found for id :: " + email);
		}
		return doctor;
	}
}

