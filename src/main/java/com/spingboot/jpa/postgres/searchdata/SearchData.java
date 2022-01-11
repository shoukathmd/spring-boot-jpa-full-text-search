package com.spingboot.jpa.postgres.searchdata;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_search")
@JsonPropertyOrder({ "name", "type1", "type2", "description" })
public class SearchData {

    public SearchData() {}

    public SearchData(
            final String name,
            final String type1,
            final String type2,
            final String description) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.description = description;
    }

    @Id
    @GeneratedValue
    public Long id;

    @Column
    public String name;

    @Column
    public String type1;

    @Column
    public String type2;

    @Column
    public String description;
}
