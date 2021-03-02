package ee.taltech.iti0202.mysticorbs.exceptions;

import ee.taltech.iti0202.mysticorbs.oven.Oven;

public class CannotFixException extends Exception {
    public enum Reason {
        IS_NOT_BROKEN,
        FIXED_MAXIMUM_TIMES,
        NOT_ENOUGH_RESOURCES
    }
    private Oven oven;
    private Reason reason;
    //Konstruktorist kaks muutujat puudu
    public CannotFixException(Oven oven, Reason reason){
        this.oven = oven;
        this.reason = reason;
    }
    public Oven getOven(){
        return oven;
    }
    public Reason getReason(){
        return reason;
    }
}
