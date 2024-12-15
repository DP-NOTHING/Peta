package javapro.doctor_appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import javapro.doctor_appointment.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	// add any additional query methods here if required
}

