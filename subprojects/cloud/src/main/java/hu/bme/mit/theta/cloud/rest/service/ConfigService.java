package hu.bme.mit.theta.cloud.rest.service;

import hu.bme.mit.theta.cloud.rest.endpoint.generated.model.AnalysisConfig;
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
    
    public List<ConfigurationEntity> createConfiguration(List<AnalysisConfig> configs) {
        List<ConfigurationEntity> configurationEntities = new ArrayList<>();
        for (AnalysisConfig config: configs) {

            ConfigurationEntity configurationEntity = new ConfigurationEntity();

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
            configurationEntity.setBenchmarkMode(config.isBenchmark());
            configurationEntity.setStacktrace(config.isStacktrace());

            configurationEntities.add(configurationEntity);
        }

        return configurationRepository.saveAll(configurationEntities);
    }
}
