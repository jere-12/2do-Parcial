package model;

public class RegistroTest {
    private String dni;
    private double temperatura;

    public RegistroTest(String dni, double temperatura) {
        this.dni = dni;
        this.temperatura = temperatura;
    }

    public String getDni() { return dni; }

    public void setDni(String dni) { this.dni = dni; }

    public double getTemperatura() { return temperatura; }

    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }
}
