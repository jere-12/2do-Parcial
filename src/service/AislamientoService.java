package service;

import exception.AislamientoException;
import model.Persona;
import model.RegistroTest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

    public class AislamientoService {

        public void aislarPersonas(Map<Integer, RegistroTest> registros, List<Persona> personas) {
            List<Persona> sanos = new ArrayList<>();
            List<Persona> sospechosos = new ArrayList<>();

            for (Map.Entry<Integer, RegistroTest> entry : registros.entrySet()) {
                Integer numeroKit = entry.getKey();
                RegistroTest registro = entry.getValue();
                Persona persona = buscarPersonaPorKit(numeroKit, personas);
                if (registro.getTemperatura() >= 38) {
                    try {
                        throw new AislamientoException(numeroKit, persona.getBarrio());
                    } catch (AislamientoException e) {
                        // guardo el caso sospechoso en un archivo binario "urgente.dat"
                        sospechosos.add(persona);
                       escribirPersonaEnArchivo(persona, "urgente.dat");
                    }
                } else {
                    sanos.add(persona);
                }
            }

            // almaceno los resultados en un archivo JSON
            ExportService.exportarJSON(sanos, sospechosos);
        }

        private Persona buscarPersonaPorKit(Integer numeroKit, List<Persona> personas) {
            for (Persona persona : personas) {
                if (numeroKit.equals(persona.getNumeroKit())) {
                    return persona;
                }
            }
            return null;
        }

        private void escribirPersonaEnArchivo(Persona persona, String nombreArchivo) {
            File archivo = new File(nombreArchivo);
            try {
                if (!archivo.exists()) {
                    archivo.createNewFile();
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
                    writer.write(persona.getNumeroKit() + "," + persona.getNombre() + "," + persona.getBarrio());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }