package ee.taltech.iti0202.kt1.hotel.room;

import ee.taltech.iti0202.kt1.hotel.exceptions.CannotBookException;
import ee.taltech.iti0202.kt1.hotel.exceptions.CannotCancelException;

public class Regular {
    protected int roomNumber;
    protected int roomSize;
    protected boolean booked;
    protected String type;

    /**
     * new regular room
     *
     * @param roomNumber room number
     * @param roomSize   room size
     */
    public Regular(int roomNumber, int roomSize) {
        this.roomNumber = roomNumber;
        this.roomSize = roomSize;
        booked = false;
        type = "regular";
    }

    /**
     * Checks whether the room is booked or not
     *
     * @return
     */
    public boolean isBooked() {
        return booked;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public String getType() {
        return type;
    }

    /**
     * Book room if room not already booked
     *
     * @throws CannotBookException why can't be fixed
     */
    public void bookRoom() throws CannotBookException {
        booked = true;
    }

    /**
     * Cancels the booking
     */
    public boolean cancelBooking() throws CannotCancelException {
        if (booked) {
            booked = false;
            return true;
        } else {
            throw new CannotCancelException(CannotCancelException.Reason.SUITE_NOT_BOOKED);
        }
    }
}
