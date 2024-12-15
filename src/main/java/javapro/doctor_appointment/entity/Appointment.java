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
    private boolean appointmentStatus;
 
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

	public boolean isAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(boolean appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
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

	public Appointment(Long id, LocalDateTime appointmentDateTime, boolean appointmentStatus, Patient patient,
			Doctor doctor, Payment payment) {
		super();
		this.id = id;
		this.appointmentDateTime = appointmentDateTime;
		this.appointmentStatus = appointmentStatus;
		this.patient = patient;
		this.doctor = doctor;
		this.payment = payment;
	}

	public Appointment() {
		super();
	}    
}

