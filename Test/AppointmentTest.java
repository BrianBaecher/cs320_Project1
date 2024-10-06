/*
Author: Brian Baecher
Date: 10/4/2024
Course ID: CS-320-13376-M01
Description: Junit tests for the Appointment class.
*/

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

public class AppointmentTest {
    @Test
    public void invalidDateForAppointment() {
        // create a date that is in the past.
        LocalDate invalidDate = LocalDate.of(1984, 1, 1);
        String validDesc = "A description.";

        // ensure dates not in the future throw exception
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(invalidDate, validDesc);
        });
    }

    @Test
    public void dateIsNull(){
        LocalDate date = null;
        String validDesc = "A description.";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(date, validDesc);
        });
    }

    @Test
    public void descriptionTooLong(){
        // a string greater than 50 chars
        String badDesc = "too long".repeat(10);

        Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            new Appointment(LocalDate.of(2025, 1, 1), badDesc);
        });
    }

    @Test
    public void descriptionEmpty(){
        String badDesc = " ".repeat(10);

        Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            new Appointment(LocalDate.of(2025, 1, 1), badDesc);
        });
    }

    @Test
    public void descriptionIsNull(){
        String badDesc = null;

        Assertions.assertThrows(IllegalArgumentException.class, ()-> {
            new Appointment(LocalDate.of(2025, 1, 1), badDesc);
        });
    }
}
