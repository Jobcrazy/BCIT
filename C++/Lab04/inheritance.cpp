//Name: Hang Liu
//Student# : A01173804

#include <iostream>
#include "Animal.hpp"
#include "Bird.hpp"
#include "Canine.hpp"

int main() {
    /**
     * For all overloaded operator <<, all calls to << will be the friend
     * function of Animal, because we use the Animal pointer here, and a
     * friend function of a class cannot be overloaded by its child class.
     */

    /** Create a new animal with the default constructor. */
    Animal *pA = new (std::nothrow) Animal();
    if(!pA){
        std::cout << "Failed to allocate memory" << std::endl;
        exit(1);
    }
    std::cout << std::endl;

    /**
     * Create a new bird with the default constructor, It will call
     * the default constructor of Bird's parent class Animal first.
     * Bird inherits all data from the class Animal except the height.
     */
    Animal *pB = new (std::nothrow) Bird();
    if(!pB){
        delete pA;
        std::cout << "Failed to allocate memory" << std::endl;
        exit(1);
    }
    std::cout << std::endl;

    /**
     * Create a new canine with the 3-parameters constructor. it will
     * call the 3-parameters constructor of Canine's parent class Animal
     * first. Canine inherits all data from the class Animal.
     */
    Animal *pC = new (std::nothrow) Canine(1, -1, -1);
    if(!pC){
        delete pA;
        delete pB;
        std::cout << "Failed to allocate memory" << std::endl;
        exit(1);
    }
    std::cout << std::endl;

    /** Animal invoke its own move() to change the location */
    std::cout << *pA << std::endl;
    pA->move(1, 1);
    std::cout << *pA << std::endl << std::endl;

    /**
     * Bird invoke its overridden move() to change the location.
     * In side the overridden move() it invokes the move() inherited
     * from the parent class Animal.
     */
    std::cout << *pB << std::endl;
    pB->move(1, 1, 1);
    std::cout << *pB << std::endl << std::endl;

    /** Hunt before move */
    auto pCanine = dynamic_cast<Canine *>(pC);
    if (pCanine) {
        std::cout << "Hunt before move" << std::endl;
        pCanine->hunt(*pA);
        std::cout << *pA << std::endl;
        std::cout << "Hunt failed." << std::endl << std::endl;
    }

    /**
     * Canine invoke its overridden move() to change the location.
     * In side the overridden move() it invokes the move() inherited
     * from the parent class Animal.
     */
    std::cout << *pC << std::endl;
    pC->move(1, 0);
    std::cout << *pC << std::endl << std::endl;

    /** Hunt after move */
    if (pCanine) {
        std::cout << "Hunt after move" << std::endl;
        pCanine->hunt(*pA);
        std::cout << *pA << std::endl;
        std::cout << "Hunt succeed." << std::endl << std::endl;
    }

    /**
     * For the following sleep functions, each of the objects invoke
     * its own sleep function. As Bird class and Canine class have
     * overridden sleep function of their parent class Animal.
     */
    pA->sleep();
    pB->sleep();
    pC->sleep();
    std::cout << std::endl;

    /**
     * For the following eat functions, each of the objects invoke
     * its own eat function. As Bird class and Canine class have
     * overridden eat function of their parent class Animal.
     */
    pA->eat();
    pB->eat();
    pC->eat();
    std::cout << std::endl;

    /** Animal is not a derived class, so it calls its own destructor. */
    delete pA;
    std::cout << std::endl;

    /** Bird derives from Animal, so it calls its own destructor first then
     * the destructor of its parent. */
    delete pB;
    std::cout << std::endl;

    /** Bird derives from Animal, so it calls its own destructor first then
     * the destructor of its parent. */
    delete pC;
    std::cout << std::endl;

    return 0;
}
