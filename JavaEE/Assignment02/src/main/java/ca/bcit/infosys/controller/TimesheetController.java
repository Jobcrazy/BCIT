package ca.bcit.infosys.controller;

import ca.bcit.infosys.access.TimesheetManager;
import ca.bcit.infosys.access.TimesheetRowManager;
import ca.bcit.infosys.model.employee.EmployeeModel;
import ca.bcit.infosys.model.timesheet.*;
import org.primefaces.event.RowEditEvent;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.List;

/**
 * class to back end to access Timesheet.
 *
 * @author Hang Liu
 * @version 1.2
 */

@Named("tController")
@SessionScoped
public class TimesheetController implements TimesheetCollection, Serializable {
    /**
     * The employee manipulator
     */
    @Inject
    EmployeeController employeeController;

    /**
     * The timesheetRow manipulator
     */
    @Inject
    TimesheetRowManager timesheetRowManager;

    /**
     * The timesheet manipulator
     */
    @Inject
    TimesheetManager timesheetManager;

    /**
     * The year number of the current timesheet
     */
    private Integer currentYear;

    /**
     * The week number of the current timesheet
     */
    private Integer currentWeek;

    /**
     * The current timesheet bean to be displayed
     */
    private Timesheet currentTimesheet;

    /**
     * The current timesheet model
     */
    private TimesheetModel currentTimesheetModel;

    /**
     * The date of the current timesheet
     */
    private Date date;

    /**
     * The default constructor
     */
    public TimesheetController() {
        date = new Date();
        LocalDate lDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        currentYear = lDate.getYear();
        currentWeek = lDate.get(
                WeekFields.of(DayOfWeek.MONDAY, 1).
                        weekOfYear());
    }

    /**
     * getter for currentYear.
     *
     * @return the year number of the current timesheet.
     */
    public Integer getCurrentYear() {
        return currentYear;
    }

    /**
     * setter for currentYear.
     *
     * @param currentYear the year number of the current timesheet.
     */
    public void setCurrentYear(Integer currentYear) {
        this.currentYear = currentYear;
    }

    /**
     * Getter for date.
     *
     * @return the date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter for date.
     *
     * @param date the date of the timesheet to be shown.
     */
    public void setDate(Date date) {
        this.date = date;
    }


    /**
     * getter for currentWeek.
     *
     * @return the week number of the current timesheet.
     */
    public Integer getCurrentWeek() {
        return currentWeek;
    }

    /**
     * setter for currentWeek.
     *
     * @param currentWeek the week number of the current timesheet.
     */
    public void setCurrentWeek(Integer currentWeek) {
        this.currentWeek = currentWeek;
    }

    /**
     * No need to implement for the current project.
     * Return all time sheets of all users.
     *
     * @return a list contains all time sheets of all users.
     */
    @Override
    public List<Timesheet> getTimesheets() {
        return null;
    }

    /**
     * get all timesheets for an employee.
     *
     * @param e the employee whose timesheets are returned
     * @return all of the timesheets for an employee.
     */
    @Override
    public List<Timesheet> getTimesheets(EmployeeModel e) {
        return null;
    }

