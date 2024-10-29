import model.Persona;
import model.RegistroTest;
import service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // determino mis reactivos iniciales
        RegistroService registroService = new RegistroService(3);
        List<Persona> personas = new ArrayList<>();

        try {
            Persona persona1 = new Persona("Lionel", "Messi", 37, "Zona Oeste", "27345678", "Doctor");
            registroService.registrarPersona(persona1);
            personas.add(persona1);

            Persona persona2 = new Persona("Cristiano", "Ronaldo", 40, "Zona Norte", "23654321", "Abogado");
            registroService.registrarPersona(persona2);
            personas.add(persona2);

            System.out.println("Personas Registradas:");
            for (Persona persona : personas) {
                System.out.println("Nombre: " + persona.getNombre() + " " + persona.getApellido() +
                        ", DNI: " + persona.getDni() +
                        ", Numero de Kit: " + persona.getNumeroKit());
            }

            TestService testService = new TestService();
            for (Persona persona : personas) {
                testService.testearPersona(persona);
            }

            // muestro los resultados de los tests
            System.out.println("\nResultados del Testeo:");
            for (Map.Entry<Integer, RegistroTest> entry : testService.getRegistros().entrySet()) {
                Integer numeroKit = entry.getKey();
                RegistroTest registro = entry.getValue();
                System.out.println("Kit: " + numeroKit +
                        ", DNI: " + registro.getDni() +
                        ", Temperatura: " + registro.getTemperatura());
            }

           //aislamos a las personas sospechosas donde a su vez almaceno los datos en un  archivo BIN Y en JSON(consignas opcionales)
            AislamientoService aislamientoService = new AislamientoService();
            aislamientoService.aislarPersonas(testService.getRegistros(), personas);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}