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
    private Long configurationId;

    @Column
    private String schemaString;

    @Column
    private String createdBy;

    @Column
    private String createdDate;

    public Schema(Long configurationId, String schemaString, String createdBy, String createdDate) {
        this.configurationId = configurationId;
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
