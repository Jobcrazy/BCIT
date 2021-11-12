//
// Created by Win7x64 on 2021/11/10.
//

#pragma once

#include <vector>
#include "Patient.hpp"

class Heap {
private:
    typedef size_t index;
    std::vector<Patient> heap;
    const std::string DEFAULT_NAME = "Place Holder";

public:
    // The default constructor
    Heap() { heap.emplace_back(DEFAULT_NAME, 0); };

    // A constructor
    // PRE: patients is a vector of Patient instances
    // POST: all patients have been added to the heap
    explicit Heap(const std::vector<Patient> &patients);

    // Add a new patient to the heap
    // PRE: patient is a instance of Patient class
    // POST: patient have been added to the heap
    void push(const Patient &patient);

    // Remove the patient with greatest priority from the heap
    // POST: the patient with greatest priority has been removed
    // RETURN: the patient with greatest priority
    Patient pop();

    // Get the number of all patients in the heap
    // POST: nothing has been changed
    // RETURN: the number of all patients in the heap
    index size() const;

    // Determine are there any patient in the heap
    // POST: nothing has been changed
    // RETURN: true if there are patients in the heap
    bool is_empty() const;

    // Remove all patients from the heap
    // POST: all patients have been removed
    void clear();

    // Print all patients in the heap
    // POST: nothing has been changed
    void print_all() const;

    // The destructor
    ~Heap() { clear(); }

private:
    // Determine which element is with the greatest priority number
    // PRE: parent is the parent node
    // PRE: left is the left child node
    // PRE: right is the right child node
    // POST: nothing has been changed
    // RETURN: the index of the node that with the greatest priority number
    size_t max(index parent, index left, index right);

    // Rebuild the heap
    // POST: the vector has been sorted as a heap
    void heapify();
};
