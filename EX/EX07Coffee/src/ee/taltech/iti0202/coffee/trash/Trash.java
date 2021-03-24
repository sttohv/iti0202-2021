package ee.taltech.iti0202.coffee.trash;

import java.util.logging.Logger;

public class Trash {
    protected int trashCount;
    protected int trashCapacity;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public Trash(int capacity){
        trashCapacity = capacity;
        trashCount = 0;
    }
    /**
     * Checks if the trash is full
     *
     * @return if the trash is full or not
     */
    public boolean isTrashFull() {
        return trashCount == trashCapacity;
    }

    /**
     * Empties the trash
     */
    public void emptyTrash() {
        log("trash thrown out");
        trashCount = 0;
    }

    public void throwInTrash(){
        log("trash thrown in the bin");
        trashCount++;
    }
    /**
     * log actions
     *
     * @param message message to be logged
     */
    protected void log(String message) {
        LOGGER.info(message);
    }


}
