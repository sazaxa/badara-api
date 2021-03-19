package com.sazaxa.shipmentapi.excel.shipping;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShippingServiceTest {

    @Test
    void test(){
        double givenWeight = 0.5;
        double weight = 0.5 * Math.ceil(givenWeight / 0.5);
        System.out.println(weight);
    }
}
