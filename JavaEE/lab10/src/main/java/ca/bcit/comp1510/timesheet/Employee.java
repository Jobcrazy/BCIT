package ca.bcit.comp1510.timesheet;

/**
 * Simple Employee placeholder for timesheet.
 * @author blink
 * @version 1.0
 */
public class Employee {
    /** employee number. */
    private String empNum;
    
    /** first name. */
    private String firstName;
    
    /** Last name. */
    private String lastName;
    
    /** Employee address. */
    private Address address;

    /**
     * creates empty employee to be initialized via setters.
     */
    public Employee() {
        
    }
    
    /**
     * Convenience constructor.
     * @param empNum employee number
     * @param firstName first name
     * @param lastName last name
     * @param address address
     */
    public Employee(String empNum, String firstName, String lastName, Address address) {
        this.empNum = empNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
    
    /**
     * Getter for employee number.
     * @return the empNum
     */
    public String getEmpNum() {
        return empNum;
    }

    /**
     * Setter for employee number.
     * @param empNum the empNum to set
     */
    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    /**
     * Getter for employee first name.
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for employee first name.
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for employee last name.
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for employee last name.
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for Address.
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Setter for Address.
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

}
