/*
Author: Brian Baecher
Date: 10/4/2024
Course ID: CS-320-13376-M01
Description: The ContactService class.
*/

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

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


    /**
     * Generates and returns a unique identifier for a Contact instance.
     * @return String
     * */
    public String getUniqueContactId() {
        String uniqueId;
        do {
            uniqueId = UtilityClasses.IdGenerator.generateRandomId();
        } while (contactMap.containsKey(uniqueId));

        return uniqueId;
    }

    /**
     * Adds the given Contact to stored Contacts.
     * */
    public void addContact(Contact contact) {
        // add incoming contact to hash map
        // key is contact's Id
        contactMap.put(contact.getId(), contact);
    }

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
