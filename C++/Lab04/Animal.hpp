//
// Created by Win7x64 on 2021/9/29.
//

#pragma once

class Animal {
private:
    static long count;
    long id;
    int age;
    bool alive;
    double latitude;
    double longitude;

public:
    // The default constructor
    Animal();

    // The 3-parameters constructor
    // PRE: iAge is a positive integer
    // PRE: dLatitude is a double
    // PRE: dLongitude is a double
    // POST: initialize a new Animal with given info
    Animal(int iAge, double dLatitude, double dLongitude);

    // The copy constructor
    // PRE: animal is another instance of Animal
    // POST: copy all info from animal except the id
    Animal(const Animal &animal);

    // The destructor
    virtual ~Animal();

    // Move Animal to another location
    // PRE: dLatitude is a double
    // PRE: dLongitude is a double
    // POST: new location is set
    virtual void move(double dLatitude, double dLongitude);

    // A virtual 3-parameters move() for overloading purpose
    // POST: keep Animal unchanged
    virtual void move(double, double, double) {};

    // Animal sleeps
    // POST: keep Animal unchanged
    virtual void sleep() const;

    // Animal eats
    // POST: keep Animal unchanged
    virtual void eat() const;

    // Set Animal alive or dead
    // PRE: bAlive is a boolean
    // POST: alive is set as bAlive
    void setAlive(bool bAlive);

    // Get the Animal's id
    // POST: keep Animal unchanged
    // RETURN: the Animal's id
    [[nodiscard]] inline long getId() const { return id; }

    // Get the Animal's age
    // POST: keep Animal unchanged
    // RETURN: the Animal's age
    [[nodiscard]] inline int getAge() const { return age; }

    // Get if the Animal is alive or not
    // POST: keep Animal unchanged
    // RETURN: true if the animal is alive, otherwise false
    [[nodiscard]] inline bool isAlive() const { return alive; }

    // Get the Animal's latitude
    // POST: keep Animal unchanged
    // RETURN: the Animal's latitude
    [[nodiscard]] inline double getLatitude() const { return latitude; };

    // Get the Animal's longitude
    // POST: keep Animal unchanged
    // RETURN: the Animal's longitude
    [[nodiscard]] inline double getLongitude() const { return longitude; };

    // Derived classes could override this function to get its height
    // POST: keep Animal unchanged
    // RETURN: 0
    [[nodiscard]] virtual inline double getHeight() const { return 0; };

    // Overload operator <<
    friend std::ostream &operator<<(std::ostream &out, const Animal &animal);
};
