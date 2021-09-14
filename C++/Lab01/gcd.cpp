//
// Created by Hang Liu (A01173804) on 2021/9/7.
//

#include "gcd.hpp"

int gcd(int a, int b) {
    int r = a;
    if (a < b) {
        a = b;
        b = r;
    }

    while (r) {
        r = a % b;
        a = b;
        b = r;
    }

    return a;
}