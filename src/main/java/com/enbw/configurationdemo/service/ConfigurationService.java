package com.enbw.configurationdemo.service;

import com.enbw.configurationdemo.model.Configuration;
import com.enbw.configurationdemo.model.File;
import com.enbw.configurationdemo.model.FileContent;
import com.enbw.configurationdemo.model.Schema;
import com.enbw.configurationdemo.repo.ConfigurationRepo;
import com.enbw.configurationdemo.repo.FileContentRepo;
import com.enbw.configurationdemo.repo.FileRepo;
import com.enbw.configurationdemo.repo.SchemaRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigurationService {

    private final ConfigurationRepo configurationRepo;
    private final FileRepo fileRepo;
    private final SchemaRepo schemaRepo;
    private final FileContentRepo fileContentRepo;

    public ConfigurationService(ConfigurationRepo configurationRepo, FileRepo fileRepo, SchemaRepo schemaRepo, FileContentRepo fileContentRepo) {
        this.configurationRepo = configurationRepo;
        this.fileRepo = fileRepo;
        this.schemaRepo = schemaRepo;
        this.fileContentRepo = fileContentRepo;
    }

    /*
     * CONFIG
     */

    public void saveConfig(Configuration config) {
        configurationRepo.save(config);
    }
    
    @Transactional(rollbackOn = Exception.class)
    public void addSchemaToConfig(Long configId, String schemaString) {
        Configuration config = getConfigOrThrowException(configId);
        Schema schema = createAndPersistSchema(configId, schemaString);
        config.addSchema(schema);
    }

    @Transactional(rollbackOn = Exception.class)
    public void setSchemaAsDefault(Long configId, Long schemaId) {
        Configuration config = getConfigOrThrowException(configId);
        Schema schema = getSchemaOrThrowException(configId, schemaId);
        config.setCurrentSchema(schema);
    }

    @Transactional(rollbackOn = Exception.class)
    public void addSchemaToConfigAndSetAsDefault(Long configId, String schemaString) {
        Configuration config = getConfigOrThrowException(configId);
        Schema schema = createAndPersistSchema(configId, schemaString);
        config.addSchema(schema);
        config.setCurrentSchema(schema);
        configurationRepo.save(config);
    }

    private Schema createAndPersistSchema(Long configId, String schemaString) {
        Schema schema = new Schema(configId, schemaString, "Creator", "Date");
        return schemaRepo.save(schema);
    }

    /*
     * FILE
     */

    @Transactional(rollbackOn = Exception.class)
    public void addFile(Long configId, String content) {

        // Validate config id
        Configuration configuration = getConfigOrThrowException(configId);

        Schema currentSchema = configuration.getCurrentSchema();
        if(currentSchema != null && currentSchema.isInvalid(content)) {
            throw new IllegalArgumentException("Mate, srsly?");
        }

        File file = new File(configId, "Creator", "Date", configuration.getCurrentSchema());
        fileRepo.save(file);

        FileContent fileContent = new FileContent(file.getId(), content);
        fileContentRepo.save(fileContent);

        configuration.addFile(file);
        configurationRepo.save(configuration);
    }

    /*
     * GET OR EXCEPTION
     */

    private Configuration getConfigOrThrowException(Long configId) {
        Optional<Configuration> optionalConfiguration = configurationRepo.findById(configId);
        if (optionalConfiguration.isEmpty()) {
            throw new IllegalArgumentException("Fuck off, Mate!");
        }
        return optionalConfiguration.get();
    }

    private Schema getSchemaOrThrowException(Long configId, Long schemaId) {
        Optional<Schema> optionalSchema = schemaRepo.findByConfigIdAndId(configId, schemaId);
        if (optionalSchema.isEmpty()) {
            throw new IllegalArgumentException("Fuck off, Mate!");
        }
        return optionalSchema.get();
    }
}
