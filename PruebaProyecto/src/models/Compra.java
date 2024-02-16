package models;

import java.util.ArrayList;

// Definición de la clase Compra, sirve para crear una nueva Compra, la cual es necesaria para que el
//encargado de ventas lleve una lista de las mismas de manera detallada y con informacion consisa
public class Compra {
    // Lista que almacena los productos de la compra
    private ArrayList<Producto> listaProducto = new ArrayList<>();
    // Total de la compra
    private int totalCompra;
    // Fecha de la compra
    private String fechaDeCompra;

    // Método getter para obtener la lista de productos de la compra
    public ArrayList<Producto> getListaProducto() {
        return listaProducto;
    }

    // Método setter para agregar un nuevo producto a la lista de productos de la compra
    public void setListaProducto(Producto newProduct) {
        listaProducto.add(newProduct);
    }

    // Método getter para obtener el total de la compra
    public int getTotalCompra() {
        return totalCompra;
    }

    // Método setter para establecer el total de la compra
    public void setTotalCompra(int totalCompra) {
        this.totalCompra = this.totalCompra + totalCompra;
    }

    // Método getter para obtener la fecha de la compra
    public String getFechaDeCompra() {
        return fechaDeCompra;
    }

    // Método setter para establecer la fecha de la compra
    public void setFechaDeCompra(String fechaDeCompra) {
        this.fechaDeCompra = fechaDeCompra;
    }

    // Método toString para representar la información de la compra como una cadena de texto
    @Override
    public String toString() {
        return "Compra{" +
                "Total=" + totalCompra +
                ", Fecha de compra='" + fechaDeCompra + '\'' +
                '}';
    }
}

