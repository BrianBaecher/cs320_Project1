public class Task {
    private final String Id;

    private String Name;

    private String Description;

    public Task(String name, String description) {
        Id = TaskService.getInstance().getUniqueTaskId();
        boolean validName = tryRegisterName(name);
        Description = description;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    // setter

    public void setName(String name) {
        //TODO: validate
        Name = name;
    }

    public void setDescription(String description) {
        //Todo: validate
        Description = description;
    }

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


    private boolean tryRegisterDescription(String name){
        boolean success = false;

        try {
            Name = validateName(name);
            success = true;
        } catch (IllegalArgumentException illegalArg) {
            throw new IllegalArgumentException(illegalArg.getMessage());
        }
        return success;
    }

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
