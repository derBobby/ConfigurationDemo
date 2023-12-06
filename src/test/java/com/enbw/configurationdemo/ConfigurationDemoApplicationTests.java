package com.enbw.configurationdemo;

import com.enbw.configurationdemo.model.Configuration;
import com.enbw.configurationdemo.repo.ConfigurationRepo;
import com.enbw.configurationdemo.repo.FileContentRepo;
import com.enbw.configurationdemo.repo.FileRepo;
import com.enbw.configurationdemo.repo.SchemaRepo;
import com.enbw.configurationdemo.service.ConfigurationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ConfigurationDemoApplicationTests {

	@Autowired
	ConfigurationRepo configurationRepo;

	@Autowired
	SchemaRepo schemaRepo;

	@Autowired
	FileRepo fileRepo;

	@Autowired
	FileContentRepo fileContentRepo;

	@Test
	void contextLoads() {
	}

	@Test
	void firstConfig_canBeCreated() {

		ConfigurationService service = new ConfigurationService(configurationRepo, fileRepo, schemaRepo, fileContentRepo);

		// Create first config
		Configuration config = new Configuration();
		service.saveConfig(config);

		// Create first file
		service.addFile(config.getId(), "<json>Spaß mit Strings</json>");
		service.addSchemaToConfig(config.getId(), "Spaß");

		// Create first file
		service.addSchemaToConfigAndSetAsDefault(config.getId(), "Spaß mit");
		service.addFile(config.getId(), "<json>Weiterer Spaß mit Strings</json>");

		assertThrows(IllegalArgumentException.class, () -> {
			service.addFile(config.getId(), "<json>Kein Spaß</json>");
		});
	}
}
