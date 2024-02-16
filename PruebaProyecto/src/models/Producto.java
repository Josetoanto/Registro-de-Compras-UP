package models;

// Definición de la clase Producto, sin estos no podemos llevar a cabo compras, definimos bien el producto para
//cuando el contador desee ver las compras del encargado de ventas sepa bien que fue y cuanto fue lo que compro
public class Producto {
    // Atributos de la clase Producto
    private String name;
    private int precio;
    private int cantidad;
    private int total;

    // Método getter para obtener el nombre del producto
    public String getName() {
        return name;
    }

    // Método setter para establecer el nombre del producto
    public void setName(String name) {
        this.name = name;
    }

    // Método getter para obtener el precio del producto
    public int getPrecio() {
        return precio;
    }

    // Método setter para establecer el precio del producto
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    // Método getter para obtener la cantidad del producto
    public int getCantidad() {
        return cantidad;
    }

    // Método setter para establecer la cantidad del producto
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Método para calcular el total del producto (precio * cantidad) y devolverlo
    public int obtenerTotal() {
        return total = (cantidad * precio);
    }

    // Método toString para representar la información del producto como una cadena de texto
    @Override
    public String toString() {
        return "Producto{" +
                "Nombre='" + name + '\'' +
                ", Precio=" + precio +
                ", Cantidad=" + cantidad +
                '}';
    }
}