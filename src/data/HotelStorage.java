package data;

import domain.Employee;
import domain.Hotel;
import domain.Reservation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Handles the main serialized persistence mechanism of the hotel system.
 */
public class HotelStorage {

    /**
     * Saves the complete hotel state and sequence counters into a serialized file.
     */
    public static void save(Hotel hotel, String fileName) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))) {
            output.writeObject(hotel);
            output.writeInt(Reservation.getNextReservationNumber());
            output.writeInt(Employee.getNextEmployeeNumber());
            System.out.println("Hotel data saved successfully.");
        } catch (IOException error) {
            System.err.println("An error occurred while saving hotel data.");
        }
    }

    /**
     * Loads the complete hotel state and restores sequence counters from a serialized file.
     */
    public static Hotel load(String fileName) {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))) {
            Hotel hotel = (Hotel) input.readObject();
            int reservationCounter = input.readInt();
            int employeeCounter = input.readInt();
            Reservation.setNextReservationNumber(reservationCounter);
            Employee.setNextEmployeeNumber(employeeCounter);
            return hotel;
        } catch (IOException | ClassNotFoundException error) {
            return null;
        }
    }
}
