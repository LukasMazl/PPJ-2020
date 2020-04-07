package cz.lukas.mazl.test.uploading;

import cz.mazl.tul.blogic.service.index.PrepareIndexDataService;
import cz.mazl.tul.blogic.exception.CountryNotFoundException;
import cz.mazl.tul.blogic.exception.FileValidationException;
import cz.mazl.tul.blogic.service.temperature.TemperatureService;
import cz.mazl.tul.configs.servise.RepositoryConfig;
import cz.mazl.tul.configs.servise.ServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class, RepositoryConfig.class})
@ActiveProfiles(profiles = "devel")
public class BaseTest {

    @Autowired
    private TemperatureService temperatureService;

    @Autowired
    private PrepareIndexDataService prepareIndexDataService;

    @Test(expected = CountryNotFoundException.class)
    public void testExportData() {
        temperatureService.exportData(null, "Praha");
    }

    @Test(expected = FileValidationException.class)
    public void testImport() {
        temperatureService.importTemperatureFromFile(null,null, "Praha");
    }

    @Test(expected = CountryNotFoundException.class)
    public void testImportWithCountry() {
        MockMultipartFile firstFile = new MockMultipartFile("data", "filename.csv", "text/plain", "Praha;25".getBytes());
        temperatureService.importTemperatureFromFile(firstFile, "cs", "Praha");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrepareData() {
        prepareIndexDataService.getDataForCountry(null);
    }

    @Test(expected = CountryNotFoundException.class)
    public void testPrepareData1() {
        prepareIndexDataService.getDataForCountry("cs");
    }
}
