package by.application.client.presentation.viewModel;

import by.application.client.entity.student.Student;
import by.application.client.service.StudentClientService;
import java.util.List;

/**
 * Presentation model class
 */
public abstract class PresentationModel
{
    /**
     * Student service
     */
    protected StudentClientService studentService;

    /**
     * Constructor
     *
     * @param studentService Student service
     */
    public PresentationModel(StudentClientService studentService) {
        this.studentService = studentService;
    }

    /**
     * Get student by id
     *
     * @return List
     */
    public abstract List<Student> getItems();
}
