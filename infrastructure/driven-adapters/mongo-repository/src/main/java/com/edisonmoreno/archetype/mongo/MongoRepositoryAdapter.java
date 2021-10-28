package com.edisonmoreno.archetype.mongo;

import com.edisonmoreno.archetype.model.document.Document;
import com.edisonmoreno.archetype.model.document.gateways.DocumentRepository;
import com.edisonmoreno.archetype.mongo.entity.EntityDocument;
import com.edisonmoreno.archetype.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MongoRepositoryAdapter extends AdapterOperations<Document, EntityDocument, String, MongoDBRepository> implements DocumentRepository {

    public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Document.class));
    }
}
