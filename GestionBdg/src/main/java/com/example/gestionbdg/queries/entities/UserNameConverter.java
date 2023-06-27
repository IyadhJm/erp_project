package com.example.gestionbdg.queries.entities;
/*
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter
public class UserNameConverter implements AttributeConverter<List<String>, String> {

    private static final String START_TAG = "<string>";
    private static final String END_TAG = "</string>";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String value : attribute) {
            stringBuilder.append(START_TAG).append(value).append(END_TAG);
        }
        return stringBuilder.toString();
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> attribute = new ArrayList<>();
        int startIndex = dbData.indexOf(START_TAG);
        while (startIndex != -1) {
            int endIndex = dbData.indexOf(END_TAG, startIndex);
            if (endIndex != -1) {
                String value = dbData.substring(startIndex + START_TAG.length(), endIndex);
                attribute.add(value);
                startIndex = dbData.indexOf(START_TAG, endIndex);
            } else {
                break;
            }
        }
        return attribute;
    }

}
*/