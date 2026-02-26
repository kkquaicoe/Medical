/**
 * Student Name: YOUR FULL NAME
 * Date: February 24, 2026
 * Class / Section: COMP167-___
 * Description: Main Driver for the Medical Office application.
 */
  

public class Main {

    public static void main(String[] args) {

        // Ensure both input and output file names are provided
        if (args.length < 2) {
            System.out.println("Usage: java Main <input_file> <output_file>");
            System.out.println("Example: java Main medicalDataSmall.txt output.txt");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        System.out.println("--- Launching Medical Office Program ---");

        // Create a MedicalOffice object
        MedicalOffice office = new MedicalOffice();

        // Load data from input file
        System.out.println("Loading data from: " + inputFile);
        office.readMedicalOfficeData(inputFile);
        System.out.println("Number of Patients Loaded: " + office.getPatientSize());
        System.out.println("Number of Physicians Loaded: " + office.getPhysicianSize());

        System.out.println("\n--- Adding Additional Records ---");

        // Create and register a new patient
        Patient newPatient = new Patient(
                9999, "Aaron", "Anderson", 'M',
                "01/01/1990", "Zimmerman, Hans"
        );
        office.addPatient(newPatient);

        // Add a new physician to the office
        office.addPhysician("Zimmerman, Hans");

        // Create an appointment for the new patient
        Appointment newAppt = new Appointment(
                9999, "12/25/2026", "Zimmerman, Hans",
                72.0, 180.0, 98.6, 75, 120, 80,
                "Initial consultation."
        );

        newPatient.addAppointment(newAppt);

        System.out.println("New patient and physician successfully added.");

        // Display records before sorting
        System.out.println("\n--- Records Before Sorting ---");
        System.out.println(office.toString());

        // Sort physicians and patients
        System.out.println("\n--- Sorting Records ---");
        office.sortData();

        // Display records after sorting
        System.out.println("\n--- Records After Sorting ---");
        System.out.println(office.toString());

        // Save updated data to output file
        System.out.println("\nSaving updated data to: " + outputFile);
        office.saveMedicalOfficeData(outputFile);

        System.out.println("--- Program Completed Successfully ---");
    }
}
