package domain;

/**
 * Provides a basic room recommendation system without external libraries.
 */
public class RoomRecommendationSystem {

    /**
     * Recommends the cheapest available room that satisfies the requested beds and budget.
     */
    public Room recommendRoom(Hotel hotel, int requiredBeds, double maximumBudget) {
        Room bestRoom = null;
        for (Room room : hotel.getAvailableRooms()) {
            boolean hasEnoughBeds = room.getBedCount() >= requiredBeds;
            boolean isWithinBudget = maximumBudget <= 0 || room.getNightlyRate() <= maximumBudget;
            if (hasEnoughBeds && isWithinBudget) {
                if (bestRoom == null || room.getNightlyRate() < bestRoom.getNightlyRate()) {
                    bestRoom = room;
                }
            }
        }
        return bestRoom;
    }

    /**
     * Classifies hotel occupancy using the percentage of unavailable rooms.
     */
    public String classifyOccupancy(Hotel hotel) {
        if (hotel.getRooms().isEmpty()) {
            return "NO_ROOMS_REGISTERED";
        }
        int occupiedRooms = hotel.getRooms().size() - hotel.getAvailableRooms().size();
        double occupancyRate = (double) occupiedRooms / hotel.getRooms().size();
        if (occupancyRate < 0.35) {
            return "LOW_OCCUPANCY";
        }
        if (occupancyRate < 0.75) {
            return "MEDIUM_OCCUPANCY";
        }
        return "HIGH_OCCUPANCY";
    }
}
