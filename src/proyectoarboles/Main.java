package proyectoarboles;

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
        double porcentaje;
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
                    if (arbolProductos.Length() == 0) {
                        System.out.println("El árbol está vacío. Inserta datos primero e intentelo de nuevo.");
                        break;
                    }
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
                case 4: // Registrar entrada a todos los productos con IdProducto mayor a X (valor aleatorio) 
                    if (arbolProductos.Length() == 0) {
                        System.out.println("El árbol está vacío. Inserta datos primero e intentelo de nuevo.");
                        break;
                    }
                    id = Rutinas.nextInt(100, 5000);
                    while (!arbolProductos.Busca(new Producto(id))) {
                        id = Rutinas.nextInt(100, 5000); // Genero id aleatoria hasta que genere uno existente.
                    }
                    System.out.println("Ingrese el número de entrada de producto: ");
                    entrada = scanner.nextInt();
                    registrarEntradaIdMayor(arbolProductos, id, entrada);
                    System.out.println("\nSe ha registrado entrada a todos los productos con IdProducto mayor a " + id);
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
                case 6: // Retirar todos los productos con existencia igual a 100x|
                    retirarPorExistencia(arbolProductos, arbolProductos.getRaiz(), 100);
                    System.out.println("Se han retirado todos los productos con existencia igual a 100.");
                    break;
                case 7: // Consulta: porcentaje de productos registrados (con respecto al total productos)
                    porcentaje = (arbolProductos.Length() / 4900d) * 100d;
                    System.out.println("El árbol se encuentra " + String.format("%.2f", porcentaje) + "% lleno.");
                    break;
                case 8: // Consulta: productos registrados (en orden descendente)
                    if (arbolProductos.Length() == 0) {
                        System.out.println("El árbol se encuentra vacío.");
                        break;
                    }
                    consultaOrdenDesc(arbolProductos);
                    break;
                case 9: // Consulta: proporcionado un id, mostrar la ubicación del producto en el árbol: nivel y secuencia de subárboles (izq,der,…)
                    if (arbolProductos.Length() == 0) {
                        System.out.println("El árbol está vacío. Inserta datos primero e intentelo de nuevo.");
                        break;
                    }
                    System.out.println("Ingrese el Id del producto que desea consultar: ");
                    id = scanner.nextInt();
                    producto = new Producto(id);
                    if (!arbolProductos.Busca(producto)) {
                        System.out.println("\nEl producto no se encuentra en el árbol. No se ha impreso nada.");
                        break;
                    }
//                    arbolProductos.Busca(producto);
                    consultaNodoRecorrido(arbolProductos, arbolProductos.Dr);
                    break;
                case 10: // Consulta: niveles inferiores que tienen menos nodos que el nivel superior.
                    if (arbolProductos.Length() == 0) {
                        System.out.println("El árbol está vacío. Inserta datos primero e intentelo de nuevo.");
                        break;
                    }
                    nodosNivelInferiorMenorSuperior(arbolProductos);
                    // TEST:
