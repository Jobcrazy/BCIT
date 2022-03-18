#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int *copy_array(
        const int *src,
        size_t size) {
    int *dest = malloc(sizeof(int) * size);
    if (!dest) {
        perror("Fail to malloc\n");
        exit(1);
    }

    memcpy(dest, src, sizeof(int) * size);
    return dest;
}

void merge(
        int *dest_array,
        int *first_array,
        size_t first_size,
        int *second_array,
        size_t second_size) {
    size_t first_index = 0, second_index = 0, array_index = 0;

    while (first_index < first_size && second_index < second_size) {
        if (first_array[first_index] < second_array[second_index]) {
            dest_array[array_index] = first_array[first_index];
            first_index++;
        } else {
            dest_array[array_index] = second_array[second_index];
            second_index++;
        }
        array_index++;
    }

    if (first_index == first_size) {
        memcpy(&dest_array[array_index], second_array + second_index,
               (second_size - second_index) * sizeof(int));
    } else {
        memcpy(&dest_array[array_index], first_array + first_index,
               (first_size - first_index) * sizeof(int));
    }
}

void merge_sort(
        int *array,
        size_t size) {
    if (1 == size) {
        return;
    }

    size_t split_index = size / 2;

    // First half
    int *first_half = copy_array(array, split_index);
    merge_sort(first_half, split_index);

    // Second half
    int *second_half = copy_array(array + split_index, size - split_index);
    merge_sort(second_half, size - split_index);

    merge(array, first_half, split_index,
          second_half, size - split_index);

    // Free temp memory
    free(first_half);
    free(second_half);
}

int main() {
    int array[10] = {9, 1, 3, 7, 5, 6, 4, 2, 8, 0};
    merge_sort(array, sizeof(array) / sizeof(int));

    for (size_t i = 0; i < sizeof(array) / sizeof(int); ++i) {
        printf("%d ", array[i]);
    }

    return 0;
}
