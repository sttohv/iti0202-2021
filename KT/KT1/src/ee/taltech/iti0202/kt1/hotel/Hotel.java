package ee.taltech.iti0202.kt1.hotel;

import ee.taltech.iti0202.kt1.hotel.exceptions.CannotBookException;
import ee.taltech.iti0202.kt1.hotel.exceptions.CannotCancelException;
import ee.taltech.iti0202.kt1.hotel.exceptions.CannotCreateNewRoom;
import ee.taltech.iti0202.kt1.hotel.room.Regular;
import ee.taltech.iti0202.kt1.hotel.room.Suite;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Regular> rooms;

    /**
     * New hotel
     */
    public Hotel() {
        rooms = new ArrayList<>();
    }

    /**
     * Add new room to hotel
     *
     * @param type       room type
     * @param size       room size
     * @param roomNumber room number
     * @throws CannotCreateNewRoom why can't be created
     */
    public void addRoom(String type, int size, int roomNumber) throws CannotCreateNewRoom {
        if (!checkIfRoomWithSameNumber(roomNumber)) {
            if (type.toLowerCase().equals("regular")) {
                Regular room = new Regular(roomNumber, size);
                rooms.add(room);
            } else if (type.toLowerCase().equals("suite")) {
                Suite room = new Suite(roomNumber, size);
                rooms.add(room);
            } else {
                throw new CannotCreateNewRoom(CannotCreateNewRoom.Reason.WRONG_TYPE);
            }
        } else {
            throw new CannotCreateNewRoom(CannotCreateNewRoom.Reason.ALREADY_HAS_SAME_ROOM);
        }
    }

    /**
     * Book room
     *
     * @param type room type
     * @param size room size
     * @return booked room number
     * @throws CannotBookException exception
     */
    public int bookRoom(String type, int size) throws CannotBookException {
        Regular room = findRoomToBook(type, size);
        room.bookRoom();
        return room.getRoomNumber();
    }

    /**
     * Cancel room
     *
     * @param roomNumber roomNumber to be canceled
     * @return roomNumber
     * @throws CannotCancelException why can't be cancelled
     */
    public int cancelRoom(int roomNumber) throws CannotCancelException {
        Regular room = getRoomByNumber(roomNumber);
        room.cancelBooking();
        return room.getRoomNumber();
    }

    public List<Regular> getRooms() {
        return rooms;
    }

    /**
     * Check if there is room with the same number
     *
     * @param roomNumber room number
     * @return if there is room with the same number
     */
    private boolean checkIfRoomWithSameNumber(int roomNumber) {
        for (Regular room : rooms
        ) {
            if (room.getRoomNumber() == roomNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * Find room to book
     *
     * @param type room type
     * @param size room size
     * @return room found
     * @throws CannotBookException error when room cannot be found
     */
    private Regular findRoomToBook(String type, int size) throws CannotBookException {
        for (Regular room : rooms
        ) {
            if (!room.isBooked() && room.getRoomSize() == size && room.getType().equals(type.toLowerCase())) {
                return room;
            }
        }
        throw new CannotBookException(CannotBookException.Reason.NO_AVAILABLE_ROOMS);
    }

    /**
     * Get room by number
     *
     * @param roomNumber room number
     * @return room found
     * @throws CannotCancelException used only in canceled
     */
    private Regular getRoomByNumber(int roomNumber) throws CannotCancelException {
        for (Regular room : rooms
        ) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        throw new CannotCancelException(CannotCancelException.Reason.NO_SUCH_SUITE);
    }


}
