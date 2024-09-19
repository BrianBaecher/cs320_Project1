public class Contact {
    private final String Id;

    private String firstName;

    private String lastName;

    private String phone;

    private String address;

    // constructor
    public Contact(String firstName, String lastName, String phone, String address) {
        // TODO: contact service should create a unique ID. call method here and use value.
        this.Id = ContactService.getInstance().getUniqueContactId();

        boolean firstNameIsValid = tryRegisterName(firstName, true);
        System.out.println("firstName of " + firstName + " is valid? " + firstNameIsValid);

        boolean lastNameIsValid = tryRegisterName(lastName, false);
        System.out.println("lastName of " + lastName + " is valid? " + lastNameIsValid);

        boolean phoneIsValid = tryRegisterPhone(phone);
        System.out.println("phone of " + phone + " is valid? " + phoneIsValid);

        boolean addressIsValid = tryRegisterAddress(address);
        System.out.println("address of: " + address + " is valid? " + addressIsValid);

        if(firstNameIsValid && lastNameIsValid && phoneIsValid && addressIsValid){
            System.out.println("Trying to add contact?");
            ContactService.getInstance().addContact(this);
        }else{
            System.out.println("did not try to add contact?");
        }
    }


    private boolean tryRegisterName(String name, boolean isFirstName) {
        boolean success = false;

        // trim whitespace from firstName
        name = name.trim();
        try {
            if (isFirstName) {
                this.firstName = validateName(name, true);
            } else {
                this.lastName = validateName(name, false);
            }
            success = true;
        } catch (IllegalArgumentException illegalArg) {
            System.out.println(illegalArg.getMessage());
        }
        return success;
    }

    private String validateName(String name, boolean isFirstName) {
        // firstName String field that cannot be longer than 10 characters. The firstName field shall not be null.
        if (name == null || name.trim().isEmpty()) {
            if (isFirstName) {
                throw new IllegalArgumentException("firstName is null or empty String");
            }
            throw new IllegalArgumentException("lastName is null or empty String");
        }

        // check length of name.
        if (name.length() > 10) {
            String msg = "length of name: " + name + " exceeds 10 characters";
            throw new IllegalArgumentException(msg);
        }

        // if name contains any non-alphabetical char
        for (char c : name.toCharArray()) {
            if (!Character.isAlphabetic(c)) {
                String msg = "name: " + name + "contains non-alphabetical character";
                throw new IllegalArgumentException(msg);
            }
        }

        return name;
    }


    private boolean tryRegisterPhone(String phone) {
        boolean success = false;
        try {
            this.phone = validatePhone(phone);
            success = true;
        } catch (IllegalArgumentException illegalArg) {
            System.out.println(illegalArg.getMessage());
        }
        return success;
    }

    private String validatePhone(String phone) {
        //required phone String field must be exactly 10 digits. The phone field shall not be null.
        if (phone == null || phone.trim().length() != 10) {
            String msg = phone == null ? "phone field is null" : "phone string is not exactly 10 characters in length";
            throw new IllegalArgumentException(msg);
        }

        // trim whitespace if any
        phone = phone.trim();

        // ensure phone string only consists of numerical chars
        for (char c : phone.toCharArray()) {
            if (!Character.isDigit(c)) {
                String msg = "phone string contains non-numerical characters";
                throw new IllegalArgumentException(msg);
            }
        }

        return phone;
    }


    private boolean tryRegisterAddress(String address) {
        boolean success = false;
        try {
            this.address = validateAddress(address);
            success = true;
        } catch (IllegalArgumentException illegalArg) {
            System.out.println(illegalArg.getMessage());
        }
        return success;
    }

    private String validateAddress(String address) {
        //required address field must be no longer than 30 characters. The address field shall not be null.
        if (address == null || address.trim().length() > 30) {
            String msg = address == null ? "address field is null" : "address String exceeds 30 characters in length";
            throw new IllegalArgumentException(msg);
        }
        // the checks required in the rubric are sort of insufficient. To be sure that a valid address is entered,
        // I would think creating an Address class with its own validators would be a better approach.
        // however the only criteria given for a valid address in the rubric are that
        // it cannot be null, and it cannot be longer than 30 chars, so yeah...
        return address.trim();
    }


    // getters
    public String getId() {
        return this.Id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getAddress() {
        return this.address;
    }

    // setters
    public void setFirstName(String name) {
        tryRegisterName(name, true);
    }

    public void setLastName(String name) {
        tryRegisterName(name, false);
    }

    public void setPhone(String phone) {
        tryRegisterPhone(phone);
    }

    public void setAddress(String address) {
        tryRegisterAddress(address);
    }

    //TODO: remove debug stuff
    public void printInfo(){
        System.out.println("-----------------Contact printInfo()-----------------");
        System.out.println("Contact Information");
        System.out.println("Contact Id: " + this.getId());
        System.out.println("Contact First Name: " + this.getFirstName());
        System.out.println("Contact Last Name: " + this.getLastName());
        System.out.println("Contact Phone: " + this.getPhone());
        System.out.println("Contact Address: " + this.getAddress());
        System.out.println("------------------------------------------------------------------------");
    }

}
