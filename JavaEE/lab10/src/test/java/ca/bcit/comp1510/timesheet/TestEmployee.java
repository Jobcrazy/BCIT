package ca.bcit.comp1510.timesheet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * test Employee
 * class/method/line are 100% covered
 *
 * @author Hang Liu
 */
class TestEmployee {
    private final Address address = new Address("D", "E", "F", "G");
    private final Employee anEmployee = new Employee("A", "B", "C", address);

    @Test
    void TestCreate() {
        Employee e = new Employee();
        assertNull(e.getEmpNum());
        assertNull(e.getAddress());
        assertNull(e.getFirstName());
        assertNull(e.getLastName());

        Employee testEmployee = new Employee("A", "B", "C", address);

        assertEquals(testEmployee.getEmpNum(), "A");
        assertEquals(testEmployee.getFirstName(), "B");
        assertEquals(testEmployee.getLastName(), "C");
        assertSame(testEmployee.getAddress(), address);
    }

    @Test
    void TestGetEmpNum() {
        assertEquals(anEmployee.getEmpNum(), "A");
    }

    @Test
    void TestSetEmpNum() {
        anEmployee.setEmpNum("123");
        assertEquals(anEmployee.getEmpNum(), "123");
    }

    @Test
    void TestGetFirstName() {
        assertEquals(anEmployee.getFirstName(), "B");
    }

    @Test
    void TestSetFirstName() {
        anEmployee.setFirstName("123");
        assertEquals(anEmployee.getFirstName(), "123");
    }

    @Test
    void TestGetLastName() {
        assertEquals(anEmployee.getLastName(), "C");
    }

    @Test
    void TestSetLastName() {
        anEmployee.setLastName("123");
        assertEquals(anEmployee.getLastName(), "123");
    }

    @Test
    void TestGetAddress() {
        assertSame(anEmployee.getAddress(), address);
    }

    @Test
    void TestSetAddress() {
        Address newAddress = new Address("X", "Y", "Z", "G");
        anEmployee.setAddress(newAddress);
        assertSame(anEmployee.getAddress(), newAddress);
    }
}