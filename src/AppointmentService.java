/*
Author: Brian Baecher
Date: 10/4/2024
Course ID: CS-320-13376-M01
Description: The AppointmentService class.
*/

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class AppointmentService {

    // singleton instance
    private static AppointmentService instance;

    // data store for Appointment objects
    private final Map<String, Appointment> appointmentMap;

    // private ctor
    private AppointmentService() {
        appointmentMap = new HashMap<>();
    }

    // public access to singleton instance
    public static AppointmentService getInstance() {
        if (instance == null) {
            instance = new AppointmentService();
        }

        return instance;
    }

    // creates Id for an Appointment instance.
    public String getUniqueAppointmentId() {
        String uniqueId;
        do {
            uniqueId = UtilityClasses.IdGenerator.generateRandomId();
        } while (appointmentMap.containsKey(uniqueId));

        return uniqueId;
    }

    public Appointment getAppointment(String appointmentId) {
        return appointmentMap.get(appointmentId);
    }

    public void addAppointment(Appointment appointment) {
        appointmentMap.put(appointment.getId(), appointment);
    }

    public void deleteAppointment(String appointmentId) {
        if (appointmentMap.remove(appointmentId) == null) {
            throw new NoSuchElementException("cannot remove appointment with id: " + appointmentId + ", no matching id found in store");
        }
    }
}
