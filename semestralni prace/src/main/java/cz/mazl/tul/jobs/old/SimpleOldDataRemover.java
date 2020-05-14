package cz.mazl.tul.jobs.old;

import cz.mazl.tul.blogic.repository.mongo.TemperatureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SimpleOldDataRemover implements OldDataRemover {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleOldDataRemover.class);

    private TemperatureRepository temperatureRepository;

    public SimpleOldDataRemover(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    @Override
    public void removeOldTemperatureDate(Date date) {
        LOG.trace("Removing old data from mongo repository.");
        temperatureRepository.deleteAllByDayBefore(date);
    }
}
