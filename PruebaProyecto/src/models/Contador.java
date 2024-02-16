package models;

import java.util.ArrayList;

// Definición de la clase Contador que extiende de la clase Empleado, encargado de asignarle montos a los encargados de ventas asi como
// dar de altas sus compras.
public class Contador extends Empleado {
    // Atributo privado que almacena el total del monto asignado
    private int totalMontoAsignado;

    // Método para asignar un monto a un encargado de ventas en la lista de empleados
    public ArrayList<Empleado> asignarMonto(ArrayList<Empleado> lista, int monto, int encargadoVentasID) {
        // Se establece el monto disponible para el encargado de ventas especificado en la lista
        ((BuysResponsable) lista.get(encargadoVentasID)).setAvaibleMonto(monto);
        // Se actualiza el total del monto asignado por el contador
        totalMontoAsignado += monto;
        // Se devuelve la lista actualizada de empleados
        return lista;
    }

    // Método para dar de alta una compra para un encargado de ventas en la lista de empleados
    public ArrayList<Empleado> DarAltaCompra(ArrayList<Empleado> lista, int encargadoVentasID) {
        // Se llama al método agregarComprasListaAlta del encargado de ventas especificado en la lista
        ((BuysResponsable) lista.get(encargadoVentasID)).agregarComprasListaAlta();
        // Se devuelve la lista actualizada de empleados
        return lista;
    }
}

