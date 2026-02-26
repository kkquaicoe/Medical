/**
 * Author: Kelvin Quaicoe
 * Date: February 24, 2026
 * Course / Section: COMP167 - Section 004
 *
 * This class defines an appointment record in the medical office system.
 * It maintains visit information including patient identification,
 * provider details, vital statistics, and additional notes.
 * Accessor methods and a formatted toString() method are provided.
 */

public class Appointment {

    // Instance Variables
    private int patientID;
    private String apptDate;
    private String physician;
    private double height;
    private double weight;
    private double temperature;
    private int pulse;
    private int bpSystolic;
    private int bpDiastolic;
    private String notes;

    // No-argument constructor
    public Appointment() {
        // Fields are initialized to default values automatically
    }

    // Constructor with parameters to initialize all fields
    public Appointment(int patientID, String apptDate, String physician,
                       double height, double weight, double temperature,
                       int pulse, int bpSystolic, int bpDiastolic, String notes) {
        this.patientID = patientID;
        this.apptDate = apptDate;
        this.physician = physician;
        this.height = height;
        this.weight = weight;
        this.temperature = temperature;
        this.pulse = pulse;
        this.bpSystolic = bpSystolic;
        this.bpDiastolic = bpDiastolic;
        this.notes = notes;
    }

    /*
     Accessor and Mutator Methods
    */

    // Patient ID
    public int getPatientID() { return patientID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }

    // Appointment Date
    public String getApptDate() { return apptDate; }
    public void setApptDate(String apptDate) { this.apptDate = apptDate; }

    // Physician Name
    public String getPhysician() { return physician; }
    public void setPhysician(String physician) { this.physician = physician; }

    // Height Measurement
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    // Weight Measurement
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    // Body Temperature
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    // Pulse Rate
    public int getPulse() { return pulse; }
    public void setPulse(int pulse) { this.pulse = pulse; }

    // Systolic Blood Pressure
    public int getBpSystolic() { return bpSystolic; }
    public void setBpSystolic(int bpSystolic) { this.bpSystolic = bpSystolic; }

    // Diastolic Blood Pressure
    public int getBpDiastolic() { return bpDiastolic; }
    public void setBpDiastolic(int bpDiastolic) { this.bpDiastolic = bpDiastolic; }

    // Appointment Notes
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    /*
     String Representation Method
    */

    @Override
    public String toString() {
        return patientID + "*" +
                (apptDate == null ? "" : apptDate) + "*" +
                (physician == null ? "" : physician) + "*" +
                height + "*" +
                weight + "*" +
                temperature + "*" +
                pulse + "*" +
                bpSystolic + "*" +
                bpDiastolic + "*" +
                (notes == null ? "" : notes);
    }
}

