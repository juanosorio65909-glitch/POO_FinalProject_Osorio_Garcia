package ui;

import data.HotelStorage;
import domain.Employee;
import domain.Guest;
import domain.Hotel;
import domain.Reservation;
import domain.Room;
import domain.RoomRecommendationSystem;

/**
 * Runs the internal hotel management system through a text console menu.
 */
public class Main {

    private static final String FILE_NAME = "HotelSystem.dat";
    private static Hotel currentHotel = new Hotel();
    private static final RoomRecommendationSystem ROOM_RECOMMENDATION_SYSTEM = new RoomRecommendationSystem();

    /**
     * Starts the program, loads saved data, and controls the main menu loop.
     */
    public static void main(String[] args) {
        loadHotel();
        boolean running = true;
        while (running) {
            showMainMenu();
            int option = Console.readInt("- Enter an option: ");
            switch (option) {
                case 1 -> configureHotelInformation();
                case 2 -> manageRooms();
                case 3 -> manageReservations();
                case 4 -> manageEmployees();
                case 5 -> showRecordsAndSearches();
                case 6 -> showRoomRecommendation();
                case 7 -> {
                    saveHotel();
                    Console.writeLine("- Application closed.");
                    running = false;
                }
                default -> Console.writeLine("- Invalid option. Try again.");
            }
        }
    }

    /**
     * Displays the main system menu.
     */
    private static void showMainMenu() {
        Console.writeLine("\n- - - HOTEL MANAGEMENT SYSTEM - - -");
        Console.writeLine("(1) Configure hotel information");
        Console.writeLine("(2) Manage rooms");
        Console.writeLine("(3) Manage reservations");
        Console.writeLine("(4) Manage employees");
        Console.writeLine("(5) Records and searches");
        Console.writeLine("(6) Room recommendation");
        Console.writeLine("(7) Save and exit\n");
    }

    /**
     * Updates the basic hotel information.
     */
    private static void configureHotelInformation() {
        currentHotel.setHotelName(Console.readLine("- Hotel name: "));
        currentHotel.setHotelAddress(Console.readLine("- Hotel address: "));
        currentHotel.setHotelEmail(Console.readLine("- Hotel email: "));
        currentHotel.setHotelPhoneNumber(Console.readLong("- Hotel phone number: "));
        saveHotel();
        Console.writeLine("- Hotel information updated.");
    }

    /**
     * Controls the room management menu.
     */
    private static void manageRooms() {
        boolean running = true;
        while (running) {
            Console.writeLine("\n- - - ROOM MENU - - -");
            Console.writeLine("(1) Add room");
            Console.writeLine("(2) List rooms");
            Console.writeLine("(3) Search room");
            Console.writeLine("(4) Update room");
            Console.writeLine("(5) Delete room");
            Console.writeLine("(6) Go back\n");
            int option = Console.readInt("- Enter an option: ");
            switch (option) {
                case 1 -> addRoom();
                case 2 -> listRooms();
                case 3 -> searchRoom();
                case 4 -> updateRoom();
                case 5 -> deleteRoom();
                case 6 -> running = false;
                default -> Console.writeLine("- Invalid option. Try again.");
            }
        }
    }

    /**
     * Registers a new room in the hotel.
     */
    private static void addRoom() {
        int roomNumber = Console.readInt("- Room number: ");
        int bedCount = Console.readInt("- Bed count: ");
        int bathCount = Console.readInt("- Bath count: ");
        String category = Console.readLine("- Category: ");
        double nightlyRate = Console.readDouble("- Nightly rate: ");
        boolean added = currentHotel.addRoom(new Room(roomNumber, bedCount, bathCount, category, nightlyRate));
        Console.writeLine(added ? "- Room added successfully." : "- Room number already exists.");
        saveHotel();
    }

    /**
     * Lists all registered rooms.
     */
    private static void listRooms() {
        if (currentHotel.getRooms().isEmpty()) {
            Console.writeLine("- No rooms registered.");
            return;
        }
        for (Room room : currentHotel.getRooms()) {
            Console.writeLine(room);
        }
    }

    /**
     * Searches and prints one room by room number.
     */
    private static void searchRoom() {
        int roomNumber = Console.readInt("- Room number to search: ");
        Room room = currentHotel.findRoomByNumber(roomNumber);
        Console.writeLine(room == null ? "- Room not found." : room);
    }

