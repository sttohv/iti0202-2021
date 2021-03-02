package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;

public interface Fixable {
    /**
     * fix method
     *
     * @throws CannotFixException why cannot fix
     */
    void fix() throws CannotFixException;

    /**
     * how many times has the oven been fixed
     *
     * @return times oven has been fixed
     */
    int getTimesFixed();
}
