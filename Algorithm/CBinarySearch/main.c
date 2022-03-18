#include <stdio.h>

int binary_search(
        const int *array,
        int size,
        int key) {
    int left = 0, right = size - 1, mid = right / 2;

    while (size > 0) {
        if (array[mid] == key) {
            return mid;
        } else if (array[mid] > key) {
            right = mid - 1;
            size = right - left + 1;
        } else {
            left = mid + 1;
            size = right - mid;
        }
        mid = (left + right) / 2;
    }

    return -1;
}

int main() {
    int array[5] = {0, 10, 50, 55, 90};
    printf("%d\n", binary_search(
            array, sizeof(array) / sizeof(int), 10));
    return 0;
}