    /**
     * Updates the editable information of a registered room.
     */
    private static void updateRoom() {
        int roomNumber = Console.readInt("- Room number to update: ");
        Room room = currentHotel.findRoomByNumber(roomNumber);
        if (room == null) {
            Console.writeLine("- Room not found.");
            return;
        }
        room.setBedCount(Console.readInt("- New bed count: "));
        room.setBathCount(Console.readInt("- New bath count: "));
        room.setCategory(Console.readLine("- New category: "));
        room.setNightlyRate(Console.readDouble("- New nightly rate: "));
        saveHotel();
        Console.writeLine("- Room updated successfully.");
    }

    /**
     * Deletes an available room from the system.
     */
    private static void deleteRoom() {
        int roomNumber = Console.readInt("- Room number to delete: ");
        boolean deleted = currentHotel.deleteRoom(roomNumber);
        Console.writeLine(deleted ? "- Room deleted successfully." : "- Room not found or currently reserved.");
        saveHotel();
    }

    /**
     * Controls the reservation management menu.
     */
    private static void manageReservations() {
        boolean running = true;
        while (running) {
            Console.writeLine("\n- - - RESERVATION MENU - - -");
            Console.writeLine("(1) Add reservation");
            Console.writeLine("(2) List reservations");
            Console.writeLine("(3) Search reservation");
            Console.writeLine("(4) Update reservation");
            Console.writeLine("(5) Cancel reservation");
            Console.writeLine("(6) Delete reservation");
            Console.writeLine("(7) Go back\n");
            int option = Console.readInt("- Enter an option: ");
            switch (option) {
                case 1 -> addReservation();
                case 2 -> listReservations();
                case 3 -> searchReservation();
                case 4 -> updateReservation();
                case 5 -> cancelReservation();
                case 6 -> deleteReservation();
                case 7 -> running = false;
                default -> Console.writeLine("- Invalid option. Try again.");
            }
        }
    }

    /**
     * Registers a new reservation and its guest.
     */
    private static void addReservation() {
        if (currentHotel.getAvailableRooms().isEmpty()) {
            Console.writeLine("- There are no available rooms.");
            return;
        }
        Guest guest = readGuestInformation();
        listAvailableRooms();
        int roomNumber = Console.readInt("- Room number for the reservation: ");
        Room room = currentHotel.findRoomByNumber(roomNumber);
        if (room == null || !room.isAvailable()) {
            Console.writeLine("- Selected room is not available.");
            return;
        }
        String checkInDate = Console.readLine("- Check-in date: ");
        String checkOutDate = Console.readLine("- Check-out date: ");
        currentHotel.addReservation(new Reservation(guest, room, checkInDate, checkOutDate));
        saveHotel();
        Console.writeLine("- Reservation added successfully.");
    }

    /**
     * Lists all reservations in the system.
     */
    private static void listReservations() {
        if (currentHotel.getReservations().isEmpty()) {
            Console.writeLine("- No reservations registered.");
            return;
        }
        for (Reservation reservation : currentHotel.getReservations()) {
            Console.writeLine(reservation);
        }
    }

    /**
     * Searches and prints one reservation by reservation number.
     */
    private static void searchReservation() {
        int reservationNumber = Console.readInt("- Reservation number to search: ");
        Reservation reservation = currentHotel.findReservationByNumber(reservationNumber);
        Console.writeLine(reservation == null ? "- Reservation not found." : reservation);
    }

    /**
     * Updates guest, date, or room data of an existing reservation.
     */
    private static void updateReservation() {
        int reservationNumber = Console.readInt("- Reservation number to update: ");
        Reservation reservation = currentHotel.findReservationByNumber(reservationNumber);
        if (reservation == null) {
            Console.writeLine("- Reservation not found.");
            return;
        }
        Console.writeLine("(1) Guest name");
        Console.writeLine("(2) Guest email");
        Console.writeLine("(3) Guest phone number");
        Console.writeLine("(4) Guest document ID");
        Console.writeLine("(5) Dates");
        Console.writeLine("(6) Room");
        int option = Console.readInt("- Field to update: ");
        switch (option) {
            case 1 -> reservation.getGuest().setFullName(Console.readLine("- New guest name: "));
            case 2 -> reservation.getGuest().setEmail(Console.readLine("- New guest email: "));
            case 3 -> reservation.getGuest().setPhoneNumber(Console.readLong("- New guest phone number: "));
            case 4 -> reservation.getGuest().setDocumentId(Console.readLong("- New guest document ID: "));
            case 5 -> {
                reservation.setCheckInDate(Console.readLine("- New check-in date: "));
                reservation.setCheckOutDate(Console.readLine("- New check-out date: "));
            }
            case 6 -> changeReservationRoom(reservation);
            default -> Console.writeLine("- Invalid option.");
        }
        saveHotel();
        Console.writeLine("- Reservation update finished.");
    }

