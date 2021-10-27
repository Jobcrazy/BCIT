package ca.bcit.infosys.controller;

import ca.bcit.infosys.employee.Employee;
import ca.bcit.infosys.timesheet.*;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * An implement for accessing all existing Timesheets.
 *
 * @author Hang Liu & Joon Kim
 * @version 1.1
 *
 */

@Named("tController")
@SessionScoped
public class TimeSheetController implements TimesheetCollection, Serializable {
    /** The collection of all time sheets */
    private final static List<Timesheet> timesheets = new ArrayList<>();

    /** The year number of the current timesheet */
    private Integer currentYear;

    /** The week number of the current timesheet */
    private Integer currentWeek;

    /** The current displayed timesheet */
    private Timesheet myTimesheet;

    /** The date of the current timesheet */
    private Date date;

    /** The employee manipulator */
    @Inject
    EmployeeController employeeController;

    /** The default constructor */
    public TimeSheetController() {
        date = new Date();
        LocalDate lDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        currentYear = lDate.getYear();
        currentWeek = lDate.get(
                WeekFields.of(DayOfWeek.MONDAY, 1).
                        weekOfYear());
    }

    /**
     * Getter for date.
     * @return the date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Seeter for date.
     * @param date the date of the timesheet to be shown.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Getter for employee controller.
     * @return the employee controller instance.
     */
    public EmployeeController getEmployeeController() {
        return employeeController;
    }

    /**
     * timesheets getter.
     * @return all of the timesheets.
     */
    @Override
    public List<Timesheet> getTimesheets() {
        if (employeeController.isCurrentUserAdmin()) {
            return timesheets;
        }
        return null;
    }

    /**
     * get all timesheets for an employee.
     * @param e the employee whose timesheets are returned
     * @return all of the timesheets for an employee.
     */
    @Override
    public List<Timesheet> getTimesheets(Employee e) {
        if (!employeeController.isLoggedIn()) {
            return null;
        }

        List<Timesheet> result = new ArrayList<>();
        int EmpNumber = e.getEmpNumber();
        for (Timesheet timesheet : timesheets) {
            if (timesheet.getEmployee().getEmpNumber() == EmpNumber) {
                result.add(timesheet);
            }
        }
        return result;
    }

    /**
     * get current timesheet for an employee.
     * @param e the employee whose current timesheet is returned
     * @return the current timesheet for an employee.
     */
    @Override
    public Timesheet getCurrentTimesheet(Employee e) {
        if (!employeeController.isLoggedIn()) {
            return null;
        }

        List<Timesheet> allTimesheet = getTimesheets(e);
        for (Timesheet timesheet : allTimesheet) {
            if (timesheet.getEmployee().getEmpNumber() ==
                    e.getEmpNumber() && timesheet.getWeekNumber()
                    == getCurrentWeek()) {
                return timesheet;
            }
        }

        // Create new time sheet if current time sheet does not exist
        Timesheet ts = createTimeSheet(employeeController.getCurrentEmployee());
        timesheets.add(ts);
        return ts;
    }

    /**
     * add a new timesheet for the current week.
     * @return the outcome for the next page.
     */
    @Override
    public String addTimesheet() {
        Timesheet ts = createTimeSheet(employeeController.getCurrentEmployee());
        timesheets.add(ts);
        return null;
    }

    /**
     * create a new time sheet for the give employee.
     * @param e the employee whose timesheets are returned
     * @return a timesheet for the given employee.
     */
    private Timesheet createTimeSheet(Employee e) {
        LocalDate lDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        Timesheet ts = new TimesheetImpl(e, lDate);
        for (int n = 0; n < 5; ++n) {
            ts.addRow();
        }
        return ts;
    }

    /**
     * getter for currentYear.
     * @return the year number of the current timesheet.
     */
    public Integer getCurrentYear() {
        return currentYear;
    }

    /**
     * setter for currentYear.
     * @param currentYear the year number of the current timesheet.
     */
    public void setCurrentYear(Integer currentYear) {
        this.currentYear = currentYear;
    }

    /**
     * getter for currentWeek.
     * @return the week number of the current timesheet.
     */
    public Integer getCurrentWeek() {
        return currentWeek;
    }

    /**
     * setter for currentWeek.
     * @param currentWeek the week number of the current timesheet.
     */
    public void setCurrentWeek(Integer currentWeek) {
        this.currentWeek = currentWeek;
    }

    /**
     * get the timesheet for the current user.
     * @return the timesheet for the current user.
     */
    public Timesheet getCurrentUserTimesheet() {
        Employee e = employeeController.getCurrentEmployee();
        return getCurrentTimesheet(e);
    }

    /**
     * The callback function for the row-edit event
     *
     * @param event the event generated by the row editing
     */
    public void onRowEdit(RowEditEvent<TimesheetRowImpl> event) {
        // Update Database
        ((TimesheetImpl) myTimesheet).updateRows();

        FacesMessage msg = new FacesMessage("Timesheet Updated",
                "Please refresh the page.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * getter for myTimesheet.
     * @return the timesheet for the current week.
     */
    public Timesheet getMyTimesheet() {
        myTimesheet = getCurrentTimesheet(employeeController.getCurrentEmployee());
        return myTimesheet;
    }

    /**
     * add a row for myTimesheet.
     * @return the out come of the next page.
     */
    public String addTimesheetRow(){
        myTimesheet.addRow();
        return "sheet";
    }

    /**
     * search for timesheet for the given date.
     * @return the outcome for the next page.
     */
    public String searchTimesheet(){
        LocalDate lDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        currentYear = lDate.getYear();
        currentWeek = lDate.get(
                WeekFields.of(DayOfWeek.MONDAY, 1).
                        weekOfYear());
        myTimesheet = getCurrentTimesheet(employeeController.getCurrentEmployee());
        return "sheet";
    }
}
