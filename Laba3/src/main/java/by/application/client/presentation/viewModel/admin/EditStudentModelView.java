package by.application.client.presentation.viewModel.admin;

import by.application.client.entity.student.Student;
import by.application.client.presentation.viewModel.PresentationModel;
import by.application.client.service.StudentClientService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Get student information
 */
public class EditStudentModelView extends PresentationModel
{
    /**
     * Student id
     */
    private final int id;

    /**
     * Constructor
     *
     * @param studentService Student service
     * @param id Student id
     */
    public EditStudentModelView(StudentClientService studentService, int id) {
        super(studentService);

        this.id = id;
    }

    /**
     * Get student by id
     *
     * @return List<Student>
     */
    @Override
    public List<Student> getItems() {
        Student result = this.studentService.get(this.id);

        return result == null ? new ArrayList<>() : Collections.singletonList(result);
    }
}
