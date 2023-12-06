package com.enbw.configurationdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FileContent {

    @Id
    private Long id;

    @Column
    private String content;
}
