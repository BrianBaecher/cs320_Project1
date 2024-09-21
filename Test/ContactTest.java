import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ContactTest {

    // NOTE - I have ContactService responsible for managing Ids,
    // so the uniqueness of Ids is tested in ContactServiceTest class.

    String validFirstName = "John";
    String validLastName = "Doe";
    String validPhone = "1234567890";
    String validAddress = "123 main street";

    // --------------------------------------------Contact.firstName tests-------------------------------------------->
    @Test
    public void firstNameTooLong() {
        String firstName = "overTenCharacters";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(firstName, validLastName, validPhone, validAddress);
        });
    }

    @Test
    public void firstNameIsNull() {
        String firstName = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(firstName, validLastName, validPhone, validAddress);
        });
    }

    @Test
    public void firstNameIsEmpty(){
        String firstName = "   ";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(firstName, validLastName, validPhone, validAddress);
        });
    }

    @Test
    public void firstNameContainsNonAlphabeticalChar(){
        String firstName = "Jo-hn";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(firstName, validLastName, validPhone, validAddress);
        });
    }

    // --------------------------------------------Contact.lastName tests-------------------------------------------->
    @Test
    public void lastNameTooLong() {
        String lastName = "overTenCharacters";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validFirstName, lastName, validPhone, validAddress);
        });
    }

    @Test
    public void lastNameIsNull() {
        String lastName = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validFirstName, lastName, validPhone, validAddress);
        });
    }

    @Test
    public void lastNameIsEmpty(){
        String lastName = "   ";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validFirstName, lastName, validPhone, validAddress);
        });
    }

    @Test
    public void lastNameContainsNonAlphabeticalChar(){
        String lastName = "Jo-hn";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validFirstName, lastName, validPhone, validAddress);
        });
    }

    // --------------------------------------------Contact.phone tests-------------------------------------------->

    @Test
    public void phoneLessThanTen(){
        String phone = "1234567";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validFirstName, validLastName, phone, validAddress);
        });
    }

    @Test
    public void phoneGreaterThanTen(){
        String phone = "12345678901";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validFirstName, validLastName, phone, validAddress);
        });
    }

    @Test
    public void phoneContainsNonDigitChar(){
        String phone = "123a567890";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validFirstName, validLastName, phone, validAddress);
        });
    }

    @Test
    public void phoneIsNull(){
        String phone = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validFirstName, validLastName, phone, validAddress);
        });
    }

    @Test
    public void phoneIsEmptyString(){
        // ten spaces (" ") are trimmed to an empty string, then fails length == 10 check
        String phone = " ".repeat(10);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validFirstName, validLastName, phone, validAddress);
        });
    }

    // --------------------------------------------Contact.address tests-------------------------------------------->
    @Test
    public void addressGreaterThanThirtyChars(){
        String address = "this string has a length greater than 30 characters";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validFirstName, validLastName, validPhone, address);
        });
    }

    @Test
    public void addressIsNull(){
        String address = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validFirstName, validLastName, validPhone, address);
        });
    }

    @Test
    public void addressIsEmptyString(){
        String address = " ".repeat(20);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Contact(validFirstName, validLastName, validPhone, address);
        });
    }
}
