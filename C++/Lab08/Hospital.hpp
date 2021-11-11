//
// Created by Win7x64 on 2021/11/10.
//

#pragma once

#include "Heap.hpp"

class Hospital {
private:
    // The patient heap
    Heap heapPatients;

public:
    // Get the singleton of patient heap
    // POST: nothing has been changed
    // RETURN: the singleton of patient heap
    static Hospital &getInstance() {
        static Hospital hospital;
        return hospital;
    }

    // Delete the copy constructor
    // POST: copy constructor cannot be used
    Hospital(const Hospital & hospital) = delete;

    // Delete the assign operator
    // POST: assign operator cannot be used
    Hospital &operator=(const Hospital) = delete;

    // Add a patient to the patient heap
    // PRE: patient is an instance of class Patient
    // POST: the patient has been added into the heap
    void add_patient(const Patient &patient);

    // Get a patient from the patient heap
    // POST: the patient has been removed from the heap
    // RETURN: a patient from the patient heap
    Patient get_patient();

    // Determine are there any patient in the patient heap
    // POST: nothing has been changed
    // RETURN: true if there are patients in the patient heap
    bool has_patient() const;

    // Print all patients in the patient heap
    // POST: nothing has been changed
    void print_patients() const;

private:
    // The default constructor
    Hospital() = default;
};
