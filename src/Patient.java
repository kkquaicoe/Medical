/**
 * Author: Kelvin Quaicoe
 * February 24, 2026
 * COMP167 - Section 4
 * This class represents a patient in the medical office system.
 * It stores personal information and maintains a list of
 * appointment records associated with the patient.
 */

import java.util.ArrayList;

public class Patient {

    // Instance variables representing patient information
    private int patientID;
    private String firstName;
    private String lastName;
    private char gender;
    private String dateOfBirth;
    private String primaryPhysician;

    // List that stores this patient's appointments
    private ArrayList<Appointment> appointments;

    // No-argument constructor initializes the appointment list
    public Patient() {
        this.appointments = new ArrayList<>();
    }

    /**
     * Constructs a Patient object with identifying and demographic details.
     * The appointment list is initialized automatically.
     */
    public Patient(int patientID, String firstName, String lastName,
                   char gender, String dateOfBirth, String primaryPhysician) {

        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.primaryPhysician = primaryPhysician;
        this.appointments = new ArrayList<>();
    }

    //Getters and Setters

    // Patient ID methods
    public int getPatientID() { return patientID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }

    // First name methods
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    // Last name methods
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    // Gender methods
    public char getGender() { return gender; }
    public void setGender(char gender) { this.gender = gender; }

    // Date of birth methods
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    // Primary physician methods
    public String getPrimaryPhysician() { return primaryPhysician; }
    public void setPrimaryPhysician(String primaryPhysician) { this.primaryPhysician = primaryPhysician; }

    //ArrayList methods

    // Returns total number of appointments for this patient
    public int getAppointmentSize() {
        return appointments.size();
    }

    // Retrieves an appointment at the specified index
    public Appointment getAppointment(int index) {
        if (index >= 0 && index < appointments.size()) {
            return appointments.get(index);
        }
        return null;
    }

    // Replaces an appointment at a specific index
    public void setAppointment(int index, Appointment item) {
        if (index >= 0 && index < appointments.size()) {
            appointments.set(index, item);
        }
    }

    // Adds a new appointment to the list
    public void addAppointment(Appointment item) {
        appointments.add(item);
    }

    // Removes and returns the appointment at the given index
    public Appointment removeAppointment(int index) {
        if (index >= 0 && index < appointments.size()) {
            return appointments.remove(index);
        }
        return null;
    }

    /**
     * Generates a formatted string containing patient details
     * followed by all associated appointments.
     */
    @Override
    public String toString() {

        String lineSep = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();

        sb.append(patientID).append(lineSep);
        sb.append(firstName == null ? "" : firstName).append(lineSep);
        sb.append(lastName == null ? "" : lastName).append(lineSep);
        sb.append(gender).append(lineSep);
        sb.append(dateOfBirth == null ? "" : dateOfBirth).append(lineSep);
        sb.append(primaryPhysician == null ? "" : primaryPhysician);

        // Append each appointment record
        for (Appointment appt : appointments) {
            sb.append(lineSep);
            sb.append(appt.toString());
        }

        return sb.toString();
    }
}

