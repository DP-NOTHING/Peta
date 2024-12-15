package javapro.doctor_appointment.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javapro.doctor_appointment.entity.Appointment;
import javapro.doctor_appointment.entity.Doctor;
import javapro.doctor_appointment.entity.Patient;
import javapro.doctor_appointment.service.AppointmentService;
import javapro.doctor_appointment.service.DoctorService;
import javapro.doctor_appointment.service.PatientService;


@Controller
public class AppController {
	@Autowired
	private PatientService patientService;
	
	@GetMapping("showPatient/{id}")
	public String showPatientDetails(@PathVariable("id") Long id, Model model) {
	    Patient patient = patientService.getPatientById(id);
	    List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(id);
	    model.addAttribute("patient", patient);
	    model.addAttribute("appointments", appointments);
	    return "patientdetails";
	}
	@GetMapping("/patients")
	public String viewPatientPage(Model model) {
		model.addAttribute("listPatients", patientService.getAllPatients());
		return "patientindex";
	}

	@GetMapping("/newPatientForm")
	public String showNewPatientForm(Model model) {
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		return "new_patient";
	}

	@PostMapping("/savePatient")
	public String savePatient(@ModelAttribute("patient") Patient patient) {
		patientService.savePatient(patient);
		return "redirect:/patients";
	}
	
	@PostMapping("/update-patient/{id}")
	public String updatePatient(@PathVariable(value = "id") Long id,@ModelAttribute("patient") Patient patient) {
	    // Get the patient record from the database
	    Patient existingPatient = patientService.getPatientById(id);

	    // Update the existing patient record with the new information
	    existingPatient.setName(patient.getName());
	    existingPatient.setPhoneNumber(patient.getPhoneNumber());
	    existingPatient.setEmail(patient.getEmail());
	    existingPatient.setAddress(patient.getAddress());
	    existingPatient.setDateOfBirth(patient.getDateOfBirth());
	    patientService.savePatient(existingPatient);

	    // Redirect to the patient list page
	    return "redirect:/patients";
	}


	@GetMapping("/showFormForPatientUpdate/{id}")
	public String showFormForPatientUpdate(@PathVariable(value = "id") Long id, Model model) {
		Patient patient = patientService.getPatientById(id);
		model.addAttribute("patient", patient);
		return "update_patient";
	}

	@GetMapping("/deletePatient/{id}")
	public String deletePatient(@PathVariable(value = "id") Long id, Model model) {
		this.patientService.deletePatientById(id);
		return "redirect:/patients";
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping("/doctors")
	public String viewDoctorPage(Model model) {
		model.addAttribute("listDoctors", doctorService.getAllDoctors());
		return "doctorindex";
	}
	
	@GetMapping("/showDoctor/{id}")
	public String showDoctorDetails(@PathVariable("id") Long id, Model model) {
	    Doctor doctor = doctorService.getDoctorById(id);
	    List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(id);
	    model.addAttribute("doctor", doctor);
	    model.addAttribute("appointments", appointments);
	    return "doctordetails";
	}

	@GetMapping("/newDoctorForm")
	public String showNewDoctorForm(Model model) {
		Doctor doctor = new Doctor();
		model.addAttribute("doctor", doctor);
		return "new_doctor";
	}

	@PostMapping("/saveDoctor")
	public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {
		doctorService.saveDoctor(doctor);
		return "redirect:/doctors";
	}
	
	@PostMapping("/update-doctor/{id}")
	public String updateDoctor(@PathVariable(value = "id") Long id,@ModelAttribute("doctor") Doctor doctor) {
	    // Get the doctor record from the database
	    Doctor existingDoctor = doctorService.getDoctorById(id);

	    // Update the existing doctor record with the new information
	    existingDoctor.setName(doctor.getName());
	    existingDoctor.setPhoneNumber(doctor.getPhoneNumber());
	    existingDoctor.setEmail(doctor.getEmail());
	    existingDoctor.setSpecialization(doctor.getSpecialization());
	    doctorService.saveDoctor(existingDoctor);

	    // Redirect to the doctor list page
	    return "redirect:/doctors";
	}


	@GetMapping("/showFormForDoctorUpdate/{id}")
	public String showFormForDoctorUpdate(@PathVariable(value = "id") Long id, Model model) {
		Doctor doctor = doctorService.getDoctorById(id);
		model.addAttribute("doctor", doctor);
		return "update_doctor";
	}

	@GetMapping("/deleteDoctor/{id}")
	public String deleteDoctor(@PathVariable(value = "id") Long id, Model model) {
		this.doctorService.deleteDoctorById(id);
		return "redirect:/doctors";
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------
	
	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("/appointments")
	public String viewAppointmentPage(Model model) {
		model.addAttribute("listAppointments", appointmentService.getAllAppointments());
		return "appointmentindex";
	}

	@GetMapping("/newAppointmentForm")
	public String showNewAppointmentForm(Model model) {
	    Appointment appointment = new Appointment();
	    List<Doctor> doctors = doctorService.getAllDoctors();
	    List<Patient> patients = patientService.getAllPatients();
	    model.addAttribute("appointment", appointment);
	    model.addAttribute("doctors", doctors);
	    model.addAttribute("patients", patients);
	    return "new_appointment";
	}


	@PostMapping("/saveAppointment")
	public String saveAppointment(@ModelAttribute("appointment") Appointment appointment) {
		appointmentService.saveAppointment(appointment);
		return "redirect:/appointments";
	}
	
	@PostMapping("/update-appointment/{id}")
	public String updateAppointment(@PathVariable(value = "id") Long id,@ModelAttribute("appointment") Appointment appointment) {
	    // Get the appointment record from the database
	    Appointment existingAppointment = appointmentService.getAppointmentById(id);

	    // Update the existing appointment record with the new information
	    appointmentService.saveAppointment(existingAppointment);

	    // Redirect to the appointment list page
	    return "redirect:/appointments";
	}


	@GetMapping("/showFormForAppointmentUpdate/{id}")
	public String showFormForAppointmentUpdate(@PathVariable(value = "id") Long id, Model model) {
		Appointment appointment = appointmentService.getAppointmentById(id);
		model.addAttribute("appointment", appointment);
		return "update_appointment";
	}

	@GetMapping("/deleteAppointment/{id}")
	public String deleteAppointment(@PathVariable(value = "id") Long id, Model model) {
		this.appointmentService.deleteAppointmentById(id);
		return "redirect:/appointments";
	}
	
//--------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
}
