package domain;

import java.io.Serializable;

/**
 * Represents a reservation that connects one guest with one room and date range.
 */
public class Reservation implements Serializable {

    private static int nextReservationNumber = 0;
    private int reservationNumber;
    private Guest guest;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private boolean active;

    /**
     * Creates an active reservation with an automatic reservation number.
     */
    public Reservation(Guest guest, Room room, String checkInDate, String checkOutDate) {
        nextReservationNumber++;
        this.reservationNumber = nextReservationNumber;
        setGuest(guest);
        setRoom(room);
        setCheckInDate(checkInDate);
        setCheckOutDate(checkOutDate);
        this.active = true;
        this.room.setAvailable(false);
    }

    public static int getNextReservationNumber() {
        return nextReservationNumber;
    }

    public static void setNextReservationNumber(int nextReservationNumber) {
        Reservation.nextReservationNumber = nextReservationNumber;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate == null || checkInDate.isBlank() ? "not-defined" : checkInDate.trim();
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate == null || checkOutDate.isBlank() ? "not-defined" : checkOutDate.trim();
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Cancels the reservation and releases the assigned room.
     */
    public void cancel() {
        this.active = false;
        this.room.setAvailable(true);
    }

    @Override
    public String toString() {
        return "Reservation{reservationNumber=" + reservationNumber +
                ", roomNumber=" + room.getRoomNumber() + ", guest='" + guest.getFullName() + '\'' +
                ", checkInDate='" + checkInDate + '\'' + ", checkOutDate='" + checkOutDate + '\'' +
                ", active=" + active + '}';
    }
}
