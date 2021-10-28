package com.edisonmoreno.archetype.domainservice;

import com.edisonmoreno.archetype.usecase.CompressPDFUseCase;
import com.edisonmoreno.archetype.usecase.base.UseCaseHandler;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class CompressService {
    private final CompressPDFUseCase compressPDFUseCase;

    public CompletableFuture<CompressPDFUseCase.Response> reduce(String name, byte[] bytes) {
        return UseCaseHandler
                .getInstance()
                .execute(compressPDFUseCase, new CompressPDFUseCase.Request(name, bytes));
    }
}
