package javapro.doctor_appointment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import javapro.doctor_appointment.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	Optional<Patient> findByEmail(String email);
	// add any additional query methods here if required
}