    /**
     * Changes the room assigned to a reservation if the new room is available.
     */
    private static void changeReservationRoom(Reservation reservation) {
        listAvailableRooms();
        int newRoomNumber = Console.readInt("- New room number: ");
        Room newRoom = currentHotel.findRoomByNumber(newRoomNumber);
        if (newRoom == null || !newRoom.isAvailable()) {
            Console.writeLine("- New room is not available.");
            return;
        }
        reservation.getRoom().setAvailable(true);
        newRoom.setAvailable(false);
        reservation.setRoom(newRoom);
    }

    /**
     * Cancels a reservation without deleting its historical record.
     */
    private static void cancelReservation() {
        int reservationNumber = Console.readInt("- Reservation number to cancel: ");
        Reservation reservation = currentHotel.findReservationByNumber(reservationNumber);
        if (reservation == null) {
            Console.writeLine("- Reservation not found.");
            return;
        }
        reservation.cancel();
        saveHotel();
        Console.writeLine("- Reservation canceled successfully.");
    }

    /**
     * Deletes a reservation from the system.
     */
    private static void deleteReservation() {
        int reservationNumber = Console.readInt("- Reservation number to delete: ");
        boolean deleted = currentHotel.deleteReservation(reservationNumber);
        Console.writeLine(deleted ? "- Reservation deleted successfully." : "- Reservation not found.");
        saveHotel();
    }

    /**
     * Controls the employee management menu.
     */
    private static void manageEmployees() {
        boolean running = true;
        while (running) {
            Console.writeLine("\n- - - EMPLOYEE MENU - - -");
            Console.writeLine("(1) Add employee");
            Console.writeLine("(2) List employees");
            Console.writeLine("(3) Search employee");
            Console.writeLine("(4) Update employee");
            Console.writeLine("(5) Dismiss employee");
            Console.writeLine("(6) Delete employee");
            Console.writeLine("(7) Go back\n");
            int option = Console.readInt("- Enter an option: ");
            switch (option) {
                case 1 -> addEmployee();
                case 2 -> listEmployees();
                case 3 -> searchEmployee();
                case 4 -> updateEmployee();
                case 5 -> dismissEmployee();
                case 6 -> deleteEmployee();
                case 7 -> running = false;
                default -> Console.writeLine("- Invalid option. Try again.");
            }
        }
    }

    /**
     * Registers a new employee in the hotel.
     */
    private static void addEmployee() {
        String fullName = Console.readLine("- Employee full name: ");
        String email = Console.readLine("- Employee email: ");
        long phoneNumber = Console.readLong("- Employee phone number: ");
        long documentId = Console.readLong("- Employee document ID: ");
        String position = Console.readLine("- Employee position: ");
        double salary = Console.readDouble("- Monthly salary: ");
        boolean added = currentHotel.addEmployee(new Employee(fullName, email, phoneNumber, documentId, position, salary));
        Console.writeLine(added ? "- Employee added successfully." : "- Employee document ID already exists.");
        saveHotel();
    }

    /**
     * Lists all employees.
     */
    private static void listEmployees() {
        if (currentHotel.getEmployees().isEmpty()) {
            Console.writeLine("- No employees registered.");
            return;
        }
        for (Employee employee : currentHotel.getEmployees()) {
            Console.writeLine(employee);
        }
    }

    /**
     * Searches and prints one employee by employee number.
     */
    private static void searchEmployee() {
        int employeeNumber = Console.readInt("- Employee number to search: ");
        Employee employee = currentHotel.findEmployeeByNumber(employeeNumber);
        Console.writeLine(employee == null ? "- Employee not found." : employee);
    }

