package com.sazaxa.shipmentapi.member;

public enum MemberStatus {
    ACTIVATE("ACTIVATE"),
    DEACTIVATE("DEACTIVATE");

    private String status;

    MemberStatus(String status) {
        this.status = status;
    }
}
