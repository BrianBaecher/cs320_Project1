/*
Author: Brian Baecher
Date: 9/27/2024
Course ID: CS-320-13376-M01
Description: TaskService class Junit testing file.
*/

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class TaskServiceTest {
    @Test
    public void addSingleTask(){
        //adding a single task
        //adding and getting a task back from the service
        var service = TaskService.getInstance();

        // tasks are added from within constructors if all fields valid
        Task t = new Task("Name", "Description");

        // the stored object is the object that was created.
        Assertions.assertEquals(service.getTask(t.getId()), t);
    }

    @Test
    public void addMultipleTasks(){
        var service = TaskService.getInstance();
        int numToAdd = 20;

        String validName = "Name";
        String validDescription = "Description";

        List<Task> taskList = new ArrayList<>(numToAdd);
        List<String> idList = new ArrayList<>(numToAdd);

        for(int i = 0; i < numToAdd; i++){
            Task t = new Task(validName, validDescription);
            idList.add(t.getId());
            taskList.add(t);
        }

        for(int i = 0; i < idList.size(); i++){
            Assertions.assertEquals(service.getTask(idList.get(i)), taskList.get(i));
        }
    }

    @Test
    public void updateTaskName(){
        var service = TaskService.getInstance();

        String validName = "New name";

        Task t = new Task("Old Name", "Description");

        service.updateTaskName(t.getId(), validName);

        // confirm the Task now has "New Name" as its name value.
        Assertions.assertEquals(t.getName(), validName);
    }

    @Test
    public void updateDescription(){
        var service = TaskService.getInstance();

        String validDescription= "New valid description";

        Task t = new Task("Name", "Old Description");

        service.updateTaskDescription(t.getId(), validDescription);

        // confirm the Task now has "New valid description" as its description value.
        Assertions.assertEquals(t.getDescription(), validDescription);
    }


    @Test
    public void deleteTask(){
        var service = TaskService.getInstance();
        Task t = new Task("Name", "Description");

        // the task exists in the store
        Assertions.assertEquals(service.getTask(t.getId()), t);

        service.deleteTask(t.getId());

        // task no longer exists in store
        Assertions.assertNull(service.getTask(t.getId()));
    }


}
