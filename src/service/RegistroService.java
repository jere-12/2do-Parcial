package service;


import model.Persona;
import exception.SinReactivosException;
import java.util.*;

public class RegistroService {
    private int reactivosDisponibles;
    private int numeroKit = 1;
    private Set<String> dnisRegistrados = new HashSet<>();
    private Map<Integer, Persona> personasRegistradas = new HashMap<>();

    public RegistroService(int reactivosDisponibles) {
        this.reactivosDisponibles = reactivosDisponibles;
    }

    public void registrarPersona(Persona persona) throws SinReactivosException {
        if (reactivosDisponibles <= 0) {
            throw new SinReactivosException("No hay reactivos disponibles.");
        }
        if (!dnisRegistrados.add(persona.getDni())) {
            throw new IllegalArgumentException("DNI duplicado: " + persona.getDni());
        }
        persona.setNumeroKit(numeroKit);
        if (personasRegistradas.containsKey(numeroKit)) {
            throw new IllegalArgumentException("Numero de kit duplicado: " + numeroKit);
        }
        personasRegistradas.put(numeroKit, persona);
        numeroKit++;
        reactivosDisponibles--;
    }

    public void agregarReactivos(int cantidad) {
        this.reactivosDisponibles += cantidad;
    }

    public Map<Integer, Persona> getPersonasRegistradas() {
        return personasRegistradas;
    }
}
