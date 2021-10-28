package com.edisonmoreno.archetype.config;

import com.edisonmoreno.archetype.PDF.CompressPDFUtil;
import com.edisonmoreno.archetype.domainservice.CompressService;
import com.edisonmoreno.archetype.model.ICompressPDF;
import com.edisonmoreno.archetype.model.document.gateways.DocumentRepository;
import com.edisonmoreno.archetype.usecase.CompressPDFUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.edisonmoreno.archetype.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private ICompressPDF iCompressPDF;

    @Bean
    public CompressService configure() {
        iCompressPDF = CompressPDFUtil.getInstance();
        CompressPDFUseCase compressPDFUseCase = new CompressPDFUseCase(documentRepository, iCompressPDF);
//                //ListAllPDFUseCase listAllPDFUseCase = new ListAllPDFUseCase(documentRepository);
//                //return new CompressService(compressPDFUseCase, listAllPDFUseCase);
        return new CompressService(compressPDFUseCase);
    }
}
