// Import Packages
package model;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {
    private final SimpleIntegerProperty appointmentID = new SimpleIntegerProperty();
    private final SimpleIntegerProperty appointmentCustomerID = new SimpleIntegerProperty();
    private final SimpleStringProperty appointmentStart = new SimpleStringProperty();
    private final SimpleStringProperty appointmentEnd = new SimpleStringProperty();
    private final SimpleStringProperty appointmentTitle = new SimpleStringProperty();
    private final SimpleStringProperty appointmentDescription = new SimpleStringProperty();
    private final SimpleStringProperty appointmentLocation = new SimpleStringProperty();
    private final SimpleStringProperty appointmentContact = new SimpleStringProperty();
    
    public Appointment(int id, int custId, String start, String end, String title, String description, String location, String contact) {
        setAppointmentID(id);
        setAppointmentCustomerID(custId);
        setAppointmentStart(start);
        setAppointmentEnd(end);
        setAppointmentTitle(title);
        setAppointmentDescription(description);
        setAppointmentLocation(location);
        setAppointmentContact(contact);
    }
    
    // Getters for Appointment class
    public int getAppointmentID() {
        return appointmentID.get();
    }
    
    public int getAppointmentCustomerID() {
        return appointmentCustomerID.get();
    }
    
    public String getAppointmentEnd() {
        return appointmentEnd.get();
    }
    
    public String getAppointmentStart() {
        return appointmentStart.get();
    }
    
    public String getAppointmentTitle() {
        return appointmentTitle.get();
    }
    
    public String getAppointmentDescription() {
        return appointmentDescription.get();
    }
    
    public String getAppointmentLocation() {
        return appointmentLocation.get();
    }
    
    public String getAppointmentContact() {
        return appointmentContact.get();
    }
    
    public StringProperty getAppointmentTitleProperty() {
        return this.appointmentTitle;
    }
    
    public StringProperty getAppointmentDescriptionProperty() {
        return this.appointmentDescription;
    }
    
    public StringProperty getAppointmentLocationProperty() {
        return this.appointmentLocation;
    }
    
    public StringProperty getAppointmentContactProperty() {
        return this.appointmentContact;
    }
    
    // Setters for Appointment Class
    public void setAppointmentID(int aptId) {
        this.appointmentID.set(aptId);
    }
    
    public void setAppointmentCustomerID(int aptCustId) {
        this.appointmentCustomerID.set(aptCustId);
    }
    
    public void setAppointmentEnd(String aptEnd) {
        this.appointmentEnd.set(aptEnd);
    }
    
    public void setAppointmentStart(String aptTimeStart) {
        this.appointmentStart.set(aptTimeStart);
    } 
    
    public void setAppointmentTitle(String aptTitle) {
        this.appointmentTitle.set(aptTitle);
    }
    
    public void setAppointmentDescription(String aptDescription) {
        this.appointmentDescription.set(aptDescription);
    }
    
    public void setAppointmentLocation(String aptLocation) {
        this.appointmentLocation.set(aptLocation);
    }
    
    public void setAppointmentContact(String aptContact) {
        this.appointmentContact.set(aptContact);
    }
    
    // Set Apointment Start
    public StringProperty getAppointmentStartProperty() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
 	LocalDateTime ldt = LocalDateTime.parse(this.appointmentStart.getValue(), df);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("UTC"));
        ZoneId zid = ZoneId.systemDefault();
        ZonedDateTime utcDate = zdt.withZoneSameInstant(zid); 
        StringProperty date = new SimpleStringProperty(utcDate.toLocalDateTime().toString());
        return date;
    }
    
    // Set Apointment End
        public StringProperty getAppointmentEndProperty() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
 	LocalDateTime ldt = LocalDateTime.parse(this.appointmentEnd.getValue(), df);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("UTC"));
        ZoneId zid = ZoneId.systemDefault();
        ZonedDateTime utcDate = zdt.withZoneSameInstant(zid); 
        StringProperty date = new SimpleStringProperty(utcDate.toLocalDateTime().toString());
        return date;
    }
   
    // Set Appointment Date
    public LocalDate getDateOnly() {
        Timestamp ts = Timestamp.valueOf(this.appointmentStart.get());
        ZonedDateTime zdt;
        ZoneId zid;
        LocalDate ld;
        if(this.appointmentLocation.get().equals("New York")) {
            zid = ZoneId.of("America/New_York");
        } else if(this.appointmentLocation.get().equals("Phoenix")) {
            zid = ZoneId.of("America/Phoenix");
        } else {
            zid = ZoneId.of("Europe/London");
        }
        zdt = ts.toLocalDateTime().atZone(zid);
        ld = zdt.toLocalDate();
        return ld;
    }
    
    // Set Appointment Time
    public String getTimeOnly() {
        Timestamp ts = Timestamp.valueOf(this.appointmentStart.get());
        ZonedDateTime zdt;
        ZoneId zid;
        LocalTime lt;
        if(this.appointmentLocation.get().equals("New York")) {
            zid = ZoneId.of("America/New_York");
            zdt = ts.toLocalDateTime().atZone(zid);
            lt = zdt.toLocalTime().minusHours(4);
        } else if(this.appointmentLocation.get().equals("Phoenix")) {
            zid = ZoneId.of("America/Phoenix");
            zdt = ts.toLocalDateTime().atZone(zid);
            lt = zdt.toLocalTime().minusHours(7);
        } else {
            zid = ZoneId.of("Europe/London");
            zdt = ts.toLocalDateTime().atZone(zid);
            lt = zdt.toLocalTime().plusHours(1);
        }
        int rawH = Integer.parseInt(lt.toString().split(":")[0]);
        if(rawH > 12) {
            rawH -= 12;
        }
        String ampm;
        if(rawH < 9 || rawH == 12) {
            ampm = "PM";
        } else {
            ampm = "AM";
        }
        String time = rawH + ":00 " + ampm;
        return time;
    }
    
    // 15 Minute Alert System
    public String get15Time() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
 	LocalDateTime ldt = LocalDateTime.parse(this.appointmentStart.getValue(), df);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("UTC"));
        ZoneId zid = ZoneId.systemDefault();
        ZonedDateTime utcDate = zdt.withZoneSameInstant(zid); 
        DateTimeFormatter tFormatter = DateTimeFormatter.ofPattern("kk:mm"); 
	LocalTime localTime = LocalTime.parse(utcDate.toString().substring(11,16), tFormatter);
        return localTime.toString();
    }
}
