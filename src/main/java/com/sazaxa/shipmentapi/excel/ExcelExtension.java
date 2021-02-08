package com.sazaxa.shipmentapi.excel;

public enum ExcelExtension {

    XLSX("xlsx"),
    XLS("xls");

    private String extension;

    ExcelExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

}
