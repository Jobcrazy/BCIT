//Name: Hang Liu
//Student# : A01173804

#include "Hospital.hpp"
#include "Doctor.hpp"

// The entry function
int main() {
    Hospital &hospital = Hospital::getInstance();

    // Add patients
    hospital.add_patient(Patient("Jim", 2));
    hospital.add_patient(Patient("Lucy", 9));
    hospital.add_patient(Patient("Lily", 7));
    hospital.add_patient(Patient("Mary", 6));
    hospital.add_patient(Patient("Kate", 5));
    hospital.add_patient(Patient("James", 8));

    // Print all patients
    hospital.print_patients();

    // Init a doctor
    Doctor doctor("Hang Liu");

    while(Hospital::getInstance().has_patient()){
        // Treat a patient while there are any patients in the heap
        doctor.treat();
        hospital.print_patients();
    }

    return 0;
}
