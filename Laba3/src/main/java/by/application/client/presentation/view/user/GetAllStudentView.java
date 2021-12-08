package by.application.client.presentation.view.user;

import by.application.client.entity.student.Student;
import by.application.client.entity.user.User;
import by.application.client.presentation.view.PresentationView;
import by.application.client.presentation.view.admin.AdminView;
import by.application.client.service.StudentClientService;
import java.util.List;

/**
 * Get all students form
 */
public class GetAllStudentView extends PresentationView
{
    /**
     * Constructor
     *
     *  @param studentService Student service
     *  @param user User instance
     */
    public GetAllStudentView(StudentClientService studentService, User user) {
        super(studentService, user);
    }

    /**
     * Show all students form
     */
    @Override
    public void show() {
        List<Student> studentList = this.studentService.getAll();
        for (Student student : studentList) {
            System.out.println(student.getId() + ": " + student.getName());
        }

        System.out.println("Enter 'exit' to go back");
        System.out.println("Print student id: ");
    }

    /**
     * Get user input command
     *
     * @param input User input command
     *
     * @return PresentationView
     */
    @Override
    public PresentationView getInput(String input) {
        if ("exit".equals(input)) {
            switch (this.currentUser.getRole()) {
                case USER : { return  new UserView(this.studentService, this.currentUser); }
                case ADMIN : { return new AdminView(this.studentService, this.currentUser); }
                default : { return null; }
            }
        }

        int id;
        try {
            id = Integer.parseInt(input);
        }
        catch (NumberFormatException ex) {
           throw new IllegalArgumentException();
        }

        return new GetStudentView(this.studentService, this.currentUser, id);
    }
}
