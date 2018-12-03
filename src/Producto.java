
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
    
    public String informacion() {
        return "IdProducto: " + IdProducto + " | Existencia: " + Existencia;
    }
    
    public Producto() {
        this.IdProducto = Rutinas.nextInt(100, 5000);
        this.Existencia = Rutinas.nextInt(100);
    }
    
    public Producto(int IdProducto) {
        this.IdProducto = IdProducto;
        this.Existencia = 0;
    }
    
    
}
