package com.challange.api.rest.banco.infrastructure.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.web.util.UriComponentsBuilder;

public class Utils {


    /**
     * Método que consiste en el armado de una url válida dato un path base y endpoint
     *
     * @param url      Url base del servicio.
     * @param endpoint Enpoint del servicio.
     * @return
     */
    public static UriComponentsBuilder obtenerUrl(String url, String endpoint) {
        return UriComponentsBuilder.fromHttpUrl(url).path(endpoint);
    }


    public static String objectJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }


}
