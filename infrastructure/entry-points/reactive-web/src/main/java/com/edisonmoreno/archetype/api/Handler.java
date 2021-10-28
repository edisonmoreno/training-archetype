package com.edisonmoreno.archetype.api;

import com.edisonmoreno.archetype.domainservice.CompressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.SequenceInputStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class Handler {
    private final CompressService compressService;

    public Mono<ServerResponse> listenGetDocuments(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body("test", String.class);
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest
                .body(BodyExtractors.toDataBuffers())
                .map(b -> b.asInputStream(true))
                .reduce(SequenceInputStream::new)
                .map(inputStream -> {
                    try {
                        return IOUtils.toByteArray(inputStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return new byte[0];
                })
                .flatMap(bytes -> ServerResponse.ok()
                        .body(Mono.fromFuture(compressService.reduce("undefine", bytes)), Object.class)
                );

    }
}
