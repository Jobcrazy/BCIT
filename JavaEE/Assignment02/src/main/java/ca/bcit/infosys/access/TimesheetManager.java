package ca.bcit.infosys.access;

import ca.bcit.infosys.model.employee.EmployeeModel;
import ca.bcit.infosys.model.timesheet.*;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Handle CRUD actions for TimesheetModel class.
 *
 * @author Hang Liu
 * @version 1.0
 */

@Dependent
@Stateless
public class TimesheetManager implements Serializable {
    @PersistenceContext(unitName = "timesheet-jpa")
    EntityManager em;

    /**
     * The default constructor
     */
    public TimesheetManager() {
    }

    /**
     * Find Timesheet record from database.
     *
     * @param id primary key for record.
     * @return the Timesheet record list with key = id.
     */
    public ArrayList<TimesheetModel> find(Integer id) {
        TypedQuery<TimesheetModel> query = em.createQuery(
                "SELECT t from TimesheetModel t WHERE " +
                        "t.id = :id",
                TimesheetModel.class);
        query.setParameter("id", id);

        return new ArrayList<>(query.getResultList());
    }

    /**
     * Find Timesheet record from database.
     *
     * @param employee the timesheet owner.
     * @param endDate the timesheet end date.
     * @return the Timesheet record list with given condition.
     */
    public ArrayList<TimesheetModel> find(EmployeeModel employee, LocalDate endDate) {
        TypedQuery<TimesheetModel> query = em.createQuery(
                "SELECT t from TimesheetModel t WHERE " +
                        "t.employee = :employee AND t.endDate = :endDate",
                TimesheetModel.class);
        query.setParameter("employee", employee);
        query.setParameter("endDate", endDate);

        return new ArrayList<>(query.getResultList());
    }

    /**
     * Persist Timesheet record into database. id must be unique.
     *
     * @param timesheetModel the record to be persisted.
     */
    public void persist(TimesheetModel timesheetModel) {
        em.persist(timesheetModel);
    }
}
