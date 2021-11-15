package ca.bcit.infosys.controller;

import ca.bcit.infosys.employee.Credentials;
import ca.bcit.infosys.employee.Employee;
import ca.bcit.infosys.employee.EmployeeList;
import org.primefaces.event.RowEditEvent;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * class to back end to access Employees and verify login credentials.
 *
 * @author Hang Liu & Joon Kim
 * @version 1.1
 */

@SessionScoped
public class EmployeeController implements EmployeeList, Serializable {
    /**
     * The employee's id counter, it must be unique for each employee.
     */
    private static int empNumber = 1;

    /**
     * The max employee's id for the demo users.
     */
    private static int maxEmbeddedUserNum = 1;

    /**
     * The employee list.
     */
    private final static ArrayList<Employee> employees = new ArrayList<>();

    /**
     * The credential map.
     */
    private final static Map<String, String> loginCombos = new HashMap<>();

    /**
     * The current employee's http session.
     */
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
            getExternalContext().getSession(true);

    /**
     * The username and password entered by the current user.
     */
    @Inject
    private Credentials currentCredential;

    /**
     * The new password for the current user.
     */
    private String newPassword;

    /**
     * The map of employees' username and password.
     */
    ArrayList<Credentials> listCredentials = new ArrayList<>();

    /*
     * Load demo users from the configuration file.
     */
    static {
        Properties dbProperties = new Properties();
        try {
            dbProperties.load(EmployeeController.class.
                    getResourceAsStream("/default.properties"));

            empNumber = Integer.parseInt(
                    dbProperties.getProperty("admin.num"));
            String adminName = dbProperties.getProperty("admin.name");
            String adminPassword = dbProperties.getProperty("admin.password");

            Employee admin = new Employee(adminName, empNumber++, adminName);
            employees.add(admin);
            loginCombos.put(adminName, adminPassword);

            String userName = dbProperties.getProperty("user.name");
            String userPassword = dbProperties.getProperty("user.password");

            maxEmbeddedUserNum = empNumber;
            Employee user = new Employee(userName, empNumber++, userName);
            employees.add(user);
            loginCombos.put(userName, userPassword);
        } catch (Exception e) {
            System.out.println("Admin properties file failed to load.");
        }
    }

    /**
     * The default constructor, it will update the credential list for
     * future edit.
     */
    public EmployeeController() {
        updateCredentialList();
    }

    /**
     * employees getter.
     *
     * @return the ArrayList of users.
     */
    @Override
    public List<Employee> getEmployees() {
        if (isCurrentUserAdmin()) {
            return employees;
        }
        return null;
    }

