package javapro.doctor_appointment.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javapro.doctor_appointment.entity.Patient;
import javapro.doctor_appointment.repository.PatientRepository;


@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	private PatientRepository patientRepository;

	@Override
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	public void savePatient(Patient patient) {
		this.patientRepository.save(patient);
	}

	@Override
	public Patient getPatientById(Long id) {
		Optional<Patient> optional = patientRepository.findById(id);
		Patient patient = null;
		if (optional.isPresent()) {
			patient = optional.get();
		} else {
			throw new RuntimeException("Patient not Found for id :: " + id);
		}
		return patient;
	}
	
	@Override
	public Patient getPatientByEmail(String email) {
		Optional<Patient> optional = patientRepository.findByEmail(email);
		Patient patient = null;
		if (optional.isPresent()) {
			patient = optional.get();
		} else {
			throw new RuntimeException("Patient not Found for id :: " + email);
		}
		return patient;
	}

	@Override
	public void deletePatientById(Long id) {
		this.patientRepository.deleteById(id);

	}
}
