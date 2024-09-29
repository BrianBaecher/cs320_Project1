/*
Author: Brian Baecher
Date: 9/27/2024
Course ID: CS-320-13376-M01
Description: The Task class.
*/


public class Task {
    private final String Id;

    private String Name;

    private String Description;

    public Task(String name, String description) {
        // get unique ID from Task Service
        Id = TaskService.getInstance().getUniqueTaskId();

        // ensure name field is valid
        boolean validName = tryRegisterName(name);

        // ensure description field is valid
        boolean validDescription = tryRegisterDescription(description);

        // if fields valid, add this task to the TaskService data store.
        if(validName && validDescription){
            TaskService.getInstance().addTask(this);
        }
    }


    // Getters
    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }


    // Setters
    public void setName(String name) {
        tryRegisterName(name);
    }

    public void setDescription(String description) {
        tryRegisterDescription(description);
    }


    // registration and validation methods.
    /**
     * Attempts to set Task Name field to given name value.
     * @param name the desired Task name
     * @return true if successful, false if unsuccessful
     */
    private boolean tryRegisterName(String name){
        boolean success = false;
        try {
            Name = validateName(name);
            success = true;
        } catch (IllegalArgumentException illegalArg) {
            throw new IllegalArgumentException(illegalArg.getMessage());
        }
        return success;
    }

    /**
     * Validates given name.
     * @param name the Task name to validate
     * @throws IllegalArgumentException if name is not valid.
     * @return the given name string with whitespace trimmed.
     */
    private String validateName(String name){
        //required name String field that cannot be longer than 20 characters. The name field shall not be null.
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("task name '" + name + "' is null or empty String");
        }

        // remove whitespace if any.
        name = name.trim();

        // check length of name. exception if longer than 10 chars.
        if (name.length() > 20) {
            String msg = "length of task name '" + name + "' exceeds 20 characters";
            throw new IllegalArgumentException(msg);
        }

        return name;
    }

    /**
     * Attempts to set Task.Description field to given description value.
     * @param description the desired Task description
     * @return true if successful, false if unsuccessful
     */
    private boolean tryRegisterDescription(String description){
        boolean success = false;
        try {
            Description = validateDescription(description);
            success = true;
        } catch (IllegalArgumentException illegalArg) {
            throw new IllegalArgumentException(illegalArg.getMessage());
        }
        return success;
    }

    /**
     * Validates given name.
     * @param description the Task description to validate
     * @throws IllegalArgumentException if description is not valid.
     * @return the given description string with whitespace trimmed.
     */
    private String validateDescription(String description){
        //required description String field that cannot be longer than 50 characters. The description field shall not be null.
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("task description is null or empty String");
        }

        // remove whitespace if any.
        description = description.trim();

        // check length of name. exception if longer than 10 chars.
        if (description.length() > 50) {
            String msg = "length of task name '" + description + "' exceeds 20 characters";
            throw new IllegalArgumentException(msg);
        }

        return description;
    }


}
