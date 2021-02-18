package com.sazaxa.shipmentapi.member;

public enum MemberStatus {
    ACTIVATE("활성화"),
    DEACTIVATE("비활성화");

    private String status;

    MemberStatus(String status) {
        this.status = status;
    }
}
