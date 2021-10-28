package com.edisonmoreno.archetype.PDF;

import com.edisonmoreno.archetype.model.ICompressPDF;

public class CompressPDFFactory {
    private CompressPDFUtil instance;

    public ICompressPDF getInstance() {
        if (instance == null) {
            instance = new CompressPDFUtil();
        }
        return instance;
    }
}
