package pl.sylwekczmil.ma.backend.infrastructure

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.mongodb.config.EnableMongoAuditing

@EnableMongoAuditing
@EnableJpaAuditing
@Configuration
class Config


