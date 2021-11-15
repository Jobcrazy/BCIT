package ca.bcit.infosys.model.timesheet;

import ca.bcit.infosys.model.employee.EmployeeModel;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * subclass of Timesheet.
 *
 * @author Hang Liu
 * @version 1.2
 */

public class TimesheetImpl extends Timesheet {
    /**
     * The unique id of the timesheet
     */
    Integer id;

    /**
     * getter for id
     */
    public Integer getId() {
        return id;
    }

    /**
     * setter for id
     *
     * @param id the new id for the timesheet
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * The constructor of TimesheetImpl
     *
     * @param employee the employee that the timesheet belongs tp
     * @param endDate  the end date of the time sheet
     */
    public TimesheetImpl(EmployeeModel employee, LocalDate endDate) {
        super(employee, endDate);
    }

    /**
     * The constructor of TimesheetImpl
     *
     * @param timesheetModel the timesheet model instance
     * @param rows           the row for timesheetModel
     */
    public TimesheetImpl(TimesheetModel timesheetModel,
                         ArrayList<TimesheetRowModel> rows) {
        super(timesheetModel.getEmployee(), timesheetModel.getEndDate());

        for (TimesheetRowModel row : rows) {
            TimesheetRowImpl sheetRow = new TimesheetRowImpl(
                    row.getId(), row.getProjectId(), row.getWorkPackageId(),
                    row.getNotes(), row.getPackedHours());
            float[] hours = sheetRow.getHours();
            for (int n = 0; n < hours.length; ++n) {
                sheetRow.setHour(n, sheetRow.getHour(n));
            }
            addRow(sheetRow);
        }
    }

    /**
     * Add a row to the timesheet
     *
     * @param timesheetRow the timesheet row to add
     */
    public void addRow(TimesheetRowImpl timesheetRow) {
        getDetails().add(timesheetRow);
    }

    /**
     * Add an empty row to the timesheet
     */
    public void addRow() {
        getDetails().add(new TimesheetRowImpl());
    }

    /**
     * Update the work hours array
     */
    public void updateRows() {
        for (TimesheetRow tsr : getDetails()) {
            float[] hours = tsr.getHours();
            for (int n = 0; n < hours.length; ++n) {
                tsr.setHour(n, hours[n]);
            }
        }
    }
}
