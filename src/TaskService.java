/*
Author: Brian Baecher
Date: 9/27/2024
Course ID: CS-320-13376-M01
Description: TaskService uses in-memory data structures to support storing,
 deleting, and updating Task objects used in the application.
*/

import java.util.Map;
import java.util.HashMap;
import java.util.Random;

/**
 * The TaskService class is a singleton that stores Task objects.
 * It provides methods to add, retrieve, update, and delete stored tasks
 * and is responsible for creating a Contact's unique ID property.
 */
public class TaskService {
    public static TaskService instance;

    // in memory data-store for Task objects.
    private final Map<String, Task> taskMap;

    private final Random rand = new Random();

    // private constructor
    private TaskService() {
        taskMap = new HashMap<>();
    }

    /**
     * provides access to TaskService singleton instance
     */
    public static TaskService getInstance() {
        if (instance == null) {
            instance = new TaskService();
        }
        return instance;
    }

    /**
     * Generates and returns a unique identifier for a Task instance.
     * @return String
     * */
    public String getUniqueTaskId() {
        String uniqueId;
        do {
            uniqueId = UtilityClasses.IdGenerator.generateRandomId();
        } while (taskMap.containsKey(uniqueId));

        return uniqueId;
    }

    /**
     * Adds the given Task to stored Tasks.
     * */
    public void addTask(Task task) {
        taskMap.put(task.getId(), task);
    }

    /** Search for Task with the given id.
     * @return Task object if matching id is found, NULL if not found.
     * */
    public Task getTask(String taskId) {
        return taskMap.get(taskId);
    }

    /**
     * Deletes stored Task with matching id string.
     * @param taskId id of Task to delete.
     */
    public void deleteTask(String taskId) {
        Task task = taskMap.get(taskId);

        if (task == null) {
            System.out.println("task with id " + taskId + " not found");
        } else {
            taskMap.remove(taskId);
        }
    }

    /**
     * Updates the name field of a stored Task with matching taskId.
     * @param taskId id of Task to update.
     * @param name updated Task Name value.
     */
    public void updateTaskName(String taskId, String name) {
        Task task = taskMap.get(taskId);
        if (task == null) {
            System.out.println("task with id " + taskId + " not found");
        } else {
            task.setName(name);
        }
    }

    /**
     * Updates the description field of a stored Task with matching taskId.
     * @param taskId id of Task to update.
     * @param description updated Task Description value.
     */
    public void updateTaskDescription(String taskId, String description) {
        Task task = taskMap.get(taskId);
        if (task == null) {
            System.out.println("task with id " + taskId + " not found");
        } else {
            task.setDescription(description);
        }
    }
}
