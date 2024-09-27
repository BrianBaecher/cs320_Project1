import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * The ContactService class is a singleton that stores Contact objects.
 * It provides methods to add, retrieve, update, and delete stored contacts
 * and is responsible for creating a Contact's unique ID property.
 */
public class ContactService {
    private static ContactService instance;

    // Private constructor to prevent instantiation
    private ContactService() {
        // create hashmap to track Contact objects
        contactMap = new HashMap<>();
    }

    /**
     * Public method to provide access to ContactService singleton instance
    */
    public static ContactService getInstance() {
        if (instance == null) {
//            System.out.println("Creating Instance of ContactService...");
            instance = new ContactService();
        }
        return instance;
    }

    // using Map<String, Contact> to store Contacts with Contact.Id as key.
    // Map interface is implemented by a HashMap.
    private final Map<String, Contact> contactMap;

    // constant defining max Contact.Id string length
    private final int MAX_ID_LEN = 10;

    // not specified in rubric, but I figure the minimum length of an Id should be > 1 at least
    private final int MIN_ID_LEN = 2;

    // Random to help in creating new Ids
    private final Random rand = new Random();

    // string containing the characters I'm allowing to be present within an Id.
    private final String ALLOWED_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * Generates and returns a unique identifier for a Contact instance.
     * @return String
     * */
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

    /**
     * Generates a random string as a possible Contact ID.
     * @return String
     * */
    private String generateRandomId() {
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

    /**
     * Adds the given Contact to stored Contacts.
     * */
    public void addContact(Contact contact) {
        // add incoming contact to hash map
        // key is contact's Id
        contactMap.put(contact.getId(), contact);
    }

    // adding getContact method for testing purposes...
    public Contact getContact(String contactId){
        Contact result = contactMap.get(contactId);

        if(result == null){
            throw new NoSuchElementException("Contact object with id "+ contactId + " does not exist");
        }

        return result;
    }

    /**
     * Searches for a stored Contact with a matching ID string. <br/>
     * Removes from store if found. <br/>
     * @param contactId a Contact's Id String
     * @throws NoSuchElementException if no matching contactId exists in store.
     * */
    public void deleteContact(String contactId) {
        // does the contact with contactId exist in map?
        if (!contactMap.containsKey(contactId)) {
            throw new NoSuchElementException(
                    "Cannot remove contact with id: " + contactId + " from ContactService as no such ID exists"
            );
        }
        contactMap.remove(contactId);
    }


    /**
     * enum representing the fields for a Contact object.
    */
    public enum ContactField {
        FIRST_NAME,
        LAST_NAME,
        PHONE,
        ADDRESS
    }

    /**
     * Attempts to update an existing Contact's information within the store.
     * @param contactId id string to search for.
     * @param fieldToUpdate enum value specifying which field to update.
     * @param value the update value.
     */
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
                throw new IllegalArgumentException("ContactField to update was not recognized");
        }
    }

}
