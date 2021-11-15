package ca.bcit.infosys.controller;

import ca.bcit.infosys.access.EmployeeManager;
import ca.bcit.infosys.model.employee.Credentials;
import ca.bcit.infosys.model.employee.EmployeeModel;
import ca.bcit.infosys.model.employee.EmployeeList;
import org.primefaces.event.RowEditEvent;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * class to back end to access Employees and verify login credentials.
 *
 * @author Hang Liu
 * @version 1.2
 */

@Named
@SessionScoped
public class EmployeeController implements EmployeeList, Serializable {
    /**
     * The employee manipulator
     */
    @Inject
    EmployeeManager employeeManager;

    /**
     * The employee associated to the current session
     */
    @Inject
    EmployeeModel currentEmployee;

    /**
     * The username and password entered by the current user.
     */
    @Inject
    private Credentials currentCredential;

    /**
     * The employee to be added to the database
     */
    @Inject
    EmployeeModel employeeToAdd;

    /**
     * All employees' information get from the database
     */
    private ArrayList<EmployeeModel> employeeModels;

    /**
     * The current employee's http session.
     */
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
            getExternalContext().getSession(true);

    /**
     * The default constructor, it will update the credential list for
     * future edit.
     */
    public EmployeeController() {
    }

    /**
     * The employees getter.
     *
     * @return the ArrayList of users.
     */
    @Override
    public List<EmployeeModel> getEmployees() {
        if (isCurrentUserAdmin()) {
            if (null != employeeModels) {
                return employeeModels;
            }
            employeeModels = employeeManager.getAll();
            return employeeModels;
        }
        return new ArrayList<>();
    }

    /**
     * Returns employee with a given name.
     *
     * @param name the name field of the employee
     * @return the employee model related to the given name.
     */
    @Override
    public EmployeeModel getEmployee(String name) {
        ArrayList<EmployeeModel> employees = employeeManager.find(name);
        if (1 == employees.size()) {
            return employees.get(0);
        }
        return null;
    }

    /**
     * No need to implement for the current project.
     * Return map of valid passwords for userNames.
     *
     * @return the Map containing the valid (userName, password) combinations.
     */
    @Override
    public Map<String, String> getLoginCombos() {
        return null;
    }

    /**
     * getter for currentEmployee property.
     *
     * @return the current user.
     */
    @Override
    public EmployeeModel getCurrentEmployee() {
        return currentEmployee;
    }

    /**
     * The employeeToAdd getter.
     *
     * @return the Employee to be added into the database.
     */
    public EmployeeModel getEmployeeToAdd() {
        return employeeToAdd;
    }

    /**
     * Set the employee to be added to the database.
     *
     * @param employeeToAdd the employee to be added to the database.
     */
    public void setEmployeeToAdd(EmployeeModel employeeToAdd) {
        this.employeeToAdd = employeeToAdd;
    }

    /**
     * No need to implement for the current project.
     * Assumes single administrator and returns the employee object
     * of that administrator.
     *
     * @return the administrator user object.
     */
    @Override
    public EmployeeModel getAdministrator() {
        return null;
    }

    /**
     * Verifies that the username and password is a valid combination.
     *
     * @param credential (userName, Password) pair
     * @return true if it is, false if it is not.
     */
    @Override
    public boolean verifyUser(Credentials credential) {
        ArrayList<EmployeeModel> employees =
                employeeManager.find(credential.getUserName());
        if (0 != employees.size()) {
            EmployeeModel employee = employees.get(0);
            if (employee.getPassword().equals(credential.getPassword())) {
                currentEmployee = employees.get(0);
                session.setAttribute("username", credential.getUserName());
                return true;
            }
        }
        return false;
    }

    /**
     * Logs the current user out of the system.
     *
     * @param employee the user to logout.
     * @return a String representing the login page.
     */
    @Override
    public String logout(EmployeeModel employee) {
        session.invalidate();
        return "index";
    }

    /**
     * Logs the current user out of the system.
     *
     * @return the outcome string of the login page.
     */
    public String logout() {
        return logout(null);
    }

    /**
     * Deletes the specified user from the database.
     *
     * @param employee the user to delete.
     */
    @Override
    public void deleteEmployee(EmployeeModel employee) {
        if (!isCurrentUserAdmin()) {
            return;
        }
        if (1 == employee.getAdmin()) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Sorry",
                    "You cannot delete admin");
            return;
        }
        employeeManager.remove(employee);
        employeeModels.remove(employee);
    }

    /**
     * Adds a new Employee to the database.
     *
     * @param newEmployee the employee to add to the collection
     */
    @Override
    public void addEmployee(EmployeeModel newEmployee) {
        if (!isCurrentUserAdmin()) {
            return;
        }
        employeeManager.persist(newEmployee);
    }

    /**
     * Adds a new Employee to the database and update the employee list.
     *
     * @return the outcome string of the user management page
     */
    public String addEmployee() {
        if (0 != employeeManager.find(employeeToAdd.getUsername()).size()) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Username Exists",
                    "Please choose another username");
            return null;
        }

        employeeToAdd.setAdmin(0);
        addEmployee(employeeToAdd);
        employeeModels = employeeManager.getAll();
        return "users";
    }

    /**
     * Determine if the current user is the admin or not.
     *
     * @return true if the current user is the admin, otherwise false.
     */
    public boolean isCurrentUserAdmin() {
        return 1 == currentEmployee.getAdmin();
    }

    /**
     * Return the credential of the current user.
     *
     * @return the credential of the current user.
     */
    public Credentials getCurrentCredential() {
        return currentCredential;
    }

    /**
     * Set the credential of the current user.
     *
     * @param credential the credential entered by the current user.
     */
    public void setCurrentCredential(Credentials credential) {
        this.currentCredential = credential;
    }

    /**
     * Send a message to the front end.
     *
     * @param severity the severity of the message.
     * @param summary  the summary of the message.
     * @param detail   the detail of the message.
     */
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    /**
     * Perform user login process.
     *
     * @return the sheet page outcome if login succeed, otherwise return null.
     */
    public String login() {
        if (verifyUser(currentCredential)) {
            return "sheet";
        }
        addMessage(FacesMessage.SEVERITY_ERROR, "Login Failed",
                "Please check you username and password");
        return null;
    }

    /**
     * Check if the current user has logged in.
     *
     * @return true if the current user has logged in, otherwise return false.
     */
    public boolean isLoggedIn() {
        Object loginName = session.getAttribute("username");
        return null != loginName && 0 != loginName.toString().length();
    }

    /**
     * Redirect the browser to the login page if the current user has not logged
     * in.
     */
    public void checkLogin() throws IOException {
        if (!isLoggedIn()) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
        }
    }

    /**
     * Update the password for the current user
     */
    public void updatePassword() {
        employeeManager.merge(currentEmployee);

        FacesMessage msg = new FacesMessage("Congratulations",
                "Password Updated.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * The callback function for the row-edit event
     *
     * @param event the event generated by the row editing
     */
    public void onRowEdit(RowEditEvent<EmployeeModel> event) {
        FacesMessage msg = new FacesMessage("Congratulations",
                "User Updated.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        // Update Database
        EmployeeModel employeeModel = event.getObject();
        employeeManager.merge(employeeModel);
    }
}
