package testdata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.GlobalConstants;
import testdata.orangehrm.Employee_Data;

import java.io.File;

public class Employee {

    public static Employee getEmployee(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            return mapper.readValue(new File(GlobalConstants.DATA_TEST_PATH + "employee.json"),Employee.class);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @JsonProperty("username")
    private String emloyeeUsername;

    @JsonProperty("password")
    private String emloyeePassword;

    @JsonProperty("firstname")
    private String emloyeeFirstName;

    @JsonProperty("lastname")
    private String emloyeeLastName;

    @JsonProperty("dob")
    private String emloyeeDOB;

    @JsonProperty("email")
    private String emloyeeEmailAddress;

    @JsonProperty("address")
    private String emloyeeAddress;

    public String getEmloyeeFirstName() {
        return emloyeeFirstName;
    }

    public String getEmloyeeLastName() {
        return emloyeeLastName;
    }

    public String getEmloyeeDOB() {
        return emloyeeDOB;
    }

    public String getEmloyeeEmailAddress() {
        return emloyeeEmailAddress;
    }

    public String getEmloyeeAddress() {
        return emloyeeAddress;
    }

    public String getEmloyeeUsername() {
        return emloyeeUsername;
    }

    public String getEmloyeePassword() {
        return emloyeePassword;
    }
}
