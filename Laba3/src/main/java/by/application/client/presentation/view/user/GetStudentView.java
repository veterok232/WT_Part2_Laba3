package by.application.client.presentation.view.user;

import by.application.client.entity.student.Student;
import by.application.client.entity.user.User;
import by.application.client.presentation.view.PresentationView;
import by.application.client.presentation.viewModel.user.GetStudentModelView;
import by.application.client.service.StudentClientService;
import java.util.List;

/**
 * Get student form
 */
public class GetStudentView extends PresentationView
{
    /**
     * Constructor
     *
     *  @param studentService student service
     *  @param user user instance
     * @param id student id
     */
    public GetStudentView(StudentClientService studentService, User user, int id) {
        super(studentService, user);

        this.model = new GetStudentModelView(studentService, id);
    }

    /**
     * Show student form
     */
    @Override
    public void show() {
        List<Student> items = this.model.getItems();
        if (items.isEmpty()) {
            System.out.println("Element not found!");
        } else {
            System.out.println(items.get(0));
        }
    }

    /**
     * Get user input command
     *
     * @param input user input command
     *
     * @return PresentationView
     */
    @Override
    public PresentationView getInput(String input) {
        return new GetAllStudentView(this.studentService, this.currentUser);
    }
}
