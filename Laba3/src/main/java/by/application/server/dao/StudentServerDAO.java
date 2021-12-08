package by.application.server.dao;

import by.application.client.entity.student.Student;
import by.application.client.entity.user.User;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Student data access object api
 */
public interface StudentServerDAO
{
    /**
     * Edit users information
     *
     * @param users users list
     *
     * @throws FileNotFoundException exception
     */
    void rewriteUsers(List<User> users) throws FileNotFoundException;

    /**
     * Edit students information
     *
     * @param students Students list
     *
     * @throws FileNotFoundException exception
     */
    void rewriteStudents(List<Student> students) throws FileNotFoundException;

    /**
     * Check if user has already exist
     *
     * @param user User instance
     *
     * @return User
     */
    User userExists(User user);

    /**
     * Get all users
     *
     * @return List
     */
    List<User> getAllUsers();

    /**
     * Get all students
     *
     * @return List
     */
    List<Student> getAll();

    /**
     * Get student by id
     *
     * @param id Student id
     *
     * @return Student
     */
    Student get(int id);
}
