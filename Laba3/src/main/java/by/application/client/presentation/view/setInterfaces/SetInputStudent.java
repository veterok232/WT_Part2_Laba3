package by.application.client.presentation.view.setInterfaces;

import by.application.client.entity.student.Student;

/**
 * Student information input interface
 */
public interface SetInputStudent {
    /**
     * Set student information
     *
     * @param student student information
     * @param input input command
     *
     * @return boolean
     */
    boolean setInput(Student student, String input);
}
