package Entity;

import java.io.Serializable;

public class StaffEntity implements Serializable{

    private String name;
    private String gender;
    private int employeeID;
    private String jobTitle;

    // constructor
    public StaffEntity(){
        name = "Name";
        gender = "MaleOrFemale";
        employeeID = 00000000;
        jobTitle = "Position";
    }

    public StaffEntity(String name, String gender, int employeeID, String jobTitle) {
        this.name = name;
        this.gender = gender;
        this.employeeID = employeeID;
        this.jobTitle = jobTitle;
    }

    // getters;

    public String getName() {
        return this.name;
    }

    public String getGender() {
        return this.gender;
    }

    public int getEmployeeID() {
        return this.employeeID;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setJobTtile(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
