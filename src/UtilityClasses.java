import java.util.Random;

public class UtilityClasses {
    public static class IdGenerator {
        private static final Random rand = new Random();

        public static String generateRandomId(){
            final String ALLOWED_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            final int MAX_ID_LEN = 10;
            final int MIN_ID_LEN = 2;

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
}
