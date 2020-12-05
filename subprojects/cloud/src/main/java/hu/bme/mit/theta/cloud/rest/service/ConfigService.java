package hu.bme.mit.theta.cloud.rest.service;

import hu.bme.mit.theta.cloud.rest.endpoint.generated.model.*;
import hu.bme.mit.theta.cloud.repository.ConfigurationRepository;
import hu.bme.mit.theta.cloud.repository.datamodel.ConfigurationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfigService {
    
    @Autowired
    private ConfigurationRepository configurationRepository;

    public List<ConfigurationEntity> createCfaConfiguration(List<CfaConfig> configs) {
        List<ConfigurationEntity> configurationEntities = new ArrayList<>();
        for (CfaConfig config: configs) {

            ConfigurationEntity configurationEntity = new ConfigurationEntity();

            configurationEntity.setConfigType("CFA");

            configurationEntity.setDomainName(config.getDomain().toString());
            configurationEntity.setRefinement(config.getRefinement().toString());
            configurationEntity.setPredSplit(config.getPredSplit().toString());
            configurationEntity.setErrorLoc(null);
            configurationEntity.setPrecGranularity(config.getPrecGranularity().toString());
            configurationEntity.setSearch(config.getSearch().toString());
            configurationEntity.setEncoding(config.getEncoding().toString());
            configurationEntity.setMaxEnum(config.getMaxEnum());
            configurationEntity.setInitPrec(config.getInitPrec().toString());
            configurationEntity.setPruneStrategy(config.getPruneStrategy().toString());
            configurationEntity.setLogLevel(config.getLogLevel().toString());
            configurationEntity.setStacktrace(config.isStacktrace());

            configurationEntities.add(configurationEntity);
        }

        return configurationRepository.saveAll(configurationEntities);
    }

    public List<ConfigurationEntity> createStsConfiguration(List<StsConfig> configs) {
        List<ConfigurationEntity> configurationEntities = new ArrayList<>();
        for (StsConfig config: configs) {

            ConfigurationEntity configurationEntity = new ConfigurationEntity();

            configurationEntity.setConfigType("SYSTEM");

            configurationEntity.setDomainName(config.getDomain().toString());
            configurationEntity.setRefinement(config.getRefinement().toString());
            configurationEntity.setPredSplit(config.getPredSplit().toString());
            configurationEntity.setSearch(config.getSearch().toString());
            configurationEntity.setInitPrec(config.getInitPrec().toString());
            configurationEntity.setPruneStrategy(config.getPruneStrategy().toString());
            configurationEntity.setLogLevel(config.getLogLevel().toString());
            configurationEntity.setStacktrace(config.isStacktrace());
            configurationEntity.setCexFile(config.isCexFile());

            configurationEntities.add(configurationEntity);
        }

        return configurationRepository.saveAll(configurationEntities);
    }

    public List<ConfigurationEntity> createXtaConfiguration(List<XtaConfig> configs) {
        List<ConfigurationEntity> configurationEntities = new ArrayList<>();
        for (XtaConfig config: configs) {

            ConfigurationEntity configurationEntity = new ConfigurationEntity();

            configurationEntity.setConfigType("XTA");

            configurationEntity.setDataStrategy(config.getDataStrategy().toString());
            configurationEntity.setClockStrategy(config.getClockStrategy().toString());
            configurationEntity.setSearch(config.getSearchStrategy().toString());
            configurationEntity.setCexFile(config.isVisualize());
            configurationEntity.setStacktrace(config.isStacktrace());

            configurationEntities.add(configurationEntity);
        }

        return configurationRepository.saveAll(configurationEntities);
    }

    public List<ConfigurationEntity> createXstsConfiguration(List<XstsConfig> configs) {
        List<ConfigurationEntity> configurationEntities = new ArrayList<>();
        for (XstsConfig config: configs) {

            ConfigurationEntity configurationEntity = new ConfigurationEntity();

            configurationEntity.setConfigType("XSTS");

            configurationEntity.setDomainName(config.getDomain().toString());
            configurationEntity.setRefinement(config.getRefinement().toString());
            configurationEntity.setPredSplit(config.getPredSplit().toString());
            configurationEntity.setSearch(config.getSearch().toString());
            configurationEntity.setInitPrec(config.getInitPrec().toString());
            configurationEntity.setPruneStrategy(config.getPruneStrategy().toString());
            configurationEntity.setLogLevel(config.getLogLevel().toString());
            configurationEntity.setStacktrace(config.isStacktrace());
            configurationEntity.setCexFile(config.isCexFile());
            configurationEntity.setProperty(config.getProperty());
            configurationEntities.add(configurationEntity);
        }

        return configurationRepository.saveAll(configurationEntities);
    }
}
