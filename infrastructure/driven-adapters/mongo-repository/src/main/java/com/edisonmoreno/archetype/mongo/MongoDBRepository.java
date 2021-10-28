package com.edisonmoreno.archetype.mongo;

import com.edisonmoreno.archetype.mongo.entity.EntityDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBRepository extends ReactiveMongoRepository<EntityDocument, String>, ReactiveQueryByExampleExecutor<EntityDocument> {
}
