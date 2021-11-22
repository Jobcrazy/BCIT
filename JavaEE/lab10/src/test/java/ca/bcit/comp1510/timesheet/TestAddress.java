package ca.bcit.comp1510.timesheet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test Address
 * class/method/line are 100% covered
 *
 * @author Hang Liu
 */
public class TestAddress {
    Address address = new Address("A street", "A town",
            "A street", "A code");

    @Test
    void TestToString(){
        assertEquals("A street\n" +
                "A town, A street  A code", address.toString());

        address = new Address(null, null, null, null);
        assertEquals("null\n" +
                "null, null  null", address.toString());
    }
}
