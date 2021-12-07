package by.application.client.entity.student;

import by.application.client.entity.role.UserRole;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Student model
 */
public class Student implements Serializable
{
    /**
     * User role
     */
    private UserRole role;

    /**
     * Student id
     */
    private int id;

    /**
     * Student name
     */
    private String name;

    /**
     * Student date of birthday
     */
    private LocalDate birthday;

    /**
     * Student description
     */
    private String description;

    /**
     * Student last modification
     */
    private LocalDateTime lastModification;

    /**
     * Get user role
     *
     * @return UserRole
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Set user role
     *
     * @param role user role
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Get student id
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Set student id
     *
     * @param id student id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get student name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set student name
     *
     * @param name student name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get student date of birthday
     *
     * @return LocalDate
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Set student date of birthday
     *
     * @param birthday student date of birthday
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Get student general information
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set student general information
     *
     * @param description student general information
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get student last modification
     *
     * @return LocalDateTime
     */
    public LocalDateTime getLastModification() {
        return lastModification;
    }

    /**
     * Set student last modification
     *
     * @param lastModification student last modification
     */
    public void setLastModification(LocalDateTime lastModification) {
        this.lastModification = lastModification;
    }

    /**
     * Get student information
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Student " + id + ":" +
            "name = " + name +
            ", birthday = " + birthday +
            ", characteristic = " + description +
            ", lastModification = " + lastModification;
    }
}
