package ee.taltech.iti0202.kt1.hotel;

import ee.taltech.iti0202.kt1.hotel.exceptions.CannotBookException;
import ee.taltech.iti0202.kt1.hotel.exceptions.CannotCancelException;
import ee.taltech.iti0202.kt1.hotel.exceptions.CannotCreateNewRoom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HotelTest {
    @Test
    public void addCorrectRoomsToHotel() throws CannotCreateNewRoom {
        Hotel hotel = new Hotel();
        hotel.addRoom("suite", 2, 1);
        hotel.addRoom("regular", 1, 2);

        Assertions.assertEquals(2, hotel.getRooms().size());
    }

    @Test
    public void addIncorrectRoomsToHotel() throws CannotCreateNewRoom {
        Hotel hotel = new Hotel();
        CannotCreateNewRoom.Reason exception = null;
        hotel.addRoom("suite", 2, 1);
        try {
            hotel.addRoom("regular", 1, 1);
        } catch (CannotCreateNewRoom ex) {
            exception = ex.getReason();
        }
        Assertions.assertEquals(CannotCreateNewRoom.Reason.ALREADY_HAS_SAME_ROOM, exception);


    }

    @Test
    public void bookRegularRoomInHotel() throws CannotCreateNewRoom, CannotBookException {
        Hotel hotel = new Hotel();
        hotel.addRoom("suite", 2, 1);
        hotel.addRoom("regular", 1, 2);

        Assertions.assertEquals(2, hotel.bookRoom("regular", 1));


    }

    @Test
    public void bookRegularRoomNotInHotel() throws CannotCreateNewRoom {
        Hotel hotel = new Hotel();
        hotel.addRoom("suite", 2, 1);
        hotel.addRoom("regular", 1, 2);
        CannotBookException ex = null;

        try {
            hotel.bookRoom("regular", 2);
        } catch (CannotBookException e) {
            ex = e;
        }

        Assertions.assertEquals(CannotBookException.Reason.NO_AVAILABLE_ROOMS, ex.getReason());

    }

    @Test
    public void bookSuiteInHotel() throws CannotCreateNewRoom, CannotBookException {
        Hotel hotel = new Hotel();
        hotel.addRoom("suite", 2, 1);
        hotel.addRoom("regular", 1, 2);

        Assertions.assertEquals(1, hotel.bookRoom("suite", 2));
    }

    @Test
    public void bookSuiteNotInHotel() throws CannotCreateNewRoom {
        Hotel hotel = new Hotel();
        hotel.addRoom("suite", 2, 1);
        hotel.addRoom("regular", 1, 2);
        CannotBookException ex = null;

        try {
            hotel.bookRoom("suite", 1);
        } catch (CannotBookException e) {
            ex = e;
        }

        Assertions.assertEquals(CannotBookException.Reason.NO_AVAILABLE_ROOMS, ex.getReason());

    }

    @Test
    public void cancelRegularRoom() throws CannotCreateNewRoom, CannotBookException, CannotCancelException {
        Hotel hotel = new Hotel();
        hotel.addRoom("suite", 2, 1);
        hotel.addRoom("regular", 1, 2);
        int roomNumber = hotel.bookRoom("regular", 1);
        hotel.cancelRoom(roomNumber);

    }

    @Test
    public void cancelSuite() throws CannotCreateNewRoom, CannotBookException {
        Hotel hotel = new Hotel();
        hotel.addRoom("suite", 2, 1);
        hotel.addRoom("regular", 1, 2);
        int roomNumber = hotel.bookRoom("suite", 2);
        CannotCancelException ex = null;

        try {
            hotel.cancelRoom(roomNumber);
        } catch (CannotCancelException e) {
            ex = e;
        }

        Assertions.assertEquals(CannotCancelException.Reason.SUITE_CANNOT_BE_CANCELED, ex.getReason());
    }
}
