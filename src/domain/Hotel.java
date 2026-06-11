package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the hotel and manages rooms, reservations, guests, and employees.
 */
public class Hotel implements Serializable {

    private String hotelName;
    private String hotelAddress;
    private String hotelEmail;
    private long hotelPhoneNumber;
    private List<Room> rooms;
    private List<Reservation> reservations;
    private List<Guest> guests;
    private List<Employee> employees;

    /**
     * Creates an empty hotel with all internal lists initialized.
     */
    public Hotel() {
        this.hotelName = "Unnamed Hotel";
        this.hotelAddress = "not-defined";
        this.hotelEmail = "not-defined";
        this.hotelPhoneNumber = 0;
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.guests = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public long getHotelPhoneNumber() {
        return hotelPhoneNumber;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName == null || hotelName.isBlank() ? "Unnamed Hotel" : hotelName.trim();
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress == null || hotelAddress.isBlank() ? "not-defined" : hotelAddress.trim();
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail == null || hotelEmail.isBlank() ? "not-defined" : hotelEmail.trim();
    }

    public void setHotelPhoneNumber(long hotelPhoneNumber) {
        this.hotelPhoneNumber = hotelPhoneNumber > 0 ? hotelPhoneNumber : 0;
    }

    /**
     * Registers a new room if its room number is not already used.
     */
    public boolean addRoom(Room room) {
        if (findRoomByNumber(room.getRoomNumber()) != null) {
            return false;
        }
        rooms.add(room);
        return true;
    }

    /**
     * Deletes an available room by room number.
     */
    public boolean deleteRoom(int roomNumber) {
        Room room = findRoomByNumber(roomNumber);
        if (room == null || !room.isAvailable()) {
            return false;
        }
        return rooms.remove(room);
    }

    /**
     * Searches a room by its room number.
     */
    public Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    /**
     * Returns all rooms that are currently available.
     */
    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    /**
     * Registers a reservation and stores the guest in the guest record.
     */
    public boolean addReservation(Reservation reservation) {
        if (reservation.getRoom() == null || !reservations.add(reservation)) {
            return false;
        }
        guests.add(reservation.getGuest());
        return true;
    }

    /**
     * Searches a reservation by its unique reservation number.
     */
    public Reservation findReservationByNumber(int reservationNumber) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationNumber() == reservationNumber) {
                return reservation;
            }
        }
        return null;
    }

    /**
     * Deletes a reservation and releases its room.
     */
    public boolean deleteReservation(int reservationNumber) {
        Reservation reservation = findReservationByNumber(reservationNumber);
        if (reservation == null) {
            return false;
        }
        reservation.getRoom().setAvailable(true);
        return reservations.remove(reservation);
    }

    /**
     * Registers a new employee if the document ID is not already used.
     */
    public boolean addEmployee(Employee employee) {
        if (findEmployeeByDocumentId(employee.getDocumentId()) != null) {
            return false;
        }
        employees.add(employee);
        return true;
    }

    /**
     * Searches an employee by employee number.
     */
    public Employee findEmployeeByNumber(int employeeNumber) {
        for (Employee employee : employees) {
            if (employee.getEmployeeNumber() == employeeNumber) {
                return employee;
            }
        }
        return null;
    }

    /**
     * Searches an employee by document ID.
     */
    public Employee findEmployeeByDocumentId(long documentId) {
        for (Employee employee : employees) {
            if (employee.getDocumentId() == documentId) {
                return employee;
            }
        }
        return null;
    }

    /**
     * Deletes an employee from the employee list.
     */
    public boolean deleteEmployee(int employeeNumber) {
        Employee employee = findEmployeeByNumber(employeeNumber);
        if (employee == null) {
            return false;
        }
        return employees.remove(employee);
    }

    /**
     * Searches a guest by document ID.
     */
    public Guest findGuestByDocumentId(long documentId) {
        for (Guest guest : guests) {
            if (guest.getDocumentId() == documentId) {
                return guest;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Hotel{name='" + hotelName + '\'' + ", address='" + hotelAddress + '\'' +
                ", email='" + hotelEmail + '\'' + ", phoneNumber=" + hotelPhoneNumber + '}';
    }
}
