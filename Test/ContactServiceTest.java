/*
Author: Brian Baecher
Date: 10/4/2024
Course ID: CS-320-13376-M01
Description: Junit tests for the ContactService class.
*/

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class ContactServiceTest {
    Contact validContact = new Contact(
            "John",
            "Doe",
            "1234567890",
            "11 main street"
    );

    // -----------------------------Adding and Deleting Contacts from Contact Service---------------------------------->
    @Test
    public void canAddValidContact() {
        // if all Contact's fields are valid, the Contact instance calls
        // ContactService static instance's .addContact() within Contact's constructor.
        // So any valid Contact will be stored in ContactService's Map "contactMap".
        // Contacts that are not valid will never appear in the ContactService map.

        Assert.assertSame(ContactService.getInstance().getContact(validContact.getId()), validContact);
    }

    @Test
    public void canDeleteContact() {
        // assert that the contact exists in the service
        Assert.assertSame(ContactService.getInstance().getContact(validContact.getId()), validContact);

        ContactService.getInstance().deleteContact(validContact.getId());

        // assert that the contact no longer exists within the service
        // a bad search will throw a NoSuchElementException
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            ContactService.getInstance().getContact(validContact.getId());
        });
    }

    @Test
    public void cannotDeleteWithInvalidId() {
        var service = ContactService.getInstance();

        // an impossible Contact.Id
        String badId = ".1";

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            service.deleteContact(badId);
        });
    }

    // -----------------------------Updating Contact fields tests---------------------------------->

    // -----------------------------first and last name update tests---------------------------------->
    @Test
    public void canUpdateValidFirstName() {
        var service = ContactService.getInstance();

        String newValidFirstName = "Frank";

        // assert that the name of the validContact matches the original before changing...
        Assertions.assertEquals(
                service.getContact(validContact.getId()).getFirstName(),
                validContact.getFirstName());

        // update the name
        service.updateContact(validContact.getId(),
                ContactService.ContactField.FIRST_NAME,
                newValidFirstName);

        // assert that the name of the validContact now matches the new name
        Assertions.assertEquals(
                service.getContact(validContact.getId()).getFirstName(),
                newValidFirstName
        );
    }

    @Test
    public void cannotUpdateInvalidFirstName() {
        var service = ContactService.getInstance();

        // logging the initial name of the validContact
        String initialFirstName = service.getContact(validContact.getId()).getFirstName();

        String invalidFirstName = "Jo-hn";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact(validContact.getId(),
                    ContactService.ContactField.FIRST_NAME,
                    invalidFirstName);
        });

        // assert that the name was not changed.
        Assertions.assertEquals(
                service.getContact(validContact.getId()).getFirstName(),
                initialFirstName
        );
    }


    @Test
    public void canUpdateValidLastName() {
        var service = ContactService.getInstance();

        String newValidLastName = "Frank";

        // assert that the name of the validContact matches the original before changing...
        Assertions.assertEquals(
                service.getContact(validContact.getId()).getLastName(),
                validContact.getLastName()
        );

        // update the name
        service.updateContact(validContact.getId(),
                ContactService.ContactField.LAST_NAME,
                newValidLastName
        );

        // assert that the name of the validContact now matches the new name
        Assertions.assertEquals(
                service.getContact(validContact.getId()).getLastName(),
                newValidLastName
        );
    }

    @Test
    public void cannotUpdateInvalidLastName() {
        var service = ContactService.getInstance();

        // logging the initial name of the validContact
        String initialLastName = service.getContact(validContact.getId()).getLastName();

        String invalidLastName = "Jo-hn";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact(validContact.getId(),
                    ContactService.ContactField.LAST_NAME,
                    invalidLastName);
        });

        // assert that the name was not changed.
        Assertions.assertEquals(
                service.getContact(validContact.getId()).getLastName(),
                initialLastName
        );
    }

    // -----------------------------phone update tests---------------------------------->
    @Test
    public void canUpdateValidPhone() {
        var service = ContactService.getInstance();

        String newValidPhone = "9174004242";

        // assert that the phone of the validContact matches the original before changing...
        Assertions.assertEquals(
                service.getContact(validContact.getId()).getPhone(),
                validContact.getPhone()
        );

        // update the phone
        service.updateContact(validContact.getId(),
                ContactService.ContactField.PHONE,
                newValidPhone
        );

        // assert that the name of the validContact now matches the new name
        Assertions.assertEquals(
                service.getContact(validContact.getId()).getPhone(),
                newValidPhone
        );
    }

    @Test
    public void cannotUpdateInvalidPhone() {
        var service = ContactService.getInstance();

        // storing the initial name of the validContact
        String initialPhone = service.getContact(validContact.getId()).getPhone();

        String invalidPhone = "123abc7890";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact(validContact.getId(),
                    ContactService.ContactField.PHONE,
                    invalidPhone);
        });

        // assert that the phone was not changed.
        Assertions.assertEquals(
                service.getContact(validContact.getId()).getPhone(),
                initialPhone
        );
    }

    // -----------------------------address update tests---------------------------------->
    @Test
    public void canUpdateValidAddress() {
        var service = ContactService.getInstance();

        String newValidAddress = "400 Broadway Ave";

        // assert that the address of the validContact matches the original before changing...
        Assertions.assertEquals(
                service.getContact(validContact.getId()).getAddress(),
                validContact.getAddress()
        );

        // update the address
        service.updateContact(validContact.getId(),
                ContactService.ContactField.ADDRESS,
                newValidAddress
        );

        // assert that the address of the validContact now matches the new one
        Assertions.assertEquals(
                service.getContact(validContact.getId()).getAddress(),
                newValidAddress
        );
    }

    @Test
    public void cannotUpdateInvalidAddress() {
        var service = ContactService.getInstance();

        // logging the initial address of the validContact
        String initialAddress = service.getContact(validContact.getId()).getAddress();

        String invalidAddress = "abc".repeat(11); // invalid as it's > 30 chars in length

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact(validContact.getId(),
                    ContactService.ContactField.ADDRESS,
                    invalidAddress);
        });

        // assert that the address was not changed.
        Assertions.assertEquals(
                service.getContact(validContact.getId()).getAddress(),
                initialAddress
        );
    }


    // -----------------------------Id Uniqueness---------------------------------->

    /*
     * About the uniqueness of Ids and how to test them.
     * The way I've written it, there are roughly 853 quadrillion unique strings that are available
     * for a contact's ID field. When a Contact is instantiated, and its constructor calls
     * ContactService's .getUniqueContactId(), the method checks for any matching ID strings within
     * the ContactService's "contactMap" map's keys, and will create a new one if the ID is already being used.
     * I'm unsure how to test for duplicate Ids as no Contact will be given a duplicate ID string,
     * and the Id is not assigned via input to the constructor and has no setter.
     * As a workaround I'm going to write a test case that creates a large number of Contacts,
     * stores each Contact's ID into a set, and in the event that an item already exists within
     * that set, the test will fail.
     * */
    public void contactIds() {
        var service = ContactService.getInstance();

        // the amount of Contacts to create
        // the highest value I tested was 2 million, and the test passed.
        int numOfContacts = 2000;

        // first I'm going to remove the validContact I've been using for the other tests from the map
        service.deleteContact(validContact.getId());

        // ContactService.contactMap table will be resizing during this test.
        // I don't want to set any size argument in the ContactService file, but I'll at least size
        // the idSet to the number of Contacts I'm going to create to mitigate performance hits from resizing/rehashing.
        Set<String> idSet = new HashSet<>(numOfContacts);

        // going to use all the same fields for each Contact. There's nothing preventing two Contacts
        // from having identical fields at present (which wasn't a requirement).
        var firstName = validContact.getFirstName();
        var lastName = validContact.getLastName();
        var phone = validContact.getPhone();
        var address = validContact.getAddress();

        for (int i = 0; i < numOfContacts; i++) {
            Contact c = new Contact(firstName, lastName, phone, address);

            // add the contact's id to the set
            if (!idSet.add(c.getId())) {
                // set.add() returns false if set already contains element.
                // this shouldn't be possible with how the service assigns IDs.
                throw new RuntimeException("ID: " + c.getId() + " already exists in set.");
            }
        }

        if (idSet.size() != numOfContacts) {
            throw new RuntimeException("idSet's size does not equal number of contacts");
        }

        System.out.println(idSet.size() + " contacts with unique IDs added.");
    }

    @Test
    public void checkIdsAreUnique() {
        contactIds();
    }

}
