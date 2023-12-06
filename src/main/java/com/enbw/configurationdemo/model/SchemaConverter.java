package com.enbw.configurationdemo.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import org.springframework.core.convert.converter.Converter;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

public class SchemaConverter implements AttributeConverter<Schema, String> {
    @Override
    public String convertToDatabaseColumn(Schema schema) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(schema);
        } catch (JsonProcessingException e) {
            return null; // You can handle the error as needed
        }
    }

    @Override
    public Schema convertToEntityAttribute(String dbDataJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(dbDataJson, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            return null; // You can handle the error as needed
        }
    }
}
