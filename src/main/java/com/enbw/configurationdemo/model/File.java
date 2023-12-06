package com.enbw.configurationdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long configurationId;

    @Column
    private String createdBy;

    @Column
    private String createdDate;

    @Column
    @Convert(converter = SchemaConverter.class)
    private Schema schema;

    public File(Long configurationId, String createdBy, String createdDate, Schema schema) {
        this.configurationId = configurationId;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.schema = schema;
    }
}
