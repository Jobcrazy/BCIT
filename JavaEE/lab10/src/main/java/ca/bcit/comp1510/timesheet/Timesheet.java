package ca.bcit.comp1510.timesheet;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * Timesheet for an employee for a given week.
 * @author blink
 * @version 1.0
 */
public class Timesheet {
    
    /** owner of timesheet.*/
    private Employee employee;
    
    /** Friday of end of week of timesheet.*/
    private LocalDate endDate;
    
    /** List of timesheet rows.*/
    private List<TimesheetRow> details = new ArrayList<TimesheetRow>();

    /**
     * Create empty timesheet.
     */
    public Timesheet() {
    }
    
    /**
     * Creates new timesheet with no rows.  Date is adjusted to Friday.
     * @param employee owner of timesheet
     * @param endDate date in timesheet week
     */
    public Timesheet(Employee employee, LocalDate endDate) {
        this.employee = employee;
        this.endDate = LocalDate.now().
                with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
    }
    
    /**
     * Getter for employee.
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Setter for Employee.
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Getter for end date of timesheet (Friday).
     * @return the endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Setter for end date of timesheet (Friday).
     * @param endDate the endDate to set
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Getter for details.
     * @return the details
     */
    public List<TimesheetRow> getDetails() {
        return details;
    }

    /**
     * Swetter for details.
     * @param details the details to set
     */
    public void setDetails(List<TimesheetRow> details) {
        this.details = details;
    }
    
    /**
     * Add a new timesheet row to the end of the list of rows.
     * @param tsr timesheet row to be added
     */
    public void addTimesheetRow(TimesheetRow tsr) {
        details.add(tsr);
    }
    
    /**
     * Return timesheet formated as printable string.
     * @return the string
     */
    public String toString() {
        String result = employee.getEmpNum() + " " +endDate;
        for (TimesheetRow tsr : details) {
            result += "\n" + tsr;
        }
        return result;
    }
    
    /**
     * Print a proof of concept timesheet with three timesheet rows.
     * @param a unused
     */
    /*
    public static void main(String[] a) {
        final float[] row1 = {0.0F, 0.0F, 3.0F, 3.1F, 3.2F, 3.3F, 3.4F};
        final float[] row2 = {0.0F, 0.0F, 3.9F, 3.8F, 3.7F, 3.6F, 3.5F};
        final float[] row3 = {24.0F, 23.9F, 24.0F, 24.0F, 24.0F, 24.0F, 24.0F};
        final float clunk = 22.3F;
        
        final TimesheetRow tsr1 = new TimesheetRow(1234, "A1200");
        tsr1.setHoursArray(row1);
        tsr1.setHour(TimesheetRow.SAT, clunk);
        final TimesheetRow tsr2 = new TimesheetRow(1234, "A1230");
        tsr2.setHoursArray(row2);
        tsr2.setHour(TimesheetRow.WED, clunk);
        final TimesheetRow tsr3 = new TimesheetRow(10, "SICK");
        tsr3.setHoursArray(row3);
        tsr3.setHour(TimesheetRow.FRI, clunk);
                
        final Timesheet t = new Timesheet(
                new Employee("331", "Fred", "Bloggs", null),
                LocalDate.now());
        t.addTimesheetRow(tsr1);
        t.addTimesheetRow(tsr2);
        t.addTimesheetRow(tsr3);
        System.out.println(t);
        
    }
    */

}
