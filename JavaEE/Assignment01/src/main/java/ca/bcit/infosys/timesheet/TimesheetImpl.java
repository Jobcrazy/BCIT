package ca.bcit.infosys.timesheet;

import ca.bcit.infosys.employee.Employee;
import java.time.LocalDate;

/**
 * subclass of Timesheet.
 *
 * @author Hang Liu & Joon Kim
 * @version 1.1
 */

public class TimesheetImpl extends Timesheet{
    /**
     * The constructor of TimesheetImpl
     */
    public TimesheetImpl(Employee employee, LocalDate endDate){
        super(employee, endDate);
    }

    /**
     * Add an empty row to to the end of the timesheet details.
     */
    @Override
    public void addRow() {
        getDetails().add(new TimesheetRowImpl());
    }

    /**
    * Update the work hours array
    */
    public void updateRows(){
        for (TimesheetRow tsr: getDetails()){
            TimesheetRowImpl tsri = (TimesheetRowImpl) tsr;
            float []hours = tsri.getHours();
            for (int n = 0; n < hours.length; ++n){
                tsri.setHour(n, hours[n]);
            }
        }
    }
}
