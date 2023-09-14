package br.com.kitchen.club.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

    public static String toJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toCaptalize(String input) {
        String INPUT = input.trim();
        return INPUT.substring(0, 1).toUpperCase() + INPUT.substring(1);
    }
}