    /**
     * get current timesheet for an employee.
     *
     * @param employee the employee whose current timesheet is returned
     * @return the current timesheet for an employee.
     */
    @Override
    public Timesheet getCurrentTimesheet(EmployeeModel employee) {
        LocalDate endDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));

        List<TimesheetModel> listTimesheet = timesheetManager.find(employee, endDate);

        if (!listTimesheet.isEmpty()) {
            // If timesheet exists
            currentTimesheetModel = listTimesheet.get(0);
            currentTimesheet = new TimesheetImpl(currentTimesheetModel,
                    timesheetRowManager.find(currentTimesheetModel));
            return currentTimesheet;
        }

        // if timesheet doesn't exist
        addTimesheet();
        return createTimeSheet(employeeController.getCurrentEmployee());
    }

    /**
     * add a new timesheet for the current week.
     *
     * @return the outcome for the next page.
     */
    @Override
    public String addTimesheet() {
        LocalDate endDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));

        TimesheetModel timesheetModel = new TimesheetModel();
        timesheetModel.setEmployee(employeeController.getCurrentEmployee());
        timesheetModel.setEndDate(endDate);

        timesheetManager.persist(timesheetModel);
        return null;
    }

    /**
     * create a new time sheet for the give employee.
     *
     * @param employee the employee whose timesheets are returned
     */
    private Timesheet createTimeSheet(EmployeeModel employee) {
        LocalDate endDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));

        List<TimesheetModel> listTimesheet = timesheetManager.find(employee, endDate);
        if (listTimesheet.isEmpty()) {
            // Error occurs
            return null;
        }

        currentTimesheetModel = listTimesheet.get(0);

        for (int i = 0; i < 5; ++i) {
            addTimesheetRow();
        }

        listTimesheet = timesheetManager.find(employee, endDate);
        if (listTimesheet.isEmpty()) {
            return null;
        }

        TimesheetModel dbTimesheet = listTimesheet.get(0);
        currentTimesheet = new TimesheetImpl(dbTimesheet,
                timesheetRowManager.find(dbTimesheet));
        return currentTimesheet;
    }

    /**
     * get the timesheet for the current user.
     *
     * @return the timesheet for the current user.
     */
    public Timesheet getCurrentUserTimesheet() {
        EmployeeModel e = employeeController.getCurrentEmployee();
        return getCurrentTimesheet(e);
    }

    /**
     * The callback function for the row-edit event
     *
     * @param event the event generated by the row editing
     */
    public void onRowEdit(RowEditEvent<TimesheetRowImpl> event) {
        // Update Database
        TimesheetImpl timesheet = (TimesheetImpl) currentTimesheet;
        timesheet.updateRows();

        FacesMessage msg = new FacesMessage("Congratulations",
                "Timesheet Updated.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        TimesheetRowImpl timesheetRow = event.getObject();
        TimesheetRowModel timesheetRowModel =
                timesheetRowManager.find(timesheetRow.getId());
        timesheetRowModel.setPackedHours(timesheetRow.getPackedHours());
        timesheetRowModel.setWorkPackageId(timesheetRow.getWorkPackageId());
        timesheetRowModel.setProjectId(timesheetRow.getProjectId());
        timesheetRowManager.merge(timesheetRowModel);
    }

    /**
     * getter for currentTimesheet.
     *
     * @return the timesheet for the current week.
     */
    public Timesheet getCurrentTimesheet() {
        LocalDate endDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        if (null != currentTimesheet && currentTimesheet.getEndDate().equals(endDate)) {
            return currentTimesheet;
        }
        currentTimesheet = getCurrentTimesheet(employeeController.getCurrentEmployee());
        return currentTimesheet;
    }

    /**
     * add a row for myTimesheet.
     *
     * @return the out come of the next page.
     */
    public String addTimesheetRow() {
        TimesheetRowModel timesheetRowModel = new TimesheetRowModel();
        timesheetRowModel.setTimesheet(currentTimesheetModel);
        timesheetRowModel.setNotes("");
        timesheetRowModel.setPackedHours(0L);
        timesheetRowModel.setProjectId(0);
        timesheetRowModel.setWorkPackageId("");
        timesheetRowManager.persist(timesheetRowModel);

        currentTimesheet = getCurrentTimesheet(employeeController.getCurrentEmployee());
        ((TimesheetImpl)currentTimesheet).updateRows();

        return "sheet";
    }

    /**
     * search for timesheet for the given date.
     *
     * @return the outcome for the next page.
     */
    public String searchTimesheet() {
        LocalDate lDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        currentYear = lDate.getYear();
        currentWeek = lDate.get(
                WeekFields.of(DayOfWeek.MONDAY, 1).
                        weekOfYear());
        currentTimesheet = getCurrentTimesheet(employeeController.getCurrentEmployee());
        return "sheet";
    }
}
