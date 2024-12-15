package javapro.doctor_appointment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import javapro.doctor_appointment.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	Optional<Doctor> findByEmail(String email);
	// add any additional query methods here if required
}

