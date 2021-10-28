package com.edisonmoreno.archetype.usecase;

import com.edisonmoreno.archetype.model.ICompressPDF;
import com.edisonmoreno.archetype.model.document.Document;
import com.edisonmoreno.archetype.model.document.gateways.DocumentRepository;
import com.edisonmoreno.archetype.usecase.base.UseCase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

public class CompressPDFUseCase extends UseCase<CompressPDFUseCase.Request, CompressPDFUseCase.Response> {
    private DocumentRepository documentRepository;
    //private CompressPDFUtil compressPDFUtil = CompressPDFUtil.getInstance();
    private ICompressPDF compressPDF;

    public CompressPDFUseCase(DocumentRepository documentRepository, ICompressPDF iCompressPDF) {
        this.documentRepository = documentRepository;
        this.compressPDF = iCompressPDF;
    }

    @Override
    protected void executeUseCase(Request requestValues) {
        Optional.of(compressPDF.reduce(requestValues.input, 500L))
                .map(bytes -> {
                    String name = requestValues.name;
                    String path = saveFile(name, bytes);
                    Document document = Document.builder()
                            .name(name)
                            .uri(path)
                            .build();
                    documentRepository.save(document).block();
                    emit().onSuccess(new Response(bytes, "Success"));
                    return bytes;
                }).orElseGet(() -> {
                    emit().onError(new RuntimeException("No se pudo realizar la tarea"));
                    return null;
                });
    }

    private String saveFile(String fileName, byte[] file) {
        FileOutputStream outputStream;
        String name = fileName + ".pdf";
        try {
            outputStream = new FileOutputStream(fileName + ".pdf");
            outputStream.write(file);
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar documento =>" + name, e);
        }
        return name;
    }

    public static class Request implements UseCase.RequestValues {
        private final byte[] input;
        private final String name;

        public Request(String name, byte[] input) {
            this.input = input;
            this.name = name;
        }
    }

    public static class Response implements UseCase.ResponseValue {
        private final byte[] output;
        private final String status;

        public Response(byte[] output, String status) {
            this.status = status;
            this.output = output;
        }

        public String getResult() {
            return status;
        }
    }
}
