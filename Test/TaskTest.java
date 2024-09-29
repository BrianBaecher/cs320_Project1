/*
Author: Brian Baecher
Date: 9/27/2024
Course ID: CS-320-13376-M01
Description: The Task class Junit testing file.

Note: In the same way as the Contact class, Ids are assigned through
the relevant Service class which enforces uniqueness (duplicate Ids are not possible).
*/

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TaskTest {
    @Test
    public void createTask(){
        var service = TaskService.getInstance();

        String validName = "Task Name";
        String validDescription = "Task Description";

        Task t = new Task(validName, validDescription);

        // confirm task is created, and is stored in TaskService...
        Assertions.assertEquals(service.getTask(t.getId()), t);
    }

    @Test
    public void taskNameTooLong(){
        String invalidName = "A name that is too long";
        String validDescription = "task description";

        // confirm error thrown when invalid name is passed
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            new Task(invalidName, validDescription);
        });
    }

    @Test
    public void taskDescriptionTooLong(){
        String validName = "Task name";
        String invalidDescription = "too long".repeat(10);

        // confirm error thrown when invalid description is passed
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            new Task(validName, invalidDescription);
        });
    }
}
