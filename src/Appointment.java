import java.util.Date;
import java.time.LocalDate;

public class Appointment {

    private final String Id;

    private final LocalDate Date;

    private final String Description;

    public Appointment(LocalDate date, String description){

        if(date == null){
            throw new IllegalArgumentException("date is null");
        }

        if (description == null || description.trim().isEmpty()){
            throw new IllegalArgumentException("description is null or empty string");
        }

        // The appointment Date field cannot be in the past.
        if (date.isBefore(LocalDate.now())){
            // exception
            throw new IllegalArgumentException("date is in the past");
        }

        //required description String field that cannot be longer than 50 characters
        if(description.length() > 50){
            throw new IllegalArgumentException("description greater than 50 chars in length");
        }

        // fields valid, create object...
        description = description.trim();
        var service = AppointmentService.getInstance();

        Id = service.getUniqueAppointmentId();

        this.Date = date;

        Description = description;

        // add appointment to service storage.
        service.addAppointment(this);
    }

    // getter
    public String getId(){
        return Id;
    }

    public LocalDate getDate(){
        return Date;
    }

    public String getDescription() {
        return Description;
    }

    // rubric doesn't say that appointment dates, or descriptions should be able to be modified. so no setters...
}