    /**
     * Returns employee with a given name.
     *
     * @param name the name field of the employee
     * @return the employees.
     */
    @Override
    public Employee getEmployee(String name) {
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                return employee;
            }
        }
        return null;
    }

    /**
     * Return map of valid passwords for userNames.
     *
     * @return the Map containing the valid (userName, password) combinations.
     */
    @Override
    public Map<String, String> getLoginCombos() {
        if (!isCurrentUserAdmin()) {
            return null;
        }
        return loginCombos;
    }

    /**
     * getter for currentEmployee property.
     *
     * @return the current user.
     */
    @Override
    public Employee getCurrentEmployee() {
        Object currentEmployeeName = session.
                getAttribute("username");
        if (null == currentEmployeeName) {
            return new Employee();
        }

        return getEmployee(currentEmployeeName.toString());
    }

    /**
     * Assumes single administrator and returns the employee object
     * of that administrator.
     *
     * @return the administrator user object.
     */
    @Override
    public Employee getAdministrator() {
        if (!isLoggedIn()) {
            return null;
        }

        Properties dbProperties = new Properties();
        try {
            dbProperties.load(EmployeeController.class.
                    getResourceAsStream("/default.properties"));
        } catch (Exception e) {
            System.out.println("Admin properties file failed to load.");
            return null;
        }

        int adminNum = Integer.parseInt(
                dbProperties.getProperty("admin.num"));
        String adminName = dbProperties.getProperty("admin.name");
        String adminPassword = dbProperties.getProperty("admin.password");

        for (Employee employee : employees) {
            if (employee.getEmpNumber() == adminNum) {
                return employee;
            }
        }

        return null;
    }

    /**
     * Verifies that the loginID and password is a valid combination.
     *
     * @param credential (userName, Password) pair
     * @return true if it is, false if it is not.
     */
    @Override
    public boolean verifyUser(Credentials credential) {
        String password = loginCombos.get(credential.getUserName());
        if (null != password && password.equals(credential.getPassword())) {
            session.setAttribute("username", credential.getUserName());
            return true;
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
    public String logout(Employee employee) {
        session.invalidate();
        return "index";
    }

    /**
     * Logs the current user out of the system.
     *
     * @return a String representing the login page.
     */
    public String logout() {
        return logout(null);
    }

    /**
     * Deletes the specified user from the collection of Users.
     *
     * @param userToDelete the user to delete.
     */
    @Override
    public void deleteEmployee(Employee userToDelete) {
        if (!isCurrentUserAdmin()) {
            return;
        }
        employees.remove(userToDelete);
    }

    /**
     * Adds a new Employee to the collection of Employees.
     *
     * @param newEmployee the employee to add to the collection
     */
    @Override
    public void addEmployee(Employee newEmployee) {
        if (isCurrentUserAdmin()) {
            newEmployee.setEmpNumber(empNumber++);
            employees.add(newEmployee);
        }
    }

    /**
     * Adds a new Employee to the collection of Employees.
     *
     * @param newEmployee the employee to add to the collection
     * @param password    the employee's password to add to the collection
     */
    public void addEmployee(Employee newEmployee, String password) {
        if (isCurrentUserAdmin()) {
            newEmployee.setEmpNumber(empNumber++);
            employees.add(newEmployee);
            loginCombos.put(newEmployee.getUserName(), password);
        }
    }

    /**
     * Determine if the current user is the admin or not.
     *
     * @return true if the current user is the admin, otherwise false.
     */
    public boolean isCurrentUserAdmin() {
        return getCurrentEmployee().getEmpNumber() ==
                getAdministrator().getEmpNumber();
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
     * Return the new password string
     *
     * @return the new password
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Set the new password string
     *
     * @param newPassword the new password
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * Update the password for the current user
     */
    public void updatePassword() {
        loginCombos.put(currentCredential.getUserName(), newPassword);
    }

    /**
     * Update the credential list for future edit
     */
    private void updateCredentialList() {
        listCredentials.clear();
        for (String key : loginCombos.keySet()) {
            addCredential(key, loginCombos.get(key));
        }
    }

    /**
     * Add a new pair of credential for the new user
     *
     * @param userName the username for the new user
     * @param password the password for the new user
     */
    private void addCredential(String userName, String password) {
        Credentials newCredential = new Credentials();
        newCredential.setUserName(userName);
        newCredential.setPassword(password);
        listCredentials.add(newCredential);
    }

    /**
     * Add a new employee
     */
    public String addEmployee() {
        String username = "user" + empNumber;
        String password = "user" + empNumber;
        Employee employee = new Employee(username, empNumber, password);
        addEmployee(employee);
        loginCombos.put(username, password);
        addCredential(username, password);
        return "users";
    }

    /**
     * The callback function for the row-edit event
     *
     * @param event the event generated by the row editing
     */
    public void onRowEdit(RowEditEvent<Credentials> event) {
        FacesMessage msg = new FacesMessage("Congratulations",
                "User Updated.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        // Update Database
        Credentials credentials = event.getObject();
        loginCombos.put(credentials.getUserName(), credentials.getPassword());
    }

    /**
     * Return the credential list
     */
    public ArrayList<Credentials> getListCredentials() {
        return listCredentials;
    }

    /**
     * Set the credential list
     *
     * @param listCredentials the credential list
     */
    public void setListCredentials(ArrayList<Credentials> listCredentials) {
        this.listCredentials = listCredentials;
    }

    /**
     * Delete an employee
     *
     * @param credential the credential info for the user to be deleted
     */
    public String deleteEmployee(Credentials credential) {
        for (Employee employee : employees) {
            if (employee.getUserName().equals(credential.getUserName())) {
                if (employee.getEmpNumber() <= maxEmbeddedUserNum) {
                    FacesMessage msg = new FacesMessage("Sorry",
                            "You cannot delete built-in users.");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    return null;
                }
                employees.remove(employee);
                break;
            }
        }
        listCredentials.remove(credential);
        return "users";
    }
}
