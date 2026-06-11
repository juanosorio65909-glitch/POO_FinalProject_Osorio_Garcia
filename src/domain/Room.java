package domain;

import java.io.Serializable;

/**
 * Represents a hotel room with its capacity, category, price, and availability.
 */
public class Room implements Serializable {

    private int roomNumber;
    private int bedCount;
    private int bathCount;
    private String category;
    private double nightlyRate;
    private boolean available;

    /**
     * Creates a room and marks it as available by default.
     */
    public Room(int roomNumber, int bedCount, int bathCount, String category, double nightlyRate) {
        setRoomNumber(roomNumber);
        setBedCount(bedCount);
        setBathCount(bathCount);
        setCategory(category);
        setNightlyRate(nightlyRate);
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getBedCount() {
        return bedCount;
    }

    public int getBathCount() {
        return bathCount;
    }

    public String getCategory() {
        return category;
    }

    public double getNightlyRate() {
        return nightlyRate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber > 0 ? roomNumber : 0;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount > 0 ? bedCount : 0;
    }

    public void setBathCount(int bathCount) {
        this.bathCount = bathCount > 0 ? bathCount : 0;
    }

    public void setCategory(String category) {
        if (category == null || category.isBlank()) {
            this.category = "standard";
        } else {
            this.category = category.trim().toLowerCase();
        }
    }

    public void setNightlyRate(double nightlyRate) {
        this.nightlyRate = nightlyRate > 0 ? nightlyRate : 0;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Room{roomNumber=" + roomNumber + ", category='" + category + '\'' +
                ", beds=" + bedCount + ", baths=" + bathCount +
                ", nightlyRate=" + nightlyRate + ", available=" + available + '}';
    }
}
