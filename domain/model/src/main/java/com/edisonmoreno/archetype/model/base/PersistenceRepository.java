package com.edisonmoreno.archetype.model.base;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersistenceRepository<E, S> {
    Flux<S> findAll();

    Mono<E> findById(String id);

    Mono<S> save(E model);

    Flux<E> saveAll(Flux<E> model);
}