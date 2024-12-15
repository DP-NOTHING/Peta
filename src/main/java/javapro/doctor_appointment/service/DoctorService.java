package javapro.doctor_appointment.service;

import java.util.List;

import javapro.doctor_appointment.entity.Doctor;

public interface DoctorService {
	public List<Doctor> getAllDoctors();

	public void saveDoctor(Doctor doctor);

	public Doctor getDoctorById(Long id);

	public void deleteDoctorById(Long id);

	public Doctor getDoctorByEmail(String email);

}