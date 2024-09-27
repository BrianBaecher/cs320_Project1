import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class TaskService {
    public static TaskService instance;

    private final Map<String, Task> taskMap;

    private final Random rand = new Random();


    private TaskService(){
            taskMap = new HashMap<>();
    }

    public static TaskService getInstance(){
        if (instance==null){
            instance = new TaskService();
        }
        return instance;
    }

    public String getUniqueTaskId() {
        String uniqueId;
        do {
            uniqueId = generateRandomId();
        } while (taskMap.containsKey(uniqueId));

        return uniqueId;
    }
    private String generateRandomId() {
        final int MAX_ID_LEN = 10;
        final int MIN_ID_LEN = 2;
        final String ALLOWED_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        // get a random int between min and max for length of new id
        int length = MIN_ID_LEN + rand.nextInt(MAX_ID_LEN - MIN_ID_LEN + 1);

        // start building an id String...
        StringBuilder sb = new StringBuilder();

        // iterate the length of the id, adding a random char to the string builder
        for (int i = 0; i < length; i++) {
            // get a random char from the allowed
            char c = ALLOWED_CHARS.charAt(rand.nextInt(ALLOWED_CHARS.length()));

            sb.append(c);
        }

        // return the contents of the string builder with toString method...
        return sb.toString();
    }
}
