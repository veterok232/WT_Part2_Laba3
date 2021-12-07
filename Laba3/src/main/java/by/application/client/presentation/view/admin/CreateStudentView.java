package by.application.client.presentation.view.admin;

import by.application.client.entity.student.Student;
import by.application.client.entity.user.User;
import by.application.client.presentation.view.PresentationView;
import by.application.client.presentation.view.setInterfaces.SetInputStudent;
import by.application.client.service.StudentClientService;
import org.javatuples.Pair;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Get create student form
 */
public class CreateStudentView extends PresentationView
{
    /**
     * Input fields
     */
    private final List<Pair<String, SetInputStudent>> inputs = Arrays.asList(
            new Pair<>("Name:", (student, input) -> {
                student.setName(input);
                return true;
            }),
            new Pair<>("Birthday (dd/mm/yyyy):", (student, input) -> {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                try
                {
                    student.setBirthday(LocalDate.parse(input, dateTimeFormatter));

                    return true;
                }
                catch (DateTimeParseException ex)
                {
                    System.out.println("Invalid format!");
                }

                return false;
            }),
            new Pair<>("Description:", (student, input) -> {
                student.setDescription(input);
                return true;
            })
    );

    /**
     * Constructor
     *
     * @param studentService Student service
     * @param user User instance
     */
    public CreateStudentView(StudentClientService studentService, User user) {
        super(studentService, user);
    }

    /**
     * Show create student form
     */
    @Override
    public void show() {
        System.out.println("Enter 'exit' to go back");

        Student student = new Student();
        Scanner scanner = new Scanner(System.in);

        int i = 0;
        String input;

        while (i < inputs.size())
        {
            System.out.println(inputs.get(i).getValue0());
            input = scanner.nextLine();
            if (input.equals("exit"))
            {
                return;
            }

            if (inputs.get(i).getValue1().setInput(student, input))
            {
                i++;
            }
            else
            {
                System.out.println("Invalid input!");
            }
        }

        student.setLastModification(LocalDateTime.now());
        if (!this.studentService.create(student))
        {
            System.out.println("Error creating!");
        }
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
        return new AdminView(this.studentService, this.currentUser);
    }
}
