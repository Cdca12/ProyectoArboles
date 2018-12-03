
import java.util.Scanner;

/**
 * @titulo Proyecto Árboles
 * @materia Estructura de Datos
 * @author Carlos Contreras
 * @facilitador Dr. Clemente García Gerardo
 */
public class Main {

    public static void main(String[] args) {
        ArbolBinarioBusqueda<Producto> arbolProductos = new ArbolBinarioBusqueda<>();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0, id = 0, entrada = 0;
        Producto producto;

        while (opcion != 11) {
            System.out.println("----------------------------------------------------------------------------------------"
                    + "\n\t\t\t\t  Proyecto Árboles"
                    + "\n\n1. Insertar un producto."
                    + "\n2. Retirar un producto."
                    + "\n3. Registrar entrada a un producto."
                    + "\n4. Registrar entrada a todos los productos con IdProducto mayor a un número aleatorio."
                    + "\n5. Retirar existencia de un producto."
                    + "\n6. Retirar todos los productos con existencia igual a 100."
                    + "\n7. Consulta: porcentaje de productos registrados."
                    + "\n8. Consulta: productos registados (orden descendente)."
                    + "\n9. Consulta: por id, mostrar la ubicación del producto en el árbol."
                    + "\n10. Consulta: niveles inferiores que tienen menos nodos que el nivel superior."
                    + "\n11. Salir."
                    + "\n----------------------------------------------------------------------------------------"
                    + "\n\nIngrese una opción del menú: ");
            opcion = scanner.nextInt();
            System.out.println("");

            switch (opcion) {
                case 1: // Insertar un producto. Información generada de manera aleatoria 
                    producto = new Producto();
                    if (arbolProductos.Inserta(producto)) {
                        System.out.println("¡Producto insertado correctamente! "
                                + "El producto tiene los siguientes datos:\n" + producto.informacion());
                    } else {
                        System.out.println("No se pudo insertar el producto.");
                    }
                    break;
                case 2: // Retirar un producto (Id producto proporcionado por teclado). Restricción la existencia debe ser igual a cero.
                    System.out.println("Ingrese el Id del producto que desee retirar: ");
                    id = scanner.nextInt();
                    producto = new Producto(id);
                    if (!arbolProductos.Busca(producto)) {
                        System.out.println("\nEl producto no se encuentra en el árbol. No se ha retirado nada.");
                        break;
                    }
                    if (arbolProductos.Dr.Existencia != 0) {
                        System.out.println("\nLa existencia del producto no es igual a 0. No se puede retirar.");
                        break;
                    }
                    // Se manda a borrar
                    if (arbolProductos.Borrar(arbolProductos.Dr)) {
                        System.out.println("\n¡El producto ha sido retirado correctamente!");
                    } else {
                        System.out.println("\nError inesperado. El producto no se pudo borrar.");
                    }
                    break;
                case 3: // Registrar entrada a un producto (Id producto y existencia proporcionado por teclado) Aumentar Existencia
                    System.out.println("Ingrese el Id del producto que desee modificar: ");
                    id = scanner.nextInt();
                    producto = new Producto(id);
                    if (!arbolProductos.Busca(producto)) {
                        System.out.println("\nEl producto no se encuentra en el árbol. No se ha modificado nada.");
                        break;
                    }
                    System.out.println("Ingrese el número de entrada de producto: ");
                    entrada = scanner.nextInt();
                    arbolProductos.Dr.Existencia += entrada;
                    System.out.println("\n¡La entrada de producto ha sido registrada correctamente! "
                            + "El producto tiene los siguientes datos:\n" + arbolProductos.Dr.informacion());
                    break;
                case 4: // TODO: Registrar entrada a todos los productos con IdProducto mayor a X (valor aleatorio) 
                    break;
                case 5: // Retirar existencia de un producto (id proporcionado y cantidad proporcionado por teclado.
                    System.out.println("Ingrese el Id del producto que desee retirar: ");
                    id = scanner.nextInt();
                    producto = new Producto(id);
                    if (!arbolProductos.Busca(producto)) {
                        System.out.println("\nEl producto no se encuentra en el árbol. No se ha modificado nada.");
                        break;
                    }
                    System.out.println("Ingrese el número de existencia de producto que desee retirar: ");
                    entrada = scanner.nextInt();
                    if ((arbolProductos.Dr.Existencia - entrada) < 0) {
                        System.out.println("No puede retirar un número mayor que la existencia actual. No se ha modificado nada.");
                        break;
                    }
                    arbolProductos.Dr.Existencia -= entrada;
                    System.out.println("\n¡La existencia de producto ha sido modificada correctamente! "
                            + "El producto tiene los siguientes datos:\n" + arbolProductos.Dr.informacion());
                    break;
                case 6: // TODO: Retirar todos los productos con existencia igual a 100

                    break;
                case 7: // TESTEAR: Consulta: porcentaje de productos registrados (con respecto al total productos)
                    System.out.println("El árbol se encuentra " + arbolProductos.Length() * 0.49 + "% lleno.");
                    break;
                case 8: // Consulta: productos registrados (en orden descendente)
                    if (arbolProductos.Length() != 0) {
                        consultaOrdenDesc(arbolProductos);
                        break;
                    }
                    System.out.println("El árbol se encuentra vacío.");
                    break;
                case 9: // TODO: Consulta: proporcionado un id, mostrar la ubicación del producto en el árbol: nivel y secuencia de subárboles (izq,der,…)

                    break;
                case 10: // TODO: Consulta: niveles inferiores que tienen menos nodos que el nivel superior.

                    break;
                case 11: // Salir
                    System.out.println("Ha finalizado el programa correctamente.");
                    break;
                default:
                    System.out.println("Favor de ingresar una opción del menú.");
                    break;

            }
            System.out.println("");
        }
    }

    public static void consultaOrdenDesc(ArbolBinarioBusqueda<Producto> arbol) {
        consultaOrdenDesc(arbol.getRaiz());
    }

    public static void consultaOrdenDesc(NodoABB<Producto> Raiz) {
        if (Raiz == null) {
            return;
        }
        consultaOrdenDesc(Raiz.getSubDer());
        System.out.println(Raiz.Info.informacion());
        consultaOrdenDesc(Raiz.getSubIzq());
    }

    // In Orden
    public static void recorrerArbol(ArbolBinarioBusqueda<Producto> arbol, String metodo) {
        recorrerArbol(arbol.getRaiz(), metodo);
    }

    private static void recorrerArbol(NodoABB<Producto> Raiz, String metodo) {
        if (Raiz == null) {
            return;
        }
        recorrerArbol(Raiz.getSubIzq(), metodo);
        System.out.println(metodo);
        recorrerArbol(Raiz.getSubDer(), metodo);
    }

    // Pasar función como parámetro. Que con un solo método de recorrido pueda usarlo para cualquier función.
    // Lambda referencias: https://code.i-harness.com/es/q/477efb
    // https://es.stackoverflow.com/questions/67443/ejecutar-funci%C3%B3n-pasada-como-par%C3%A1metro-en-java
}
