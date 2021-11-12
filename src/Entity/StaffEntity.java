package Entity;

import java.io.Serializable;

/**
 * This is a single staff object
 * 
 * @author Wong Wei Bin
 * @author Ivan Teo
 * @author Grace Wong
 * @author Goh Xue Zhe
 * @author Matthew Chua
 * @version 0.1.0
 * @since 2021-11-11
 */
public class StaffEntity implements Serializable {

    /**
     * The name of this staff
     */
    private String name;
    /**
     * The gender of this staff
     */
    private String gender;
    /**
     * The employeeID of this staff
     */
    private int employeeID;
    /**
     * The job title of this staff
     */
    private String jobTitle;

    /**
     * Creates a new staff
     */
    public StaffEntity() {
        name = "Name";
        gender = "MaleOrFemale";
        employeeID = 00000000;
        jobTitle = "Position";
    }

    /**
     * Creates a new staff
     * 
     * @param name       This staff's name
     * @param gender     This staff's gender
     * @param employeeID This staff's employee ID
     * @param jobTitle   This staff's job title
     */
    public StaffEntity(String name, String gender, int employeeID, String jobTitle) {
        this.name = name;
        this.gender = gender;
        this.employeeID = employeeID;
        this.jobTitle = jobTitle;
    }

    /**
     * Gets the name of this staff
     * 
     * @return this staff's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the gender of this staff
     * 
     * @return this staff's gender
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * Gets the employee ID of this staff
     * 
     * @return this staff's employee ID
     */
    public int getEmployeeID() {
        return this.employeeID;
    }

    /**
     * Gets the job title of this staff
     * 
     * @return this staff's job title
     */
    public String getJobTitle() {
        return this.jobTitle;
    }

    /**
     * Changes the name of this staff
     * 
     * @param name this staff's new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Changes the gender of this staff
     * 
     * @param gender this staff's new gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Changes the employee ID of this staff
     * 
     * @param employeeID this staff's new employee ID
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * Changes the job title of this staff
     * 
     * @param jobTitle this staff's new job title
     */
    public void setJobTtile(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
