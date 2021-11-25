package ca.bcit.infosys.route;

import ca.bcit.infosys.access.TimesheetManager;
import ca.bcit.infosys.access.TimesheetRowManager;
import ca.bcit.infosys.model.employee.EmployeeModel;
import ca.bcit.infosys.model.timesheet.TimesheetModel;
import ca.bcit.infosys.model.timesheet.TimesheetRowImpl;
import ca.bcit.infosys.model.timesheet.TimesheetRowModel;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * The restful api class for the timesheet
 */
@Path("/timesheet")
public class TimesheetRoute {
    /**
     * The timesheet manipulator
     */
    @Inject
    TimesheetManager timesheetManager;

    /**
     * The timesheet row manipulator
     */
    @Inject
    TimesheetRowManager timesheetRowManager;

    /**
     * The tools for response building
     */
    @Inject
    Utils utils;

    /**
     * get timesheet of given date for given token
     *
     * @param token   the token string
     * @param strDate the timesheet end date
     * @return an object contains the timesheet if the token is valid
     */
    @GET
    @Path("/token/{token}/date/{date}")
    @Produces("application/json")
    public Map<String, Object> getTimesheet(
            @PathParam("token") String token,
            @PathParam("date") String strDate) throws ParseException {
        EmployeeModel employeeModel = utils.getCurrentUser(token);
        if (null == employeeModel) {
            return utils.buildFailedResult("Not Login");
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(strDate);
        LocalDate endDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));

        ArrayList<TimesheetModel> tsList =
                timesheetManager.find(employeeModel, endDate);
        ArrayList<TimesheetRowModel> rows;
        if (0 == tsList.size()) {
            // Create a timesheet with 5 rows
            addTimesheet(employeeModel, endDate);
        }

        tsList = timesheetManager.find(employeeModel, endDate);
        rows = timesheetRowManager.find(tsList.get(0));

        ArrayList<TimesheetRowImpl> rowImpls = new ArrayList<>();
        for (TimesheetRowModel row : rows) {
            rowImpls.add(new TimesheetRowImpl(
                    row.getId(),
                    row.getProjectId(),
                    row.getWorkPackageId(),
                    row.getNotes(),
                    row.getPackedHours()
            ));
        }

        return utils.buildSuccessResult(rowImpls);
    }

    /**
     * delete a timesheet row
     *
     * @param token the token string
     * @param id    the id of the timesheet row to be deleted
     * @return a successful object if the token is valid
     */
    @DELETE
    @Path("/token/{token}/id/{id}")
    @Produces("application/json")
    public Map<String, Object> getTimesheet(
            @PathParam("token") String token,
            @PathParam("id") int id) {
        EmployeeModel employeeModel = utils.getCurrentUser(token);
        if (null == employeeModel) {
            return utils.buildFailedResult("Not Login");
        }

        timesheetRowManager.remove(timesheetRowManager.find(id));
        return utils.buildSuccessResult(null);
    }

    /**
     * add a timesheet row
     *
     * @param token   the token string
     * @param strDate the end date of the timesheet
     * @return a successful object if the token is valid
     */
    @PUT
    @Path("/token/{token}/date/{date}")
    @Produces("application/json")
    public Map<String, Object> addNewRow(
            @PathParam("token") String token,
            @PathParam("date") String strDate) throws ParseException {
        EmployeeModel employeeModel = utils.getCurrentUser(token);
        if (null == employeeModel) {
            return utils.buildFailedResult("Not Login");
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(strDate);
        LocalDate endDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));

        ArrayList<TimesheetModel> timesheetModels =
                timesheetManager.find(employeeModel, endDate);
        addTimesheetRow(timesheetModels.get(0));

        return utils.buildSuccessResult(null);
    }

    /**
     * update a timesheet row
     *
     * @param token        the token string
     * @param timesheetRow the timesheet row to be updated
     * @return a successful object if the token is valid
     */
    @POST
    @Path("/token/{token}/")
    @Consumes("application/json")
    @Produces("application/json")
    public Map<String, Object> updateRow(
            @PathParam("token") String token,
            TimesheetRowImpl timesheetRow) {
        EmployeeModel employeeModel = utils.getCurrentUser(token);
        if (null == employeeModel) {
            return utils.buildFailedResult("Not Login");
        }

        TimesheetRowModel tsr = new TimesheetRowModel();
        tsr.setId(timesheetRow.getId());
        tsr.setProjectId(timesheetRow.getProjectId());
        tsr.setWorkPackageId(timesheetRow.getWorkPackageId());
        tsr.setTimesheet(timesheetRowManager.find(timesheetRow.getId()).getTimesheet());
        tsr.setNotes(timesheetRow.getNotes());
        tsr.setPackedHours(timesheetRow.getPackedHours());
        timesheetRowManager.merge(tsr);
        return utils.buildSuccessResult(null);
    }

    /**
     * add a new timesheet
     *
     * @param employee the owner of the timesheet
     * @param endDate  the end date of the timesheet
     */
    public void addTimesheet(EmployeeModel employee, LocalDate endDate) {
        TimesheetModel timesheetModel = new TimesheetModel();
        timesheetModel.setEmployee(employee);
        timesheetModel.setEndDate(endDate);
        timesheetManager.persist(timesheetModel);

        for (int i = 0; i < 5; ++i) {
            addTimesheetRow(timesheetModel);
        }
    }

    /**
     * add a row for myTimesheet.
     */
    public void addTimesheetRow(TimesheetModel timesheetModel) {
        TimesheetRowModel timesheetRowModel = new TimesheetRowModel();
        timesheetRowModel.setTimesheet(timesheetModel);
        timesheetRowModel.setNotes("");
        timesheetRowModel.setPackedHours(0L);
        timesheetRowModel.setProjectId(0);
        timesheetRowModel.setWorkPackageId("");
        timesheetRowManager.persist(timesheetRowModel);
    }
}
