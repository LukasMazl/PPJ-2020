package cz.mazl.tul.configs.job;

import cz.mazl.tul.blogic.repository.CityRepository;
import cz.mazl.tul.blogic.repository.mongo.TemperatureRepository;
import cz.mazl.tul.blogic.service.temperature.TemperatureService;
import cz.mazl.tul.jobs.old.OldDataRemover;
import cz.mazl.tul.jobs.old.SimpleOldDataRemover;
import cz.mazl.tul.jobs.update.SimpleUpdateTemperature;
import cz.mazl.tul.jobs.update.UpdateTemperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Calendar;
import java.util.Date;

@Configuration
@EnableAsync
@EnableScheduling
@Profile({"prod"})
public class JobConfig {

    @Autowired
    private JobPropertiesConfig jobPropertiesConfig;

    @Autowired
    private UpdateTemperature updateTemperature;

    @Autowired
    private OldDataRemover oldDataRemover;

    @Bean
    @Autowired
    public UpdateTemperature updateTemperature(CityRepository cityRepository, TemperatureService temperatureService) {
        return new SimpleUpdateTemperature(cityRepository, temperatureService);
    }

    @Bean
    @Autowired
    public OldDataRemover oldDataRemover(TemperatureRepository temperatureRepository) {
        return new SimpleOldDataRemover(temperatureRepository);
    }

    @Scheduled(fixedDelayString = "${cz.mazl.tul.job.updateInterval}")
    public void updateWeatherJob() {
        updateTemperature.updateTemperature(jobPropertiesConfig.getUpdateBatch());
    }


    @Scheduled(fixedDelayString = "${cz.mazl.tul.job.intervatDeleteExpiration}")
    public void deleteExpValues() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -jobPropertiesConfig.getDeleteExpiration());
        Date date = cal.getTime();
        oldDataRemover.removeOldTemperatureDate(date);
    }
}
