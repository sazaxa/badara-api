package com.sazaxa.shipmentapi.faq;

import com.sazaxa.shipmentapi.util.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Faq extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Builder
    public Faq(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public void updateFaq(String title, String content){
        this.title = title;
        this.content = content;
    }

}
