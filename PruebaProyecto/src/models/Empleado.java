package models;
import java.util.Objects;

// Definición de la clase Empleado, usada para generalizar a nuestros empleados y poder hacer un registro de los mismos
public class Empleado {
    // Atributos protegidos que representan el nombre, correo electrónico y contraseña del empleado
    protected String name;
    protected String mail;
    protected String password;

    // Método setter para establecer el nombre del empleado
    public void setName(String name) {
        this.name = name;
    }

    // Método setter para establecer el correo electrónico del empleado
    public void setMail(String mail) {
        this.mail = mail;
    }

    // Método setter para establecer la contraseña del empleado
    public void setPassword(String password) {
        this.password = password;
    }

    // Método getter para obtener el correo electrónico del empleado
    public String getMail() {
        return mail;
    }

    // Método getter para obtener la contraseña del empleado
    public String getPassword() {
        return password;
    }

    // Método getter para obtener el nombre del empleado
    public String getName() {
        return name;
    }

    // Sobrescritura del método equals para comparar objetos Empleado
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Si son la misma instancia, devuelve true
        if (o == null || getClass() != o.getClass()) return false; // Si el objeto o es null o no es una instancia de Empleado, devuelve false
        Empleado empleado = (Empleado) o; // Se realiza un casting seguro del objeto a Empleado
        // Se compara el correo electrónico y la contraseña utilizando el método equals de la clase Objects
        return Objects.equals(mail, empleado.mail) && Objects.equals(password, empleado.password);
    }

    // Sobrescritura del método hashCode para generar un código hash único
    @Override
    public int hashCode() {
        // Se calcula el código hash basado en el correo electrónico y la contraseña utilizando el método hash de la clase Objects
        return Objects.hash(mail, password);
    }
}
