package model.entities;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import model.exceptions.DomainException;

public class Reservation {
 private Integer roomNumber;
 private Date checkIn;
 private Date checkOut;
 
 private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 
 public Reservation() {
	
 }
 
public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException{
    if(!checkOut.after(checkIn)) {
    	throw new DomainException("Check-out date must be after check-in date.");
    }
	this.roomNumber = roomNumber;
	this.checkIn = checkIn;
	this.checkOut = checkOut;
}
public Integer getRoomNumber() {
	return roomNumber;
}
public void setRoomNumber(Integer roomNumber) {
	this.roomNumber = roomNumber;
}
public Date getCheckIn() {
	return checkIn;
}

public Date getCheckOut() {
	return checkOut;
}

public Integer duration() {
	Duration dur = Duration.between(checkIn.toInstant(), checkOut.toInstant());
	return (int) dur.toDays();
}
public void updateDates(Date checkIn, Date checkOut) throws DomainException {
	
    Date now = new Date();
    if(checkIn.before(now) || checkOut.before(now)) {
    	 throw new DomainException("Reservation dates for update must be future dates!");
    }
    if(!checkOut.after(checkIn)) {
    	throw new DomainException("Check-out date must be after check-in date.");
    }
	this.checkIn = checkIn;
	this.checkOut = checkOut;
}

@Override
public String toString() {
 return "Room " + roomNumber + ", check-in: "+ sdf.format(checkIn) + ", check-out: " + sdf.format(checkOut)+ ", "+ duration()+ " nights.";
}

}
