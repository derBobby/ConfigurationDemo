package com.enbw.configurationdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Schema {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long configId;

    @Column
    private String schemaString;

    @Column
    private String createdBy;

    @Column
    private String createdDate;

    public Schema(Long configId, String schemaString, String createdBy, String createdDate) {
        this.configId = configId;
        this.schemaString = schemaString;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public boolean isValid(String content) {
        return content.contains(schemaString);
    }

    public boolean isInvalid(String content) {
        return ! isValid(content);
    }
}
