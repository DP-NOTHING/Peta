package javapro.doctor_appointment.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javapro.doctor_appointment.entity.Appointment;
import javapro.doctor_appointment.entity.Doctor;
import javapro.doctor_appointment.entity.Patient;
import javapro.doctor_appointment.service.AppointmentService;
import javapro.doctor_appointment.service.DoctorService;
import javapro.doctor_appointment.service.PatientService;


@Controller
public class AppController {
	
	@GetMapping("/")
	public String viewHome() {
		return "start";
	}
	
	@GetMapping("/adlogin")
	public String adlogin() {
		return "adlogin";
	}
	
	@GetMapping("/validate")
	public String validate(@RequestParam("email") String email, @RequestParam("password") String password,Model model) {
		if(email.equals("vatsal@gmail.com") && password.equals("123")) {
			return "redirect:/patients";
		}
		else {
			return "adlogin";
		}
	}
	
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
	
	@GetMapping("/validatePatient")
	public String validatePatient(Model model) {
		return "login_patient";
	}
	
	@GetMapping("/validateDoctor")
	public String validateDoctor(Model model) {
		return "login_doctor";
	}
	
	@GetMapping("/showPatientI")
	public String m(@RequestParam("email") String email, @RequestParam("name") String name, Model model) {
	    Patient patient = patientService.getPatientByEmail(email); 
	    if (patient != null && name.equals(patient.getName())) {
	        model.addAttribute("patient", patient);
	        return "patient_details";
	    }
	    return "redirect:/validatePatient";
	}
	
	@GetMapping("/showDoctorI")
	public String mo(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
	    Doctor doctor = doctorService.getDoctorByEmail(email); 
	    if (doctor != null && password.equals(doctor.getPassword())) {
	        model.addAttribute("doctor", doctor);
	        return "doctor_details";
	    }
	    return "redirect:/validateDoctor";
	}
	
	@PostMapping("/saveAppointmentI")
	public String saveAppointmenti(@ModelAttribute("appointment") Appointment appointment,Model model) {
		appointmentService.saveAppointment(appointment);
		Patient p = patientService.getPatientById(appointment.getPatient().getId());
		model.addAttribute("patient", p);
        return "patient_details";
	}
	
	@GetMapping("/makeAppointment")
	public String showAppointmentForm(@RequestParam("patientId") Long patientId, Model model) {
	    Patient patients = patientService.getPatientById(patientId);
	    Appointment appointment = new Appointment();
		List<Doctor> doctors = doctorService.getAllDoctors();
	    appointment.setPatient(patients);
	    model.addAttribute("appointment", appointment);
	    model.addAttribute("doctors", doctors);
	    model.addAttribute("patients", patients);
	    return "make_appointment";
	}

	@GetMapping("/newPatientForm")
	public String showNewPatientForm(Model model) {
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		return "new_patient";
	}
	
	@GetMapping("/newPatientFormI")
	public String showNewPatientFormI(Model model) {
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		return "register_patient";
	}

	@PostMapping("/savePatient")
	public String savePatient(@ModelAttribute("patient") Patient patient,Model model) {
		List<Patient> listPatient = patientService.getAllPatients();
		
		int flag=0;
		String email = patient.getEmail();
		
		for(Patient pat : listPatient)
		{
			if(email.equals(pat.getEmail()))
			{
				flag=1;
				break;
			}
		}
		
		if(flag==0) {
			patientService.savePatient(patient);
			return "redirect:/patients";
		}
		return "redirect:/newPatientForm";
	}
	
	@PostMapping("/savePatientI")
	public String savePatienti(@ModelAttribute("patient") Patient patient,Model model) {
List<Patient> listPatient = patientService.getAllPatients();
		
		int flag=0;
		String email = patient.getEmail();
		
		for(Patient pat : listPatient)
		{
			if(email.equals(pat.getEmail()))
			{
				flag=1;
				break;
			}
		}
		
		if(flag==0) {
			patientService.savePatient(patient);
			return "patient_details";
		}
		return "redirect:/newPatientFormI";
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
	    List<Patient> patients = patientService.getAllPatients();
	    model.addAttribute("doctor", doctor);
	    model.addAttribute("appointments", appointments);
	    model.addAttribute("patient", patients);
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
		model.addAttribute("listDoctors", doctorService.getAllDoctors());
	    model.addAttribute("listPatients", patientService.getAllPatients());
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
	    // Get the doctor record from the database
	    Appointment existingDoctor = appointmentService.getAppointmentById(id);

	    // Update the existing doctor record with the new information
	    existingDoctor.setAppointmentDateTime(appointment.getAppointmentDateTime());
	    existingDoctor.setPatient(appointment.getPatient());
	    existingDoctor.setAppointmentStatus(appointment.getAppointmentStatus());
	    existingDoctor.setDoctor(appointment.getDoctor());
	    appointmentService.saveAppointment(existingDoctor);

	    // Redirect to the appointment list page
	    return "redirect:/appointments";
	}
	
	@GetMapping("/showDoctor/doneAppointment/{id}")
	public String doneAppointment(@PathVariable(value = "id") Long id, @ModelAttribute("appointment") Appointment appointment) {
	    Appointment existingAppointment = appointmentService.getAppointmentById(id);
	    existingAppointment.setAppointmentStatus("Done");
	    appointmentService.saveAppointment(existingAppointment);
		return "redirect:/appointments";
	}
	
	@GetMapping("/doneAppointment/{id}/{nid}")
	public String doneAppointmentI(@PathVariable(value = "id") Long id, @PathVariable(value = "nid") Long nid, @ModelAttribute("appointment") Appointment appointment,Model model) {
	    Appointment existingAppointment = appointmentService.getAppointmentById(id);
	    existingAppointment.setAppointmentStatus("Done");
	    appointmentService.saveAppointment(existingAppointment);
	    Doctor d= doctorService.getDoctorById(nid);
		model.addAttribute("doctor", d);
        return "redirect:/";
	}
	

	@GetMapping("/showFormForAppointmentUpdate/{id}")
	public String showFormForAppointmentUpdate(@PathVariable(value = "id") Long id, Model model) {
		Appointment appointment = appointmentService.getAppointmentById(id);
		List<Doctor> doctors = doctorService.getAllDoctors();
	    List<Patient> patients = patientService.getAllPatients();
	    model.addAttribute("appointment", appointment);
	    model.addAttribute("doctors", doctors);
	    model.addAttribute("patients", patients);
	    return "update_appointment";
	}

	@GetMapping("/deleteAppointment/{id}")
	public String deleteAppointment(@PathVariable(value = "id") Long id, Model model) {
		this.appointmentService.deleteAppointmentById(id);
		return "redirect:/appointments";
	}	
	
}
