package javapro.doctor_appointment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javapro.doctor_appointment.entity.Appointment;
import javapro.doctor_appointment.repository.AppointmentRepository;


@Service
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}
	
	@Override
	public List<Appointment> getAppointmentsByPatientId(Long patientId) {
	    return appointmentRepository.findByPatientId(patientId);
	}
	
	@Override
	public List<Appointment> getAppointmentsByPatientEmail(String email) {
	    return appointmentRepository.findByPatientEmail(email);
	}
	
	@Override
	public List<Appointment> getAppointmentsByDoctorId(Long patientId) {
	    return appointmentRepository.findByDoctorId(patientId);
	}

	@Override
	public void saveAppointment(Appointment appointment) {
		this.appointmentRepository.save(appointment);
	}

	@Override
	public Appointment getAppointmentById(Long id) {
		Optional<Appointment> optional = appointmentRepository.findById(id);
		Appointment appointment = null;
		if (optional.isPresent()) {
			appointment = optional.get();
		} else {
			throw new RuntimeException("Appointment not Found for id :: " + id);
		}
		return appointment;
	}

	@Override
	public void deleteAppointmentById(Long id) {
		this.appointmentRepository.deleteById(id);

	}
}

