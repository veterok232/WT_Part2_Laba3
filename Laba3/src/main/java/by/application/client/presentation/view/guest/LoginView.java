package by.application.client.presentation.view.guest;

import by.application.client.entity.user.User;
import by.application.client.presentation.view.user.UserView;
import by.application.client.presentation.view.PresentationView;
import by.application.client.presentation.view.admin.AdminView;
import by.application.client.presentation.view.setInterfaces.SetInputUser;
import by.application.client.service.StudentClientService;
import org.javatuples.Pair;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Guest login form
 */
public class LoginView extends PresentationView
{
    /**
     * Input fields
     */
    private final List<Pair<String, SetInputUser>> inputs = Arrays.asList(
            new Pair<>("Login:", (user, input) -> {
                user.setLogin(input);
                return true;
            }),
            new Pair<>("Password:", (user, input) -> {
                user.setPassword(input);
                return true;
            })
    );

    /**
     * Constructor
     *
     * @param studentService student service
     * @param user user instance
     */
    public LoginView(StudentClientService studentService, User user) {
        super(studentService, user);
    }

    /**
     * Show guest login form
     */
    @Override
    public void show() {
        System.out.println("Enter 'exit' to go back");

        Scanner scanner = new Scanner(System.in);
        User user = new User();

        int i = 0;
        String input;

        while (i < inputs.size()) {
            System.out.println(inputs.get(i).getValue0());
            input = scanner.nextLine();
            if (input.equals("exit")) {
                return;
            }

            if (inputs.get(i).getValue1().setInput(user, input))
            {
                i++;
            }
            else {
                System.out.println("Invalid input!");
            }
        }

        User auth = this.studentService.login(user);
        if (auth == null) {
            System.out.println("User not found!");
        }
        else {
            this.currentUser = auth;
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
        switch (this.currentUser.getRole()) {
            case GUEST : { return new GuestView(this.studentService, this.currentUser); }
            case USER : { return new UserView(this.studentService, this.currentUser); }
            case ADMIN : { return new AdminView(this.studentService, this.currentUser); }
            default : throw new IllegalArgumentException();
        }
    }
}
