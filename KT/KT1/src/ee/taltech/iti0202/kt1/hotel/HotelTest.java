package ee.taltech.iti0202.kt1.hotel;

import ee.taltech.iti0202.kt1.hotel.exceptions.CannotCreateNewRoom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        hotel.addRoom("suite", 2, 1);
        try {
            hotel.addRoom("regular", 1, 2);
        } catch (CannotCreateNewRoom ex) {
            Assertions.assertEquals(ex.getReason(), CannotCreateNewRoom.Reason.ALREADY_HAS_SAME_ROOM);
        }


    }

    @Test
    public void bookRegularRoomInHotel() {

    }

    @Test
    public void bookRegularRoomNotInHotel() {

    }

    @Test
    public void bookSuiteInHotel() {

    }

    @Test
    public void bookSuiteNotInHotel() {

    }


}