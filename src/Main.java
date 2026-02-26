public class Main {
  /**
 * Student Name: YOUR FULL NAME
 * Date: February 12, 2026
 * Class / Section: COMP167-___
 * Description: Main Driver for the Medical Office application.
 */
  public class Main {
    public static void main(String[] args) {

        // Check that both input and output file names were provided
        if (args.length < 2) {
            System.out.println("Usage: java Main <input_file> <output_file>");
            System.out.println("Example: java Main medicalDataSmall.txt output.txt");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        System.out.println("--- Launching Medical Office Program ---");

        // Create an instance of the MedicalOffice class
        MedicalOffice office = new MedicalOffice();

        // Load existing data from the specified input file
        System.out.println("Loading data from: " + inputFile);
        office.readMedicalOfficeData(inputFile);
        System.out.println("Number of Patients Loaded: " + office.getPatientSize());
        System.out.println("Number of Physicians Loaded: " + office.getPhysicianSize());

        System.out.println("\n--- Inserting Additional Records ---");

        // Create and register a new patient
        Patient newPatient = new Patient(9999, "Aaron", "Anderson", 'M', "01/01/1990", "Zimmerman, Hans");
        office.addPatient(newPatient);

        // Add a physician associated with the new patient
        office.addPhysician("Zimmerman, Hans");

        // Create an appointment and attach it to the patient
        Appointment newAppt = new Appointment(
                9999, "12/25/2026", "Zimmerman, Hans",
                72.0, 180.0, 98.6, 75, 120, 80, "Initial consultation."
        );
        newPatient.addAppointment(newAppt);

        System.out.println("Patient 'Aaron Anderson' successfully added.");
        System.out.println("Physician 'Zimmerman, Hans' successfully added.");

        System.out.println("\n--- Records Before Sorting ---");
        System.out.println(office.toString());

        // Arrange the stored data in sorted order
        System.out.println("\n--- Organizing Records ---");
        office.sortData();

        // Display the sorted results
        System.out.println("\n--- Records After Sorting ---");
        System.out.println(office.toString());

        // Write the updated data to the output file
        System.out.println("\nWriting updated data to: " + outputFile);
        office.saveMedicalOfficeData(outputFile);

        System.out.println("--- Program Finished Successfully ---");
    }
}
}