    /**
     * Updates editable employee information.
     */
    private static void updateEmployee() {
        int employeeNumber = Console.readInt("- Employee number to update: ");
        Employee employee = currentHotel.findEmployeeByNumber(employeeNumber);
        if (employee == null) {
            Console.writeLine("- Employee not found.");
            return;
        }
        employee.setFullName(Console.readLine("- New full name: "));
        employee.setEmail(Console.readLine("- New email: "));
        employee.setPhoneNumber(Console.readLong("- New phone number: "));
        employee.setPosition(Console.readLine("- New position: "));
        employee.setMonthlySalary(Console.readDouble("- New monthly salary: "));
        saveHotel();
        Console.writeLine("- Employee updated successfully.");
    }

    /**
     * Marks an employee as inactive.
     */
    private static void dismissEmployee() {
        int employeeNumber = Console.readInt("- Employee number to dismiss: ");
        Employee employee = currentHotel.findEmployeeByNumber(employeeNumber);
        if (employee == null) {
            Console.writeLine("- Employee not found.");
            return;
        }
        employee.dismiss();
        saveHotel();
        Console.writeLine("- Employee dismissed successfully.");
    }

    /**
     * Deletes an employee from the system.
     */
    private static void deleteEmployee() {
        int employeeNumber = Console.readInt("- Employee number to delete: ");
        boolean deleted = currentHotel.deleteEmployee(employeeNumber);
        Console.writeLine(deleted ? "- Employee deleted successfully." : "- Employee not found.");
        saveHotel();
    }

    /**
     * Shows general records and guest search options.
     */
    private static void showRecordsAndSearches() {
        Console.writeLine("\n- - - RECORDS - - -");
        Console.writeLine(currentHotel);
        Console.writeLine("\nRooms: " + currentHotel.getRooms().size());
        Console.writeLine("Reservations: " + currentHotel.getReservations().size());
        Console.writeLine("Guests: " + currentHotel.getGuests().size());
        Console.writeLine("Employees: " + currentHotel.getEmployees().size());
        long documentId = Console.readLong("\n- Guest document ID to search (0 to skip): ");
        if (documentId != 0) {
            Guest guest = currentHotel.findGuestByDocumentId(documentId);
            Console.writeLine(guest == null ? "- Guest not found." : guest);
        }
    }

    /**
     * Uses the recommendation system to suggest a room and classify occupancy.
     */
    private static void showRoomRecommendation() {
        int requiredBeds = Console.readInt("- Required bed count: ");
        double maximumBudget = Console.readDouble("- Maximum nightly budget (0 for no limit): ");
        Room recommendedRoom = ROOM_RECOMMENDATION_SYSTEM.recommendRoom(currentHotel, requiredBeds, maximumBudget);
        Console.writeLine("- Occupancy classification: " + ROOM_RECOMMENDATION_SYSTEM.classifyOccupancy(currentHotel));
        Console.writeLine(recommendedRoom == null ? "- No room matches the requested conditions." : "- Recommended room: " + recommendedRoom);
    }

    /**
     * Reads shared guest data from the console.
     */
    private static Guest readGuestInformation() {
        String fullName = Console.readLine("- Guest full name: ");
        String email = Console.readLine("- Guest email: ");
        long phoneNumber = Console.readLong("- Guest phone number: ");
        long documentId = Console.readLong("- Guest document ID: ");
        return new Guest(fullName, email, phoneNumber, documentId);
    }

    /**
     * Lists only rooms that can currently be reserved.
     */
    private static void listAvailableRooms() {
        Console.writeLine("\n- - - AVAILABLE ROOMS - - -");
        for (Room room : currentHotel.getAvailableRooms()) {
            Console.writeLine(room);
        }
    }

    /**
     * Loads the hotel from serialized storage if a previous file exists.
     */
    private static void loadHotel() {
        Hotel loadedHotel = HotelStorage.load(FILE_NAME);
        if (loadedHotel != null) {
            currentHotel = loadedHotel;
            Console.writeLine("- Saved hotel data loaded.");
        }
    }

    /**
     * Saves the hotel using serialized storage.
     */
    private static void saveHotel() {
        HotelStorage.save(currentHotel, FILE_NAME);
    }
}
