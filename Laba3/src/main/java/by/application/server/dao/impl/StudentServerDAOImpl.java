package by.application.server.dao.impl;

import by.application.client.entity.student.Student;
import by.application.client.entity.user.User;
import by.application.server.dao.StudentServerDAO;
import java.beans.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Student data access object class
 */
public class StudentServerDAOImpl implements StudentServerDAO
{
    /**
     * Path to users.xml
     */
    private static final String USERS_PATH = "src/main/resources/users.xml";

    /**
     * Path to students.xml
     */
    private static final String STUDENTS_PATH = "src/main/resources/students.xml";

    /**
     * Synchronization
     */
    private final ReentrantReadWriteLock usersLock = new ReentrantReadWriteLock(true);

    /**
     * Synchronization
     */
    private final ReentrantReadWriteLock studentsLock = new ReentrantReadWriteLock(true);

    /**
     * Edit users information
     *
     * @param users Users list
     *
     * @throws FileNotFoundException exception
     */
    @Override
    public void rewriteUsers(List<User> users) throws FileNotFoundException
    {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(StudentServerDAOImpl.USERS_PATH)
        ))) {
            try {
                this.studentsLock.writeLock().lock();
                for (User item : users) {
                    encoder.writeObject(item);
                }
            }
            finally {
                this.studentsLock.writeLock().unlock();
            }
        }
        catch (ArrayIndexOutOfBoundsException ignored) { }
    }

    /**
     * Edit students information
     *
     * @param students Students list
     *
     * @throws FileNotFoundException exception
     */
    @Override
    public void rewriteStudents(List<Student> students) throws FileNotFoundException
    {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(StudentServerDAOImpl.STUDENTS_PATH)
        ))) {
            encoder.setPersistenceDelegate(LocalDate.class, new PersistenceDelegate() {
                @Override
                protected Expression instantiate(Object localDate, Encoder encoder)
                {
                    return new Expression(localDate, LocalDate.class, "parse",
                            new Object[]{ localDate.toString() });
                }
            });

            encoder.setPersistenceDelegate(LocalDateTime.class, new PersistenceDelegate() {
                @Override
                protected Expression instantiate(Object localDateTime, Encoder encoder) {
                    return new Expression(localDateTime, LocalDateTime.class, "parse",
                        new Object[]{ localDateTime.toString() });
                }
            });

            try {
                this.studentsLock.writeLock().lock();
                for (Student item : students) {
                    encoder.writeObject(item);
                }
            }
            finally {
                this.studentsLock.writeLock().unlock();
            }
        }
        catch (ArrayIndexOutOfBoundsException ignored) { }
    }

    /**
     * Check if user has already exist
     *
     * @param user User instance
     *
     * @return User
     */
    @Override
    public User userExists(User user)
    {
        User readUser;
        this.usersLock.readLock().lock();

        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(StudentServerDAOImpl.USERS_PATH)
        ))) {
            do {
                readUser = (User) decoder.readObject();
                if (readUser.getLogin().equals(user.getLogin())) {
                    return readUser;
                }
            }
            while (readUser != null);
        }
        catch (ArrayIndexOutOfBoundsException | FileNotFoundException ignored) { }
        finally {
            this.usersLock.readLock().unlock();
        }

        return null;
    }

    /**
     * Get all users
     *
     * @return List
     */
    @Override
    public List<User> getAllUsers()
    {
        User user;
        ArrayList<User> users = new ArrayList<>();
        this.usersLock.readLock().lock();

        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(StudentServerDAOImpl.USERS_PATH)
        ))) {
            do {
                user = (User) decoder.readObject();
                users.add(user);
            }
            while (user != null);
        }
        catch (ArrayIndexOutOfBoundsException e) { }
        catch (FileNotFoundException e) {
            System.out.printf("Error trying read XML: %s%n", e.getMessage());
        }
        finally {
            this.usersLock.readLock().unlock();
        }

        return users;
    }

    /**
     * Get all students
     *
     * @return List
     */
    @Override
    public List<Student> getAll()
    {
        Student student;
        ArrayList<Student> students = new ArrayList<>();
        this.studentsLock.readLock().lock();

        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(StudentServerDAOImpl.STUDENTS_PATH)
        ))) {
            do {
                student = (Student) decoder.readObject();
                students.add(student);
            }
            while (student != null);
        }
        catch (ArrayIndexOutOfBoundsException e) { }
        catch (FileNotFoundException e) {
            System.out.printf("Error trying read XML: %s%n", e.getMessage());
        }
        finally {
            this.studentsLock.readLock().unlock();
        }

        return students;
    }

    /**
     * Get student by id
     *
     * @param id Student id
     *
     * @return Student
     */
    @Override
    public Student get(int id) {
        Student student;
        this.studentsLock.readLock().lock();

        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(StudentServerDAOImpl.STUDENTS_PATH)
        ))) {
            do {
                student = (Student) decoder.readObject();
                if (student.getId() == id) {
                    return student;
                }
            }
            while (student != null);
        }
        catch (ArrayIndexOutOfBoundsException e) { }
        catch (FileNotFoundException e) {
            System.out.printf("Error trying read XML: %s%n", e.getMessage());
        }
        finally {
            this.studentsLock.readLock().unlock();
        }

        return null;
    }
}
