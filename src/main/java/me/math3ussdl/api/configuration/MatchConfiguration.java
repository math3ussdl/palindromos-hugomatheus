package me.math3ussdl.api.configuration;

import me.math3ussdl.domain.port.api.MatchServicePort;
import me.math3ussdl.domain.port.spi.MatchPersistencePort;
import me.math3ussdl.domain.service.MatchServiceImpl;
import me.math3ussdl.infrastucture.adapter.MatchJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MatchConfiguration {

    @Bean
    public MatchPersistencePort matchPersistencePort() {
        return new MatchJpaAdapter();
    }

    @Bean
    public MatchServicePort matchServicePort() {
        return new MatchServiceImpl(matchPersistencePort());
    }
}
