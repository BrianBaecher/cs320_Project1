import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class AppointmentService {

    private static AppointmentService instance;

    private final Map<String, Appointment> appointmentMap;

    private AppointmentService() {
        appointmentMap = new HashMap<>();
    }

    public static AppointmentService getInstance() {
        if (instance == null) {
            instance = new AppointmentService();
        }

        return instance;
    }

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
