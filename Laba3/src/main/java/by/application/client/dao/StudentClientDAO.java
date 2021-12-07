package by.application.client.dao;

import by.application.client.entity.student.Student;
import by.application.client.entity.user.User;
import java.util.List;

/**
 * Student data access object interface
 */
public interface StudentClientDAO
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
     * @return List<Student>
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

    /**
     *  Edit student
     *
     * @param student Student instance
     *
     * @return boolean
     */
    boolean edit(Student student);

    /**
     * Create new student
     *
     * @param student Student instance
     *
     * @return boolean
     */
    boolean create(Student student);
}
