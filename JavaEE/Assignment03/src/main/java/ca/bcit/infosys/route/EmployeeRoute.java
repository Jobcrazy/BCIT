package ca.bcit.infosys.route;

import ca.bcit.infosys.access.EmployeeManager;
import ca.bcit.infosys.access.TokenManager;
import ca.bcit.infosys.model.employee.Credentials;
import ca.bcit.infosys.model.employee.EmployeeModel;
import ca.bcit.infosys.model.token.TokenModel;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * The restful api class for the employees
 */

@Path("/user")
public class EmployeeRoute {
    /**
     * The employee manipulator
     */
    @Inject
    EmployeeManager employeeManager;

    /**
     * The token manipulator
     */
    @Inject
    TokenManager tokenManager;

    /**
     * The tools for response building
     */
    @Inject
    Utils utils;

    /**
     * verify the login credentials
     *
     * @param credentials the object contains the username and password
     * @return an object contains the token if the credential is valid
     */
    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Map<String, Object> login(Credentials credentials) {
        ArrayList<EmployeeModel> employees =
                employeeManager.find(credentials.getUserName());
        if (0 != employees.size()) {
            EmployeeModel employee = employees.get(0);
            if (employee.getPassword().equals(credentials.getPassword())) {
                EmployeeModel currentEmployee = employees.get(0);

                TokenModel token = new TokenModel();
                token.setEmployee(currentEmployee);
                token.setToken(utils.encrypt2ToMD5(credentials.getUserName()));

                if (null != utils.getCurrentUser(
                        utils.encrypt2ToMD5(credentials.getUserName()))) {
                    return utils.buildSuccessResult(token);
                }

                tokenManager.persist(token);
                return utils.buildSuccessResult(token);
            }
        }

        return utils.buildFailedResult("No such user");
    }

    /**
     * verify the token
     *
     * @param token the token string
     * @return an successful object if the token is valid, otherwise a failed obj
     */
    @GET
    @Path("/info/{token}")
    @Produces("application/json")
    public Map<String, Object> validateToken(@PathParam("token") String token) {
        ArrayList<TokenModel> tokenList = tokenManager.find(token);
        if (0 != tokenList.size()) {
            return utils.buildSuccessResult(null);
        }

        return utils.buildFailedResult("Not Login");
    }

    /**
     * get all employees' information
     *
     * @param token the token string
     * @return a successful object if the token is valid, otherwise a failed obj
     */
    @GET
    @Path("/all/token/{token}")
    @Produces("application/json")
    public Map<String, Object> getAllEmployees(@PathParam("token") String token) {
        ArrayList<TokenModel> tokenList = tokenManager.find(token);
        if (0 != tokenList.size()) {
            if (1 != tokenList.get(0).getEmployee().getAdmin()) {
                return utils.buildFailedResult("No Access");
            }
            return utils.buildSuccessResult(employeeManager.getAll());
        }

        return utils.buildFailedResult("Not Login");
    }

    /**
     * add a new employee
     *
     * @param token      the token string
     * @param credential the credential for the new employee
     * @return a successful object if the employee is created, otherwise an error obj
     */
    @POST
    @Path("/token/{token}")
    @Consumes("application/json")
    @Produces("application/json")
    public Map<String, Object> addEmployee(
            @PathParam("token") String token, Credentials credential) {
        ArrayList<TokenModel> tokenList = tokenManager.find(token);
        if (0 == tokenList.size()) {
            return utils.buildFailedResult("Not Login");
        }

        if (0 != employeeManager.find(credential.getUserName()).size()) {
            return utils.buildErrorResult("User already exists!");
        }

        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setName(credential.getUserName());
        employeeModel.setUsername(credential.getUserName());
        employeeModel.setPassword(credential.getPassword());
        employeeModel.setAdmin(0);
        employeeManager.persist(employeeModel);

        return utils.buildSuccessResult(employeeManager.getAll());
    }

    /**
     * update a given employee
     *
     * @param token    the token string
     * @param employee the employee to update
     * @return a successful object if the employee is updated
     */
    @POST
    @Path("/update/token/{token}")
    @Consumes("application/json")
    @Produces("application/json")
    public Map<String, Object> updateEmployee(
            @PathParam("token") String token, EmployeeModel employee) {
        ArrayList<TokenModel> tokenList = tokenManager.find(token);
        if (0 == tokenList.size()) {
            return utils.buildFailedResult("Not Login");
        }

        employeeManager.merge(employee);
        return utils.buildSuccessResult(null);
    }

    /**
     * delete a given employee
     *
     * @param token the token string
     * @param id    the employee's id
     * @return a successful object if the employee is deleted
     */
    @DELETE
    @Path("/token/{token}/id/{id}")
    @Produces("application/json")
    public Map<String, Object> deleteEmployee(
            @PathParam("token") String token, @PathParam("id") int id) {
        ArrayList<TokenModel> tokenList = tokenManager.find(token);
        if (0 == tokenList.size()) {
            return utils.buildFailedResult("Not Login");
        }

        EmployeeModel employeeModel = employeeManager.find(id);
        if (1 == employeeModel.getAdmin()) {
            return utils.buildErrorResult("You cannot delete admin");
        }

        employeeManager.remove(id);
        return utils.buildSuccessResult(null);
    }

    /**
     * modify the employee's password
     *
     * @param token    the token string
     * @param password the new password
     * @return a successful object if the password is modified
     */
    @POST
    @Path("/token/{token}/password/{password}")
    @Produces("application/json")
    public Map<String, Object> updatePassword(
            @PathParam("token") String token,
            @PathParam("password") String password) {
        ArrayList<TokenModel> tokenList = tokenManager.find(token);
        if (0 == tokenList.size()) {
            return utils.buildFailedResult("Not Login");
        }

        EmployeeModel employeeModel = tokenList.get(0).getEmployee();
        employeeModel.setPassword(password);
        employeeManager.merge(employeeModel);

        return utils.buildSuccessResult(null);
    }
}
