package me.math3ussdl.configuration;

import me.math3ussdl.adapter.MatchJpaAdapter;
import me.math3ussdl.port.api.MatchServicePort;
import me.math3ussdl.port.spi.MatchPersistencePort;
import me.math3ussdl.service.MatchServiceImpl;
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
