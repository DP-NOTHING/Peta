package javapro.doctor_appointment.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate paymentDate;
    private int paymentAmount;
    private boolean paymentStatus;
 
    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(Long id, LocalDate paymentDate, int paymentAmount, boolean paymentStatus, Appointment appointment) {
		super();
		this.id = id;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
		this.paymentStatus = paymentStatus;
		this.appointment = appointment;
	}
 
    
}
