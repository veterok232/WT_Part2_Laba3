package by.application.client.dao.impl;

import by.application.client.dao.StudentClientDAO;
import by.application.client.dao.impl.manager.Manager;
import by.application.client.entity.student.Student;
import by.application.client.entity.response.StudentResponse;
import by.application.client.entity.request.RequestType;
import by.application.client.entity.response.ResponseType;
import by.application.client.entity.user.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Student data access object class
 */
public class StudentClientDAOImpl implements StudentClientDAO
{
    /**
     * Client socket manager
     */
    private final Manager manager = new Manager("localhost", 8080);

    /**
     * Login user
     *
     * @param user user instance
     *
     * @return User
     */
    @Override
    public User login(User user)
    {
        StudentResponse response = manager.sendRequest(RequestType.LOGIN, user);
        if (response.getBody() instanceof User)
        {
            return (User)response.getBody();
        }

        return null;
    }

    /**
     * Register new user
     *
     * @param user user instance
     *
     * @return User
     */
    @Override
    public User register(User user)
    {
        StudentResponse response = manager.sendRequest(RequestType.REGISTER, user);
        if (response.getBody() instanceof User)
        {
            return (User)response.getBody();
        }

        return null;
    }

    /**
     * Get all students
     *
     * @return List
     */
    @Override
    public List<Student> getAll()
    {
        StudentResponse response = manager.sendRequest(RequestType.GET_ALL, null);
        if ((response != null) &&
                (response.getResponseType() == ResponseType.OK) && (response.getBody() instanceof ArrayList<?>))
        {
            try
            {
                return (ArrayList<Student>)response.getBody();
            }
            catch (ClassCastException e)
            {
                return new ArrayList<>();
            }
        }

        return new ArrayList<>();
    }

    /**
     * Get student by id
     *
     * @param id student id
     *
     * @return Student
     */
    @Override
    public Student get(int id)
    {
        StudentResponse response = manager.sendRequest(RequestType.GET, id);
        if ((response != null)
                && (response.getResponseType() == ResponseType.OK) && (response.getBody() instanceof Student))
        {
            return (Student)response.getBody();
        }

        return null;
    }

    /**
     *  Edit student
     *
     * @param newValue new student instance
     *
     * @return boolean
     */
    @Override
    public boolean edit(Student newValue)
    {
        StudentResponse response = manager.sendRequest(RequestType.EDIT, newValue);

        return (response != null) && (response.getResponseType() == ResponseType.OK);
    }

    /**
     * Create new student
     *
     * @param newValue student instance
     *
     * @return boolean
     */
    @Override
    public boolean create(Student newValue)
    {
        StudentResponse response = manager.sendRequest(RequestType.CREATE, newValue);

        return (response != null) && (response.getResponseType() == ResponseType.OK);
    }
}
