package ee.taltech.iti0202.kt1.hotel.room;

import ee.taltech.iti0202.kt1.hotel.exceptions.CannotBookException;
import ee.taltech.iti0202.kt1.hotel.exceptions.CannotCancelException;

public class Regular {
    protected int roomNumber;
    protected int roomSize;
    protected boolean booked;
    protected String type;

    public Regular(int roomNumber, int roomSize) {
        this.roomNumber = roomNumber;
        this.roomSize = roomSize;
        booked = false;
        type = "regular";
    }

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
        if (booked) {
            throw new CannotBookException(CannotBookException.Reason.ROOM_ALREADY_BOOKED);
        } else {
            booked = true;
        }
    }

    /**
     * Cancels the booking
     */
    public boolean cancelBooking() throws CannotCancelException {
        if (booked) {
            booked = false;
            return true;
        }
        else{
            throw new CannotCancelException(CannotCancelException.Reason.SUITE_NOT_BOOKED);
        }
    }
}
