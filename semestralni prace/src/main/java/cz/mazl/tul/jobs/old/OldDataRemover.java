package cz.mazl.tul.jobs.old;

import java.util.Date;

public interface OldDataRemover {

    /**
     * Removes expired data from mongoDb.
     *
     * @param date
     */
    void removeOldTemperatureDate(Date date);
}
