package cz.lukas.mazl.test;

import cz.mazl.tul.configs.service.ServiceConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class})
@ActiveProfiles(profiles = "test")
public class CityServiceTest {
}
