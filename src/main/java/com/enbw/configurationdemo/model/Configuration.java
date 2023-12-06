package com.enbw.configurationdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<File> fileList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Schema> schemaList;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Setter
    private Schema currentSchema;

    public Configuration() {
        this.fileList = new ArrayList<>();
        this.schemaList = new ArrayList<>();
        this.currentSchema = null;
    }

    public void addSchema(Schema schema) {
        this.schemaList.add(schema);
    }

    public void addFile(File file) {
        this.fileList.add(file);
    }
}
