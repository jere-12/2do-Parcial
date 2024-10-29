package service;

import model.Persona;
import model.RegistroTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestService {
    private Map<Integer, RegistroTest> registros = new HashMap<>();
    private Random random = new Random();

    public void testearPersona(Persona persona) {
        double temperatura = 36 + random.nextDouble() * 3; //genera una temperatura entre 36 y 39
        registros.put(persona.getNumeroKit(), new RegistroTest(persona.getDni(), temperatura));
    }

    public Map<Integer, RegistroTest> getRegistros() {
        return registros;
    }
}
