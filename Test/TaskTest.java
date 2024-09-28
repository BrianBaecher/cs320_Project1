import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TaskTest {
    @Test
    public void createTask(){
        var service = TaskService.getInstance();

        String validName = "Task Name";
        String validDescription = "Task Description";

        Task t = new Task(validName, validDescription);

        // task is created, and is stored in TaskService...
        Assertions.assertEquals(service.getTask(t.getId()), t);
    }

    @Test
    public void taskNameTooLong(){
        String invalidName = "A name that is too long";
        String validDescription = "task description";

        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            new Task(invalidName, validDescription);
        });
    }

    @Test
    public void taskDescriptionTooLong(){
        String validName = "Task name";
        String invalidDescription = "too long".repeat(10);

        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            new Task(validName, invalidDescription);
        });
    }
}
