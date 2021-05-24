package com.sazaxa.shipmentapi.box;

import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

class BoxServiceTest {

    private BoxService boxService;
    private BoxRepository boxRepository;
    private final Long EXISTED_ID = 1L;

    @BeforeEach
    void setUp() {
        boxRepository = mock(BoxRepository.class);
        boxService = new BoxService(boxRepository);
    }

}
