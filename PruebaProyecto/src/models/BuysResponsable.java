package models;

import java.util.ArrayList;
import java.util.LinkedList;

// Definición de la clase BuysResponsable que extiende de la clase Empleado, son los encargados de llevar a cabo compras de
//productos.
public class BuysResponsable extends Empleado {
    // Atributo que almacena el monto disponible para compras
    private int avaibleMonto;
    // Lista enlazada que almacena las compras en espera por dar de alta
    private LinkedList<Compra> listaComprasEspera = new LinkedList<>();
    // Lista que almacena las compras de alta
    private ArrayList<Compra> listaComprasDeAlta = new ArrayList<>();

    // Método getter para obtener el monto disponible para compras
    public int getAvaibleMonto() {
        return avaibleMonto;
    }

    // Método setter para establecer el monto disponible para compras
    public void setAvaibleMonto(int monto) {
        this.avaibleMonto = monto;
    }

    // Método getter para obtener la lista de compras en espera por dar de alta
    public LinkedList<Compra> getListaComprasEspera() {
        return listaComprasEspera;
    }

    // Método para añadir una compra a la lista de compras en espera por dar de alta
    public boolean setListaComprasEspera(Compra newCompraEspera) {
        return listaComprasEspera.add(newCompraEspera);
    }

    // Método getter para obtener la lista de compras de alta
    public ArrayList<Compra> getListaComprasDeAlta() {
        return listaComprasDeAlta;
    }

    // Método para agregar la primera compra en espera por dar de alta a la lista de compras de alta
    public void agregarComprasListaAlta() {
        listaComprasDeAlta.add(listaComprasEspera.pollFirst());
    }
}