//                    System.out.println("Nivel 1: " + numeroNodosPorNivel(arbolProductos, 1));
//                    System.out.println("Nivel 2: " + numeroNodosPorNivel(arbolProductos, 2));
//                    System.out.println("Nivel 3: " + numeroNodosPorNivel(arbolProductos, 3));
                    break;
                case 11: // Salir
                    System.out.println("Ha finalizado el programa correctamente.");
                    break;
                default:
                    System.out.println("Favor de ingresar una opción del menú.");
                    break;
            }
            System.out.println();
        }
    }

    public static void consultaOrdenDesc(ArbolBinarioBusqueda<Producto> arbol) {
        consultaOrdenDesc(arbol.getRaiz());
    }

    private static void consultaOrdenDesc(NodoABB<Producto> Raiz) {
        if (Raiz == null) {
            return;
        }
        consultaOrdenDesc(Raiz.getSubDer());
        System.out.println(Raiz.Info.informacion());
        consultaOrdenDesc(Raiz.getSubIzq());
    }

    public static void registrarEntradaIdMayor(ArbolBinarioBusqueda<Producto> arbol, int IdProducto, int Entrada) {
        registrarEntradaIdMayor(arbol.getRaiz(), IdProducto, Entrada);
    }

    // Recorrer post orden, es más óptimo. 
    private static void registrarEntradaIdMayor(NodoABB<Producto> Raiz, int IdProducto, int Entrada) {
        if (Raiz == null) {
            return;
        }
        registrarEntradaIdMayor(Raiz.getSubIzq(), IdProducto, Entrada);
        registrarEntradaIdMayor(Raiz.getSubDer(), IdProducto, Entrada);
        if (Raiz.Info.IdProducto > IdProducto) {
            Raiz.Info.Existencia += Entrada;
            return;
        }
        return;
    }

    // Necesito pedir el árbol para acceder al método de Borrar.
    public static void retirarPorExistencia(ArbolBinarioBusqueda<Producto> arbol, NodoABB<Producto> Raiz, int Existencia) {
        if (Raiz == null) {
            return;
        }
        retirarPorExistencia(arbol, Raiz.getSubIzq(), Existencia);
        retirarPorExistencia(arbol, Raiz.getSubDer(), Existencia);
        if (Raiz.Info.Existencia == Existencia) {
            arbol.Borrar(Raiz.Info);
            return;
        }
        return;
    }

    public static void consultaNodoRecorrido(ArbolBinarioBusqueda<Producto> arbol, Producto producto) {
        consultaNodoRecorrido(arbol.getRaiz(), producto);
    }

    private static void consultaNodoRecorrido(NodoABB<Producto> Raiz, Producto producto) {
        if (Raiz == null) {
            return;
        }
        int nivel = 0;
        String IdDato = producto.toString();
        String IdRaiz = Raiz.Info.toString();
        while (Raiz != null) {
            nivel++;
            IdRaiz = Raiz.Info.toString();
            if (IdDato.compareTo(IdRaiz) == 0) {
                System.out.println("¡Se encontró informacón del Nodo! \n" + Raiz.Info.informacion() + "\t| Nivel " + nivel); // Llegó al producto
                return;
            }
            if (IdDato.compareTo(IdRaiz) > 0) { // Camino derecha
                // Derecha
                System.out.println("Busca por los nodos de la derecha de " + Raiz.Info.informacion() + "\t| Nivel " + nivel);
                Raiz = Raiz.getSubDer();
            } else { // Camino izquierda
                System.out.println("Busca por los nodos de la izquierda de " + Raiz.Info.informacion() + "\t| Nivel " + nivel);
                Raiz = Raiz.getSubIzq();
            }
        }
    }

    public static int numeroNodosPorNivel(ArbolBinarioBusqueda<Producto> arbol, int nivel) {
        return numeroNodosPorNivel(arbol.getRaiz(), 1, nivel);
    }
    
    private static int numeroNodosPorNivel(NodoABB<Producto> Raiz, int contador, int nivel) {
        if (Raiz == null) {
            return 0;
        }
        int sumar = 0;  // Estaré creando variable en cada llamado recursivo. TODO: Optimizarlo.
        if (nivel == contador) {
            sumar = 1;
        }
        // Pre Orden
        return sumar
                + numeroNodosPorNivel(Raiz.getSubIzq(), contador + 1, nivel)
                + numeroNodosPorNivel(Raiz.getSubDer(), contador + 1, nivel);
    }

    public static void nodosNivelInferiorMenorSuperior(ArbolBinarioBusqueda<Producto> arbol) {   
        int nodosNivelActual, nodosNivelSuperior;
        
        for (int i = 1; i < arbol.Altura(); i++) {
            nodosNivelActual = numeroNodosPorNivel(arbol, i);
            nodosNivelSuperior = numeroNodosPorNivel(arbol, i+1);
            // Nivel actual es menor que nivel superior
            if (nodosNivelActual < nodosNivelSuperior ) {
                System.out.println("El nivel " + i + " tiene menos nodos que el nivel superior, "   // TODO: Imprimir niveles
                        + "con " + nodosNivelActual + " y " + nodosNivelSuperior + " nodos respectivamente");
            }
        }
    }

}
// Pasar función como parámetro. Que con un solo método de recorrido pueda usarlo para cualquier función.
// Lambda referencias: https://code.i-harness.com/es/q/477efb
// https://es.stackoverflow.com/questions/67443/ejecutar-funci%C3%B3n-pasada-como-par%C3%A1metro-en-java
//    // In Orden
//    public static void recorrerArbol(ArbolBinarioBusqueda<Producto> arbol, String metodo) {
//        recorrerArbol(arbol.getRaiz(), metodo);
//    }
//
//    private static void recorrerArbol(NodoABB<Producto> Raiz, String metodo) {
//        if (Raiz == null) {
//            return;
//        }
//        recorrerArbol(Raiz.getSubIzq(), metodo);
//        System.out.println(metodo);
//        recorrerArbol(Raiz.getSubDer(), metodo);
//    }
