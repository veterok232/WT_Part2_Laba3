package by.application.client.service.impl;

import by.application.client.dao.StudentClientDAO;
import by.application.client.entity.student.Student;
import by.application.client.entity.user.User;
import by.application.client.service.StudentClientService;

import java.util.List;

/**
 * Student service class
 */
public class StudentClientServiceImpl implements StudentClientService
{
    /**
     * Student data object
     */
    private final StudentClientDAO studentDAO;

    /**
     * Constructor
     *
     * @param studentDAO Student data object
     */
    public StudentClientServiceImpl(StudentClientDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    /**
     * Login user
     *
     * @param user User instance
     *
     * @return User
     */
    @Override
    public User login(User user) {
        return this.studentDAO.login(user);
    }

    /**
     * Register new user
     *
     * @param user User instance
     *
     * @return User
     */
    @Override
    public User register(User user) {
        return this.studentDAO.register(user);
    }

    /**
     * Get all students
     *
     * @return List
     */
    @Override
    public List<Student> getAll() {
        return this.studentDAO.getAll();
    }

    /**
     * Get user by id
     *
     * @param id Student id
     *
     * @return Student
     */
    @Override
    public Student get(int id) {
        return this.studentDAO.get(id);
    }

    /**
     *  Edit student
     *
     * @param newValue New student instance
     *
     * @return boolean
     */
    @Override
    public boolean edit(Student newValue) {
        return this.studentDAO.edit(newValue);
    }

    /**
     * Create new student
     *
     * @param student Student instance
     *
     * @return boolean
     */
    @Override
    public boolean create(Student student) {
        return this.studentDAO.create(student);
    }
}
