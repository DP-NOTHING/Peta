package javapro.doctor_appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import javapro.doctor_appointment.entity.Appointment;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByPatientId(Long patientId);

	List<Appointment> findByDoctorId(Long patientId);

	List<Appointment> findByPatientEmail(String email);
}
