package ca.bcit.infosys.access;

import ca.bcit.infosys.model.employee.EmployeeModel;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Handle CRUD actions for EmployeeModel class.
 *
 * @author Hang Liu
 * @version 1.0
 */

@Dependent
@Stateless
public class EmployeeManager implements Serializable {
    @PersistenceContext(unitName = "timesheet-jpa")
    EntityManager em;

    /**
     * Persist Employee record into database. id must be unique.
     *
     * @param employee the record to be persisted.
     */
    public void persist(EmployeeModel employee) {
        em.persist(employee);
    }

    /**
     * Return Employee table as array list of Employee.
     *
     * @return Arraylist of all records in Employee table
     */
    public ArrayList<EmployeeModel> getAll() {
        TypedQuery<EmployeeModel> query = em.createQuery(
                "SELECT e from EmployeeModel e",
                EmployeeModel.class);
        return new ArrayList<>(query.getResultList());
    }

    /**
     * Find Employee record from database.
     *
     * @param userName the username for the employee.
     * @return the Employee record list with given username.
     */
    public ArrayList<EmployeeModel> find(String userName) {
        TypedQuery<EmployeeModel> query = em.createQuery(
                "SELECT e from EmployeeModel e WHERE e.username = :username",
                EmployeeModel.class);
        query.setParameter("username", userName);
        return new ArrayList<>(query.getResultList());
    }

    /**
     * merge employee record fields into existing database record.
     *
     * @param employee the record to be merged.
     */
    public void merge(EmployeeModel employee) {
        em.merge(employee);
    }

    /**
     * Remove employee from database.
     *
     * @param employee record to be removed from database
     */
    public void remove(EmployeeModel employee) {
        employee = em.find(EmployeeModel.class, employee.getId());
        em.remove(employee);
    }
}
