package ee.taltech.iti0202.kt1.hotel.room;

import ee.taltech.iti0202.kt1.hotel.exceptions.CannotCancelException;

public class Suite extends Regular {
    /**
     * New suite
     *
     * @param roomNumber room number
     * @param roomSize   room size
     */
    public Suite(int roomNumber, int roomSize) {
        super(roomNumber, roomSize);
        type = "suite";
    }

    @Override
    public boolean cancelBooking() throws CannotCancelException {
        throw new CannotCancelException(CannotCancelException.Reason.SUITE_CANNOT_BE_CANCELED);
    }
}
