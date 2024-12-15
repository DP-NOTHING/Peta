
package javapro.doctor_appointment.entity;

import java.time.*;
import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime appointmentDateTime;
    private String appointmentStatus ;
 
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
 
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
 
    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private Payment payment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getAppointmentDateTime() {
		return appointmentDateTime;
	}

	public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	

	public Appointment(Long id, LocalDateTime appointmentDateTime, String appointmentStatus, Patient patient,
			Doctor doctor) {
		super();
		this.id = id;
		this.appointmentDateTime = appointmentDateTime;
		this.appointmentStatus = appointmentStatus;
		this.patient = patient;
		this.doctor = doctor;
	}

	public Appointment() {
		super();
	}    
}

