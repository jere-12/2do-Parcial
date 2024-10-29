package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Persona;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExportService {

    public static void exportarJSON(List<Persona> sanos, List<Persona> sospechosos) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<Persona>> resultado = Map.of(
                "sanos", sanos,
                "aislar", sospechosos
        );

        try {
            mapper.writeValue(new File("resultado.json"), resultado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}