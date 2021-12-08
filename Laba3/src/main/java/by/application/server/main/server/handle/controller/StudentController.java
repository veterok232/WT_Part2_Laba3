package by.application.server.main.server.handle.controller;

import by.application.client.entity.student.Student;
import by.application.client.entity.request.StudentRequest;
import by.application.client.entity.response.StudentResponse;
import by.application.client.entity.response.ResponseType;
import by.application.client.entity.user.User;
import by.application.server.service.StudentServerService;
import java.util.List;

/**
 * Student controller
 */
public class StudentController
{
    /**
     * Student service
     */
    private final StudentServerService service;

    /**
     * Constructor
     *
     * @param service Student service
     */
    public StudentController(StudentServerService service) {
        this.service = service;
    }

    /**
     * Login user
     *
     * @param request Serialized user information
     *
     * @return StudentResponse
     */
    public StudentResponse login(StudentRequest request) {
        User user;

        StudentResponse response = new StudentResponse();
        if (request.getBody() instanceof User) {
            user = (User)request.getBody();
        }
        else {
            response.setResponseType(ResponseType.ERROR);
            return response;
        }

        User userResult = this.service.login(user);
        if (userResult != null) {
            response.setResponseType(ResponseType.OK);
            response.setBody(userResult);
        } else {
            response.setResponseType(ResponseType.ERROR);
        }

        return response;
    }

    /**
     * Registar a new user
     *
     * @param request Serialized user information
     *
     * @return StudentResponse
     */
    public StudentResponse register(StudentRequest request) {
        User user;

        StudentResponse response = new StudentResponse();
        if (request.getBody() instanceof User) {
            user = (User)request.getBody();
        }
        else {
            response.setResponseType(ResponseType.ERROR);
            return response;
        }

        User userResult = this.service.register(user);
        if (userResult != null) {
            response.setResponseType(ResponseType.OK);
            response.setBody(userResult);
        }
        else {
            response.setResponseType(ResponseType.ERROR);
        }

        return response;
    }

    /**
     * Get all students information
     *
     * @return StudentResponse
     */
    public StudentResponse getAll() {
        List<Student> students = this.service.getAll();

        StudentResponse response = new StudentResponse();
        if (students == null) {
            response.setResponseType(ResponseType.ERROR);
            response.setBody(null);
        }
        else {
            response.setResponseType(ResponseType.OK);
            response.setBody(students);
        }

        return response;
    }

    /**
     * Get student information by id
     *
     * @param request Student id
     *
     * @return StudentResponse
     */
    public StudentResponse get(StudentRequest request) {
        int id;

        StudentResponse response = new StudentResponse();
        if (request.getBody() instanceof Integer) {
            id = (int) request.getBody();
        }
        else {
            response.setResponseType(ResponseType.ERROR);
            return response;
        }

        Student studentToSend = this.service.get(id);
        if (studentToSend != null) {
            response.setResponseType(ResponseType.OK);
            response.setBody(studentToSend);
        }
        else {
            response.setResponseType(ResponseType.ERROR);
        }

        return response;
    }

    /**
     * Edit student
     *
     * @param request Student information
     *
     * @return StudentResponse
     */
    public StudentResponse edit(StudentRequest request) {
        Student student;

        StudentResponse response = new StudentResponse();
        if (request.getBody() instanceof Student) {
            student = (Student)request.getBody();
        }
        else {
            response.setResponseType(ResponseType.ERROR);
            return response;
        }

        if (this.service.edit(student)) {
            response.setResponseType(ResponseType.OK);
        }
        else {
            response.setResponseType(ResponseType.ERROR);
        }

        return response;
    }

    /**
     * Create a new student
     *
     * @param request Student information
     *
     * @return StudentResponse
     */
    public StudentResponse create(StudentRequest request) {
        Student student;

        StudentResponse response = new StudentResponse();
        if (request.getBody() instanceof Student) {
            student = (Student)request.getBody();
        }
        else {
            response.setResponseType(ResponseType.ERROR);
            return response;
        }

        if (this.service.create(student)) {
            response.setResponseType(ResponseType.OK);
        }
        else {
            response.setResponseType(ResponseType.ERROR);
        }

        return response;
    }

    /**
     * Process invalid response
     *
     * @return StudentResponse
     */
    public StudentResponse notFound() {
        StudentResponse response = new StudentResponse();
        response.setResponseType(ResponseType.NOTFOUND);

        return response;
    }
}
