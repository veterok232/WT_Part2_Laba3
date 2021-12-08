package by.application.client.service;

import by.application.client.entity.student.Student;
import by.application.client.entity.user.User;
import java.util.List;

/**
 * Student service api
 */
public interface StudentClientService
{
    /**
     * Login user
     *
     * @param user User instance
     *
     * @return User
     */
    User login(User user);

    /**
     * Register new user
     *
     * @param user User instance
     *
     * @return User
     */
    User register(User user);

    /**
     * Get all students
     *
     * @return List
     */
    List<Student> getAll();

    /**
     * Get user by id
     *
     * @param id Student id
     *
     * @return Student
     */
    Student get(int id);

    /**
     *  Edit student
     *
     * @param newValue New student instance
     *
     * @return boolean
     */
    boolean edit(Student newValue);

    /**
     * Create new student
     *
     * @param student Student instance
     *
     * @return boolean
     */
    boolean create(Student student);
}
