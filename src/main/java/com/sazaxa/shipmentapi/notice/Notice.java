package com.sazaxa.shipmentapi.notice;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Builder
    public Notice(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
