package javapro.doctor_appointment.service;

import java.util.List;

import javapro.doctor_appointment.entity.Appointment;

public interface AppointmentService {
	public List<Appointment> getAllAppointments();

	public void saveAppointment(Appointment doctor);

	public Appointment getAppointmentById(Long id);
	
	public List<Appointment> getAppointmentsByPatientId(Long id);
	
	public List<Appointment> getAppointmentsByDoctorId(Long id);

	public void deleteAppointmentById(Long id);

	public List<Appointment> getAppointmentsByPatientEmail(String email);

}