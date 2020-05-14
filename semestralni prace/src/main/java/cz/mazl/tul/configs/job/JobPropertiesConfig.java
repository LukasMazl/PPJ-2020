package cz.mazl.tul.configs.job;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "cz.mazl.tul.job")
public class JobPropertiesConfig {

    @NotNull
    private int updateInterval;

    @NotNull
    private int updateBatch;

    @NotNull
    private int deleteExpiration;

    @NotNull
    private int intervalDeleteExpiration;

    public int getUpdateInterval() {
        return updateInterval;
    }

    public void setUpdateInterval(int updateInterval) {
        this.updateInterval = updateInterval;
    }

    public int getUpdateBatch() {
        return updateBatch;
    }

    public void setUpdateBatch(int updateBatch) {
        this.updateBatch = updateBatch;
    }

    public int getDeleteExpiration() {
        return deleteExpiration;
    }

    public void setDeleteExpiration(int deleteExpiration) {
        this.deleteExpiration = deleteExpiration;
    }

    public int getIntervalDeleteExpiration() {
        return intervalDeleteExpiration;
    }

    public void setIntervatDeleteExpiration(int intervalDeleteExpiration) {
        this.intervalDeleteExpiration = intervalDeleteExpiration;
    }
}
