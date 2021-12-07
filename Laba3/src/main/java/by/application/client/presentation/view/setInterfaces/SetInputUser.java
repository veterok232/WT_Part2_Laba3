package by.application.client.presentation.view.setInterfaces;

import by.application.client.entity.user.User;

/**
 * User information input interface
 */
public interface SetInputUser {
    /**
     * Set user information
     *
     * @param user User information
     * @param input Input command
     *
     * @return boolean
     */
    boolean setInput(User user, String input);
}
