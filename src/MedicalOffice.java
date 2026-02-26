/**
 * Author: Kelvin Quaicoe
 * February 12, 2026
 * COMP167 - Section 004
 *
 * This class models a medical practice office. It maintains
 * collections of doctors and patients, supports file input/output,
 * and allows appointment information to be searched and displayed.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class MedicalOffice {

    // Instance variables for the MedicalOffice class
    private String practiceName;
    private ArrayList<String> physicians;
    private ArrayList<Patient> patients;

    // No-argument constructor initializes empty lists and blank practice name
    public MedicalOffice() {
        this.physicians = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.practiceName = "";
    }

    // Constructor that sets the practice name and initializes lists
    public MedicalOffice(String practiceName) {
        this.physicians = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.practiceName = practiceName;
    }

    /*
     Accessor and Mutator Methods
    */

    // Practice name access methods
    public String getPracticeName() { return practiceName; }
    public void setPracticeName(String practiceName) { this.practiceName = practiceName; }

    // --------------------------------------------------------
    // Methods to manage the physician list
    // --------------------------------------------------------

    public int getPhysicianSize() {
        return physicians.size();
    }

    public String getPhysician(int index) {
        if (index >= 0 && index < physicians.size()) {
            return physicians.get(index);
        }
        return null;
    }

    public void setPhysician(int index, String item) {
        if (index >= 0 && index < physicians.size()) {
            physicians.set(index, item);
        }
    }

    public void addPhysician(String item) {
        physicians.add(item);
    }

    public String removePhysician(int index) {
        if (index >= 0 && index < physicians.size()) {
            return physicians.remove(index);
        }
        return null;
    }

    // --------------------------------------------------------
    // Methods to manage the patient list
    // --------------------------------------------------------

    public int getPatientSize() {
        return patients.size();
    }

    public Patient getPatient(int index) {
        if (index >= 0 && index < patients.size()) {
            return patients.get(index);
        }
        return null;
    }

    public void setPatient(int index, Patient item) {
        if (index >= 0 && index < patients.size()) {
            patients.set(index, item);
        }
    }

    public void addPatient(Patient item) {
        patients.add(item);
    }

    public Patient removePatient(int index) {
        if (index >= 0 && index < patients.size()) {
            return patients.remove(index);
        }
        return null;
    }

    // --------------------------------------------------------
    // Level 4 Requirement: Sorting functionality
    // --------------------------------------------------------

    public void sortData() {
        sortPhysicians();
        sortPatients();
    }

    private void sortPhysicians() {
        int n = physicians.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                // Compare physician names alphabetically
                if (physicians.get(j).compareTo(physicians.get(minIdx)) < 0) {
                    minIdx = j;
                }
            }
            // Exchange positions of elements
            String temp = physicians.get(minIdx);
            physicians.set(minIdx, physicians.get(i));
            physicians.set(i, temp);
        }
    }

    private void sortPatients() {
        int n = patients.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                Patient p1 = patients.get(j);
                Patient p2 = patients.get(minIdx);

                // Compare patients by last name first
                int res = p1.getLastName().compareTo(p2.getLastName());

                // If last names match, compare first names
                if (res == 0) {
                    res = p1.getFirstName().compareTo(p2.getFirstName());
                }

                if (res < 0) {
                    minIdx = j;
                }
            }

            // Swap the patient objects
            if (minIdx != i) {
                Patient temp = patients.get(minIdx);
                patients.set(minIdx, patients.get(i));
                patients.set(i, temp);
            }
        }
    }

    // --------------------------------------------------------
    // File input and output operations
    // --------------------------------------------------------

    /**
     * Loads medical office information from the specified file.
     * @param infileName path or name of the input file
     */
    public void readMedicalOfficeData(String infileName) {
        File file = new File(infileName);
        Scanner sc = null;

        try {
            sc = new Scanner(file);

            // Read the practice name from the first line
            if (sc.hasNextLine()) {
                this.practiceName = sc.nextLine().trim();
            }

            // Read total number of physicians and store them
            if (sc.hasNextInt()) {
                int numPhysicians = sc.nextInt();
                if (sc.hasNextLine()) sc.nextLine(); // clear remaining newline character

                for (int i = 0; i < numPhysicians; i++) {
                    String docName = sc.nextLine().trim();
                    this.addPhysician(docName);
                }
            }

            // Read total number of patients and process each one
            if (sc.hasNextInt()) {
                int numPatients = sc.nextInt();
                if (sc.hasNextLine()) sc.nextLine(); // clear remaining newline character

                for (int i = 0; i < numPatients; i++) {
                    readPatient(sc);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: Input file not found: " + infileName);
        } finally {
            if (sc != null) sc.close();
        }
    }

    /**
     * Reads one patient's information and all associated
     * appointment records from the file.
     * @param sc Scanner currently reading the file
     */
    private void readPatient(Scanner sc) {
        try {
            // Extract patient basic information
            int id = Integer.parseInt(sc.nextLine().trim());
            String fName = sc.nextLine().trim();
            String lName = sc.nextLine().trim();
            String genderStr = sc.nextLine().trim();
            char gender = (genderStr.length() > 0) ? genderStr.charAt(0) : ' ';
            String dob = sc.nextLine().trim();
            String primDoc = sc.nextLine().trim();

            Patient p = new Patient(id, fName, lName, gender, dob, primDoc);

            // Retrieve appointment count and details
            if (sc.hasNextLine()) {
                String numApptsLine = sc.nextLine().trim();
                int numAppts = Integer.parseInt(numApptsLine);

                for (int i = 0; i < numAppts; i++) {
                    String apptLine = sc.nextLine();

                    // Appointment data is stored on one line separated by '*'
                    String[] parts = apptLine.split("\\*", -1);

                    if (parts.length >= 10) {
                        Appointment a = new Appointment();
                        a.setPatientID(Integer.parseInt(parts[0].trim()));
                        a.setApptDate(parts[1].trim());
                        a.setPhysician(parts[2].trim());
                        a.setHeight(Double.parseDouble(parts[3].trim()));
                        a.setWeight(Double.parseDouble(parts[4].trim()));
                        a.setTemperature(Double.parseDouble(parts[5].trim()));
                        a.setPulse(Integer.parseInt(parts[6].trim()));
                        a.setBpSystolic(Integer.parseInt(parts[7].trim()));
                        a.setBpDiastolic(Integer.parseInt(parts[8].trim()));
                        a.setNotes(parts[9].trim());

                        p.addAppointment(a);
                    }
                }
            }

            this.addPatient(p);

        } catch (Exception e) {
            System.out.println("Error parsing patient data: " + e.getMessage());
        }
    }

    /**
     * Writes all stored medical office data to a file
     * following the required structured format.
     * @param outfileName path or name of the output file
     */
    public void saveMedicalOfficeData(String outfileName) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(outfileName);

            // Output practice name
            pw.println(practiceName);

            // Output physician list
            pw.println(getPhysicianSize());
            for (String doc : physicians) {
                pw.println(doc);
            }

            // Output patient records
            pw.println(getPatientSize());
            for (Patient p : patients) {

                pw.println(p.getPatientID());
                pw.println(p.getFirstName());
                pw.println(p.getLastName());
                pw.println(p.getGender());
                pw.println(p.getDateOfBirth());
                pw.println(p.getPrimaryPhysician());

                // Write appointment total
                pw.println(p.getAppointmentSize());

                // Write each appointment record
                for (int i = 0; i < p.getAppointmentSize(); i++) {
                    Appointment a = p.getAppointment(i);
                    pw.println(a.toString());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file: " + outfileName);
        } finally {
            if (pw != null) pw.close();
        }
    }

    /**
     * Searches for a specific patient's appointment
     * and prints the appointment details if found.
     * @param patientID unique patient identifier
     * @param apptDate date of the appointment to locate
     */
    public void displayAppointment(int patientID, String apptDate) {
        String output = "Appointment not found.";

        // Locate matching patient by ID
        for (Patient p : patients) {
            if (p.getPatientID() == patientID) {

                // Check each appointment for matching date
                for (int i = 0; i < p.getAppointmentSize(); i++) {
                    Appointment a = p.getAppointment(i);
                    if (a.getApptDate().equals(apptDate)) {
                        output = "Appointment Found:\n" +
                                "Patient: " + p.getFirstName() + " " + p.getLastName() + "\n" +
                                "Date: " + a.getApptDate() + "\n" +
                                "Physician: " + a.getPhysician() + "\n" +
                                "Notes: " + a.getNotes();
                        break; // Exit appointment loop once found
                    }
                }
            }
        }

        System.out.println(output);
    }

    /**
     * Builds and returns a formatted summary of
     * the medical office information.
     */
    @Override
    public String toString() {
        String lineSep = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();

        sb.append("Practice: ").append(practiceName).append(lineSep);
        sb.append("Physicians: ").append(physicians.size()).append(lineSep);
        for (String doc : physicians) {
            sb.append(doc).append(lineSep);
        }
        sb.append("Patients: ").append(patients.size()).append(lineSep);
        for (Patient p : patients) {
            sb.append(p.toString()).append(lineSep);
        }

        return sb.toString();
    }
}
