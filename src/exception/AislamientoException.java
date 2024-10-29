package exception;

public class AislamientoException extends Exception {
  private int numeroKit;
  private String barrio;

  public AislamientoException(int numeroKit, String barrio) {
    super("Temperatura elevada detectada. Kit: " + numeroKit + ", Barrio: " + barrio);
    this.numeroKit = numeroKit;
    this.barrio = barrio;
  }

  public int getNumeroKit() { return numeroKit; }
  public String getBarrio() { return barrio; }
}