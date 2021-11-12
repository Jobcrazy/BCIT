//
// Created by Win7x64 on 2021/11/10.
//

#include "Heap.hpp"

Heap::Heap(const std::vector<Patient> &patients) {
    heap.emplace_back(DEFAULT_NAME, 0);

    for (const Patient &patient: patients) {
        heap.push_back(patient);
    }

    heapify();
}

void Heap::push(const Patient &patient) {
    heap.push_back(patient);
    heapify();
}

Patient Heap::pop() {
    std::swap(heap[1], heap[size()]);

    Patient patient = heap[size()];
    heap.pop_back();

    heapify();

    return patient;
}

void Heap::heapify() {
    index parent = size() / 2;

    while (parent > 0) {
        index left = parent * 2;
        index right = parent * 2 + 1;
        index max_child = max(parent, left, right);

        if (parent != max_child) {
            std::swap(heap[parent], heap[max_child]);
        }

        parent--;
    }
}

Heap::index Heap::size() const {
    return heap.size() - 1;
}

void Heap::clear() {
    heap.clear();
}

bool Heap::is_empty() const {
    if (1 == heap.size()) {
        return true;
    }
    return false;
}

void Heap::print_all() const {
    if (1 == heap.size()){
        std::cout << "No patients remained." << std::endl;
        return;
    }

    std::cout << "Remained Patients:" << std::endl;
    for (const Patient &patient: heap) {
        if (0 != patient.getPriority())
            std::cout << patient << std::endl;
    }
}

size_t Heap::max(index parent, index left, index right) {
    index max_index = left;

    if (size() >= right) {
        if (heap[left] < heap[right]) {
            max_index = right;
        } else {
            max_index = left;
        }
    }

    if (size() >= max_index) {
        if (heap[max_index] < heap[parent]) {
            max_index = parent;
        }
    }

    return max_index;
}
