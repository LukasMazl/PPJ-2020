package cz.mazl.tul.configs.job;

import cz.mazl.tul.blogic.repository.CityRepository;
import cz.mazl.tul.blogic.service.temperature.TemperatureService;
import cz.mazl.tul.jobs.SimpleUpdateTemperature;
import cz.mazl.tul.jobs.UpdateTemperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableAsync
@EnableScheduling
@Profile({"prod"})
public class JobConfig {

    @Autowired
    private JobPropertiesConfig jobPropertiesConfig;

    @Autowired
    private UpdateTemperature updateTemperature;

    @Bean
    @Autowired
    public UpdateTemperature updateTemperature(CityRepository cityRepository, TemperatureService temperatureService) {
        return new SimpleUpdateTemperature(cityRepository, temperatureService);
    }

    @Scheduled(fixedDelayString = "${cz.mazl.tul.job.interval}")
    public void updateWeatherJob() {
        updateTemperature.updateTemperature(jobPropertiesConfig.getBatch());
    }
}
