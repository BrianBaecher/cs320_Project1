/*
Author: Brian Baecher
Date: 10/4/2024
Course ID: CS-320-13376-M01
Description: Junit tests for the AppointmentService class.
*/

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.NoSuchElementException;

public class AppointmentServiceTest {
    @Test
    public void canAddAppointment(){
        var service = AppointmentService.getInstance();
        // create a valid appointment.
        Appointment appointment = new Appointment(
                LocalDate.of(2025,1,1),
                "A valid description.");

        // assert that getAppointment returns the same appointment object...
        // demonstrating that the appointment was added to AppointmentService's store.
        Assertions.assertEquals(
                appointment,
                service.getAppointment(appointment.getId())
        );
    }

    @Test
    public void canDeleteAppointment(){
        var service = AppointmentService.getInstance();

        // create a valid appointment.
        Appointment appointment = new Appointment(
                LocalDate.of(2025,1,1),
                "A valid description.");

        // call service's delete method.
        service.deleteAppointment(appointment.getId());

        // assert service.getAppointment returns null (meaning no longer stored).
        Assertions.assertNull(service.getAppointment(appointment.getId()));

        // trying to delete a non-existent appointment should result in NoSuchElement
        Assertions.assertThrows(NoSuchElementException.class, ()->{
            service.deleteAppointment(appointment.getId());
        });
    }
}
