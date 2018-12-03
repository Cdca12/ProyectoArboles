/**
 *
 * @author Carlos Contreras
 */
public class Producto {
    public int IdProducto; // valores de 100-5000
    public int Existencia;
    
    public String toString() {
        return Rutinas.PonCeros(IdProducto, 5);
    }
}
