import models.*;
import java.util.ArrayList;
import java.util.Scanner;

//Clase Main, encargada de llevar a cabo todos los metodos y procesos del codigo, su principal funcion es enseñar al usuario las opciones disponibles asi como llevarlas a cabo, instancia en tiempo real los objetos conforme van siendo usados, y tiene las lista de empleados principal donde se lleva a cabo todos los procesos
public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inicialización de variables
        String nombreEmpresa = "Contadores S.A";
        boolean run = true;
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        Empleado superAdministrador = new Empleado();

        // Configuración del super administrador
        System.out.println("Ingrese nombre del Administrador");
        superAdministrador.setName(scanner.nextLine());
        System.out.println("Ingrese mail del Administrador");
        superAdministrador.setMail(scanner.nextLine());
        System.out.println("Ingrese contraseña del Administrador");
        superAdministrador.setPassword(scanner.nextLine());
        listaEmpleados.add(superAdministrador);

        // Bucle principal del programa
        while (run) {
            // Mostrar el menú principal
            showMenu(nombreEmpresa);
            // Capturar la opción seleccionada por el usuario
            switch (tryInt()) {
                case 1:
                    // Iniciar sesión de un empleado
                    Empleado tryEmpleado = new Empleado();
                    System.out.println("Correo");
                    tryEmpleado.setMail(scanner.nextLine());
                    System.out.println("Contraseña");
                    tryEmpleado.setPassword(scanner.nextLine());

                    // Verificar si el empleado es el super administrador
                    if (listaEmpleados.get(0).getPassword().equals(tryEmpleado.getPassword()) && listaEmpleados.get(0).getMail().equals(tryEmpleado.getMail()))
                        listaEmpleados = administradorMenu(listaEmpleados, superAdministrador);

                    // Buscar si el empleado es otro tipo de empleado
                    int empleadoIndex = 0;
                    for (Empleado empleado : listaEmpleados) {
                        if (listaEmpleados.get(empleadoIndex).getPassword().equals(tryEmpleado.getPassword()) && listaEmpleados.get(empleadoIndex).getMail().equals(tryEmpleado.getMail())) {
                            if (empleado instanceof Contador) {
                                listaEmpleados = menuContador(listaEmpleados, empleadoIndex);
                            } else if (empleado instanceof BuysResponsable) {
                                listaEmpleados = menuEV(listaEmpleados, empleadoIndex);
                            }
                        }
                        empleadoIndex++;
                    }
                    break;
                case 2:
                    // Salir del programa
                    run = false;
                    break;
            }
        }
    }

    // Muestra el menú principal
    public static void showMenu(String nombreEmpresa) {
        System.out.println("-------------------");
        System.out.println("Bienvenido a " + nombreEmpresa);
        System.out.println("1.-Iniciar sesion");
        System.out.println("2.-Salir");
        System.out.println("-------------------");
    }

    // Muestra el menú para el administrador
    public static void showAdministradorMenu() {
        System.out.println("---------------------");
        System.out.println("Menu");
        System.out.println("1.-Agregar Empleado");
        System.out.println("2.-Editar empleado");
        System.out.println("3.-Eliminar empleado");
        System.out.println("4.-Salir");
        System.out.println("---------------------");
    }

    // Menú de acciones para el administrador
    public static ArrayList<Empleado> administradorMenu(ArrayList<Empleado> lista, Empleado superAdministrador) {
        boolean run = true;
        System.out.println("Bienvenido " + superAdministrador.getName());
        do {
            showAdministradorMenu();
            switch (tryInt()) {
                case 1:
                    lista = agregarEmpleado(lista, superAdministrador);
                    break;
                case 2:
                    lista = editEmpleado(lista);
                    break;
                case 3:
                    lista = deleteEmpleado(lista);
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    break;
            }
        } while (run);
        return lista;
    }

    // Permite al administrador agregar un nuevo empleado
    public static ArrayList<Empleado> agregarEmpleado(ArrayList<Empleado> lista, Empleado superAdministrador) {
        // Crea un nuevo empleado
        Empleado newEmpleado = new Empleado();
        System.out.println("Ingrese correo del empleado");
        newEmpleado.setMail(scanner.nextLine());
        System.out.println("Ingrese contraseña del empleado");
        newEmpleado.setPassword(scanner.nextLine());
        boolean notAnyEmpleadoMatch = true;

        // Verifica si el empleado ya existe en la lista
        for (Empleado empleadoIndex : lista) {
            if (empleadoIndex.getMail().equals(newEmpleado.getMail())) {
                notAnyEmpleadoMatch = false;
                System.out.println("Empleado ya en el sistema, intente denuevo");
            }
        }

        // Si el empleado no existe, lo agrega a la lista
        if (notAnyEmpleadoMatch) {
            System.out.println("Ingrese nombre del empleado");
            newEmpleado.setName(scanner.nextLine());
            System.out.println("Contador (1) Responsable de Ventas (2)");
            if (tryInt() == 1) {
                Empleado newEM = new Contador();
                newEM.setName(newEmpleado.getName());
                newEM.setMail(newEmpleado.getMail());
                newEM.setPassword(newEmpleado.getPassword());
                newEmpleado = newEM;
            } else {
                Empleado newEM = new BuysResponsable();
                newEM.setName(newEmpleado.getName());
                newEM.setMail(newEmpleado.getMail());
                newEM.setPassword(newEmpleado.getPassword());
                newEmpleado = newEM;
            }
            if (lista.add(newEmpleado)) System.out.println("Empleado añadido con exito");
        }
        return lista;
    }
    // Muestra el menú para editar empleado
    public static void showAdministradorEditMenu() {
        System.out.println("----------------------");
        System.out.println("Que desea editar del empleado?");
        System.out.println("1.-Nombre");
        System.out.println("2.-Mail");
        System.out.println("3.-Password");
        System.out.println("4.-Salir");
        System.out.println("----------------------");
    }
    // Permite al administrador editar la información de un empleado existente
    public static ArrayList<Empleado> editEmpleado(ArrayList<Empleado> lista) {
        Empleado editEmpleado = new Empleado();
        System.out.println("Ingrese correo del empleado");
        editEmpleado.setMail(scanner.nextLine());
        System.out.println("Ingrese contraseña del empleado");
        editEmpleado.setPassword(scanner.nextLine());
        boolean find = false;

        // Busca al empleado en la lista
        for (int indexAux = 0; indexAux < lista.size(); indexAux++) {
            if (lista.get(indexAux).getPassword().equals(editEmpleado.getPassword()) && lista.get(indexAux).getMail().equals(editEmpleado.getMail())) {
                showAdministradorEditMenu();
                find = true;
                switch (tryInt()) {
                    case 1:
                        System.out.println("Ingrese nuevo nombre");
                        lista.get(indexAux).setName(scanner.nextLine());
                        System.out.println("Empleado editado con exito");
                        break;
                    case 2:
                        System.out.println("Ingrese nuevo mail");
                        lista.get(indexAux).setMail(scanner.nextLine());
                        System.out.println("Empleado editado con exito");
                        break;
                    case 3:
                        System.out.println("Ingrese nueva contraseña");
                        lista.get(indexAux).setPassword(scanner.nextLine());
                        System.out.println("Empleado editado con exito");
                        break;
                    default:
                        break;
                }
            }
        }
        if (!find){
            System.out.println("Empleado no encontrado");
        }
        return lista;
    }

    // Elimina un empleado de la lista
    public static ArrayList<Empleado> deleteEmpleado(ArrayList<Empleado> lista) {
        Empleado deleteEmpleado = new Empleado();
        System.out.println("Ingrese correo del empleado");
        deleteEmpleado.setMail(scanner.nextLine());
        System.out.println("Ingrese contraseña del empleado");
        deleteEmpleado.setPassword(scanner.nextLine());
        boolean find = false;

        // Busca al empleado y lo elimina de la lista
        for (int indexAux = 0; indexAux < lista.size(); indexAux++) {
            if (lista.get(indexAux).getPassword().equals(deleteEmpleado.getPassword()) && lista.get(indexAux).getMail().equals(deleteEmpleado.getMail())) {
                lista.remove(indexAux);
                System.out.println("Empleado removido con exito");
                find = true;
            }
        }
        if (!find){
            System.out.println("Empleado no encontrado");
        }
        return lista;
    }
    public static void showContadorMenu(){
        System.out.println("----------------------");
        System.out.println("Menu");
        System.out.println("1.-Asignar monto a Encargado de Ventas");
        System.out.println("2.-Ver lista de compras de alta");
        System.out.println("3.-Dar de alta compras");
        System.out.println("4.-Salir");
        System.out.println("----------------------");
    }

    // Muestra la lista de encargados de ventas disponibles
    public static void showListaEncargadosDeVenta(ArrayList<Empleado>lista){
        int indexAux = 0;
        for (Empleado index:lista){
            if (index instanceof BuysResponsable){
                System.out.println("ID:"+indexAux+" Nombre:" + index.getName());
            }
            indexAux++;
        }
    }

    // Muestra la lista de compras de alta para un encargado de ventas específico
    public static void showListaComprasDeAlta(ArrayList<Empleado>lista,int ID){
        if (!((BuysResponsable)lista.get(ID)).getListaComprasDeAlta().isEmpty()){
            for (Compra compraIndex:((BuysResponsable)lista.get(ID)).getListaComprasDeAlta()){
                System.out.println(compraIndex.toString());
                for (Producto productIndex:compraIndex.getListaProducto()){
                    System.out.println(productIndex.toString());
                }
            }
        } else {
            System.out.println("Lista vacia");
        }
    }

    // Muestra la primera compra en espera para un encargado de ventas específico
    public static void showFirstCompra(ArrayList<Empleado>lista,int ID){
        Compra seeCompra;
        seeCompra = ((BuysResponsable)lista.get(ID)).getListaComprasEspera().peekFirst();
        System.out.println(seeCompra.toString());
        for (Producto indexProduct : seeCompra.getListaProducto()){
            System.out.println(indexProduct.toString());
        }
    }

    // Menú de acciones para el contador
    public static ArrayList<Empleado> menuContador(ArrayList<Empleado> lista, int empleadoIndex){
        boolean run = true;
        System.out.println("Bienvenido Contador: " + lista.get(empleadoIndex).getName());
        do {
            showContadorMenu();
            int ID;
            switch (tryInt()){
                case 1:
                    // Asigna un monto a un encargado de ventas
                    showListaEncargadosDeVenta(lista);
                    System.out.println("Ingrese ID del Encargado de ventas a ingresar monto");
                    ID = tryInt();
                    System.out.println("Ingrese monto");
                    int monto = tryInt();
                    ((Contador)lista.get(empleadoIndex)).asignarMonto(lista,monto,ID);
                    break;
                case 2:
                    // Muestra la lista de compras de alta de un encargado de ventas
                    showListaEncargadosDeVenta(lista);
                    System.out.println("Ingrese ID del Encargado de Ventas a vizualisar");
                    ID = tryInt();
                    showListaComprasDeAlta(lista,ID);
                    break;
                case 3:
                    // Da de alta una compra para un encargado de ventas
                    showListaEncargadosDeVenta(lista);
                    System.out.println("Ingrese ID del Encargado de venta");
                    ID = tryInt();
                    if (!((BuysResponsable)lista.get(ID)).getListaComprasEspera().isEmpty()) {
                        System.out.println("Desea dar de alta la siguiente compra");
                        showFirstCompra(lista, ID);
                        System.out.println("Si(1);No(2)");
                        if (tryInt() == 1) {
                            ((Contador) lista.get(empleadoIndex)).DarAltaCompra(lista, ID);
                        } else {
                            System.out.println("Accion cancelada");
                        }
                    } else {
                        System.out.println("No existe ninguna compra en espera");
                    }
                    break;
                case 4:
                    // Salir del menú del contador
                    run = false;
                    break;
            }
        } while (run);
        return lista;
    }

    // Función para mostrar el menú del Responsable de Ventas
    public static void showEV(){
        System.out.println("----------------------");
        System.out.println("Menu");
        System.out.println("1.-Ver monto asignado");
        System.out.println("2.-Realizar compras");
        System.out.println("3.-Ver compras en espera por dar de alta");
        System.out.println("4.-Salir");
        System.out.println("----------------------");
    }

    // Menú de acciones para el Responsable de Ventas
    public static ArrayList<Empleado> menuEV(ArrayList<Empleado> lista, int empleadoIndex){
        boolean run = true;
        System.out.println("Bienvenido RV: " + lista.get(empleadoIndex).getName());
        do {
            showEV();
            switch (tryInt()){
                case 1:
                    // Ver el monto asignado al Responsable de Ventas
                    System.out.println("El monto disponible es: " + ((BuysResponsable)lista.get(empleadoIndex)).getAvaibleMonto());
                    break;
                case 2:
                    // Realizar compras como Responsable de Ventas
                    lista = realizarCompra(lista, empleadoIndex);
                    break;
                case 3:
                    // Ver las compras en espera por dar de alta
                    imprimirComprasEnEspera(lista,empleadoIndex);
                    break;
                case 4:
                    // Salir del menú del Responsable de Ventas
                    run = false;
                    break;
            }
        } while (run);
        return lista;
    }
    // Realiza una compra como Responsable de Ventas
    public static ArrayList<Empleado> realizarCompra (ArrayList<Empleado> lista, int ID){
        Compra newCompra = new Compra();
        int montoDisponible = ((BuysResponsable)lista.get(ID)).getAvaibleMonto();
        System.out.println("Ingrese fecha de compra");
        newCompra.setFechaDeCompra(scanner.nextLine());
        do {
            Producto newProduct = new Producto();
            System.out.println("Ingrese nombre del producto");
            newProduct.setName(scanner.nextLine());
            System.out.println("Ingrese precio del producto");
            newProduct.setPrecio(tryInt());
            System.out.println("Ingrese cantidad de producto");
            newProduct.setCantidad(tryInt());
            int totalDeProducto = newProduct.obtenerTotal();
            if (montoDisponible >= totalDeProducto){
                newCompra.setListaProducto(newProduct);
                ((BuysResponsable)lista.get(ID)).setAvaibleMonto((montoDisponible-totalDeProducto));
                montoDisponible = montoDisponible - totalDeProducto;
                System.out.println("Producto añadido con exito, saldo restante: "+ ((BuysResponsable)lista.get(ID)).getAvaibleMonto());
                newCompra.setTotalCompra(totalDeProducto);
            } else {
                System.out.println("Saldo insuficiente");
            }
            System.out.println("Ingresar otro producto? Si(1) No(2)");
        } while (tryInt()==1 );
        if (((BuysResponsable)lista.get(ID)).setListaComprasEspera(newCompra)){
            System.out.println("Compra añadida con exito");
        }
        return lista;
    }

    // Imprime las compras en espera por dar de alta
    public static void imprimirComprasEnEspera(ArrayList<Empleado>lista, int ID){
        for (Compra compraIndex: ((BuysResponsable)lista.get(ID)).getListaComprasEspera()){
            System.out.println(compraIndex.toString());
            for (Producto productoIndex:compraIndex.getListaProducto()){
                System.out.println(productoIndex.toString());
            }
        }
    }
    // Función auxiliar para capturar números enteros desde la entrada
    public static int tryInt() {
        boolean run = true;
        int seleccion = 0;
        Scanner teclado = new Scanner(System.in);
        do {
            try {
                seleccion = teclado.nextInt();
                teclado.nextLine();
                run = false;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Intente denuevo");
                teclado.nextLine();
            }
        } while (run);
        return seleccion;
    }
}