package javapro.doctor_appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import javapro.doctor_appointment.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	// add any additional query methods here if required
}

