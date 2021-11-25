package ca.bcit.infosys.route;

import ca.bcit.infosys.access.TokenManager;
import ca.bcit.infosys.model.employee.EmployeeModel;
import ca.bcit.infosys.model.token.TokenModel;

import javax.inject.Inject;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A tool class.
 *
 * @author Hang Liu
 * @version 1.0
 */

public class Utils{
    /**
     * The token manipulator
     */
    @Inject
    private TokenManager tokenManager;

    /**
     * Build a successful object that to be sent to the client
     *
     * @param obj the object to be wrapped
     * @return an object that to be sent to the client
     */
    public Map<String, Object> buildSuccessResult(Object obj){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("data", obj);
        return result;
    }

    /**
     * Build a failed object that to be sent to the client
     *
     * @param message the failed message to be sent
     * @return an object that to be sent to the client
     */
    public Map<String, Object> buildFailedResult(String message){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 1);
        result.put("message", message);
        return result;
    }

    /**
     * Build an error object that to be sent to the client
     *
     * @param message the error message to be sent
     * @return an object that to be sent to the client
     */
    public Map<String, Object> buildErrorResult(String message){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 2);
        result.put("message", message);
        return result;
    }

    /**
     * Encrypt the original string into a md5 string
     *
     * @param str the original string
     * @return the encrypted string
     */
    public String encrypt2ToMD5(String str) {
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest  = md5.digest(str.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assert digest != null;
        return new BigInteger(1, digest).toString(16);
    }

    /**
     * get the employee from the given token
     *
     * @param token the token string
     * @return the employee model
     */
    public EmployeeModel getCurrentUser(String token){
        ArrayList<TokenModel> tokenList = tokenManager.find(token);
        if (0 != tokenList.size()) {
            return tokenList.get(0).getEmployee();
        }

        return null;
    }
}
