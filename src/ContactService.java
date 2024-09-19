import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

public class ContactService {
    // The single instance of the class
    private static ContactService instance;

    private final Map<String, Contact> contactMap;

    // constant variable defining max Contact.Id string length
    private final int MAX_ID_LEN = 10;

    // not specified in rubric, but I figure the minimum length of an Id should be > 1 at least
    private final int MIN_ID_LEN = 2;

    // Random to help in creating new Ids
    private final Random rand = new Random();

    // string containing the characters I'm allowing to be present within an Id.
    private final String ALLOWED_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // Private constructor to prevent instantiation
    private ContactService() {
        // create hashmap to track Contact objects
        contactMap = new HashMap<>();
    }

    // Public method to provide access to the instance
    public static ContactService getInstance() {
        if (instance == null) {
            System.out.println("Creating Instance of ContactService...");
            instance = new ContactService();
        }
        return instance;
    }

    public String getUniqueContactId() {
        /*
         * There is a chance that the generateRandomId method will create
         * an ID string that is already present in the map
         * (which should mean that a Contact already has that ID)
         * so a do-while loop is used until a value NOT in the map is found.
         * */

        String uniqueId;
        do {
            uniqueId = generateRandomId();
        } while (contactMap.containsKey(uniqueId));

        return uniqueId;
    }

    private String generateRandomId() {
        // get a random int between min and max for length of new id
        int length = MIN_ID_LEN + rand.nextInt(MAX_ID_LEN - MIN_ID_LEN + 1);

        // now to start building an id String...
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


    public void addContact(Contact contact) {
        // add incoming contact to hash map
        // key is contact's Id
        contactMap.put(contact.getId(), contact);

        // TODO: remove debug messages...
        System.out.println("-----------------Contact added to ContactService's map-----------------");
        System.out.println("Contact Information");
        System.out.println("Contact Id: " + contact.getId());
        System.out.println("Contact First Name: " + contact.getFirstName());
        System.out.println("Contact Last Name: " + contact.getLastName());
        System.out.println("Contact Phone: " + contact.getPhone());
        System.out.println("Contact Address: " + contact.getAddress());
        System.out.println();
        System.out.println("ContactService now contains " + contactMap.keySet().size() + " entries");
        System.out.println("------------------------------------------------------------------------");


    }

    public void deleteContact(String contactId) {
        // does the contact with contactId exist in map?
        if (!contactMap.containsKey(contactId)) {
            throw new RuntimeException(
                    "Cannot remove contact with id: " + contactId + " from ContactService as no such ID exists"
            );
        }
        contactMap.remove(contactId);

        // TODO: remove debug messages...
        System.out.println("Removed Contact with id: " + contactId + " from ContactService");
        System.out.println("ContactService now contains " + contactMap.keySet().size() + " entries");
    }


    // enum for update method
    public enum ContactField {
        FIRST_NAME,
        LAST_NAME,
        PHONE,
        ADDRESS
    }

    public void updateContact(String contactId, ContactField fieldToUpdate, String value) {
        Contact contact = contactMap.get(contactId);

        if (contact == null) {
            throw new NoSuchElementException(
                    "Could not update Contact with Id: " + contactId + " as no such Contact could be found."
            );
        }

        switch (fieldToUpdate) {
            case FIRST_NAME:
                contact.setFirstName(value);
                break;
            case LAST_NAME:
                contact.setLastName(value);
                break;
            case PHONE:
                contact.setPhone(value);
                break;
            case ADDRESS:
                contact.setAddress(value);
                break;
            default:
                throw new IllegalArgumentException("ContactField to update was not specified");
        }
    }

}
