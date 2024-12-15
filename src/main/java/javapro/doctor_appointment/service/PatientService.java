package javapro.doctor_appointment.service;

import java.util.List;

import javapro.doctor_appointment.entity.Patient;

public interface PatientService {
	public List<Patient> getAllPatients();

	public void savePatient(Patient patient);

	public Patient getPatientById(Long id);

	public void deletePatientById(Long id);

	public Patient getPatientByEmail(String email);

}

