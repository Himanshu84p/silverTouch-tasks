import java.util.ArrayList;
import java.util.List;

class Patient {
    private String patientId;
    private String patientName;
    private int age;

    public Patient(String patientId, String patientName, int age) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getAge() {
        return age;
    }
}

class Doctor {
    private String doctorId;
    private String doctorName;
    private String specialty;

    public Doctor(String doctorId, String doctorName, String specialty) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialty = specialty;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getSpecialty() {
        return specialty;
    }
}

class Appointment {
    private Patient patient;
    private Doctor doctor;
    private String appointmentDate;

    public Appointment(Patient patient, Doctor doctor, String appointmentDate) {
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }
}

class Hospital {
    private List<Doctor> doctors;
    private List<Patient> patients;
    private List<Appointment> appointments;

    public Hospital() {
        this.doctors = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void scheduleAppointment(Patient patient, Doctor doctor, String appointmentDate) {
        Appointment appointment = new Appointment(patient, doctor, appointmentDate);
        appointments.add(appointment);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}

public class Prac72_HospitalManagementSystem {
    public static void main(String[] args) {
        // Create a hospital
        Hospital hospital = new Hospital();

        // Add doctors to the hospital
        Doctor doctor1 = new Doctor("D001", "Dr. Smith", "Cardiologist");
        Doctor doctor2 = new Doctor("D002", "Dr. Johnson", "Pediatrician");
        hospital.addDoctor(doctor1);
        hospital.addDoctor(doctor2);

        // Add patients to the hospital
        Patient patient1 = new Patient("P001", "John Doe", 35);
        Patient patient2 = new Patient("P002", "Jane Doe", 28);
        hospital.addPatient(patient1);
        hospital.addPatient(patient2);

        // Schedule appointments
        hospital.scheduleAppointment(patient1, doctor1, "2024-01-20");
        hospital.scheduleAppointment(patient2, doctor2, "2024-01-21");

        // Display appointments
        List<Appointment> appointments = hospital.getAppointments();
        for (Appointment appointment : appointments) {
            System.out.println("Appointment Date: " + appointment.getAppointmentDate());
            System.out.println("Patient: " + appointment.getPatient().getPatientName());
            System.out.println("Doctor: " + appointment.getDoctor().getDoctorName());
            System.out.println("Specialty: " + appointment.getDoctor().getSpecialty());
            System.out.println("--------");
        }
    }
}
