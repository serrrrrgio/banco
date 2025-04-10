package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.modelo.Singleton.BancoSingleton;
import co.edu.uniquindio.banco.modelo.Singleton.Sesion;
import co.edu.uniquindio.banco.modelo.entidades.Banco;
import co.edu.uniquindio.banco.modelo.entidades.Usuario;
import co.edu.uniquindio.banco.modelo.enums.Categoria;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TransferenciaControlador {

    /**
 * Clase que representa el controlador de la vista de transferencia
 * @author Michael Alvarez, Sergio Posada.
 */

    @FXML
    private TextField txtReceptor;

    @FXML
    private TextField txtMonto;

    @FXML
    private ComboBox<Categoria> comboCategoria;

    private Banco banco;

    /**
     * Método que se ejecuta al inicializar el controlador.
     * Inicializa el banco desde el Singleton y llena el ComboBox con las categorías disponibles.
     */
    @FXML
    public void initialize() {
        // Obtener la instancia única del banco
        banco = BancoSingleton.getBanco();

        // Llenar el ComboBox con los valores del enum Categoria
        comboCategoria.getItems().setAll(Categoria.values());
    }

    /**
     * Método para realizar una transferencia desde el usuario actual a otro usuario.
     * Valida los campos y realiza la operación utilizando el banco.
     */
    @FXML
    public void realizarTransferencia() {
        // Obtener el usuario logueado actualmente
        Usuario remitente = Sesion.getInstance().getUsuarioActual();

        // Validar que haya un usuario activo
        if (remitente == null) {
            UtilidadesVentana.mostrarError("Error de sesión", "No hay un usuario logueado.");
            return;
        }

        // Obtener los valores ingresados en los campos de texto y combo
        String idReceptor = txtReceptor.getText().trim();
        String montoTexto = txtMonto.getText().trim();
        Categoria categoria = comboCategoria.getValue();

        // Validar que todos los campos estén llenos
        if (idReceptor.isEmpty() || montoTexto.isEmpty() || categoria == null) {
            UtilidadesVentana.mostrarError("Campos incompletos", "Completa todos los campos antes de continuar.");
            return;
        }

        try {
            // Convertir el texto del monto a un número flotante
            float monto = Float.parseFloat(montoTexto);

            // Realizar la transferencia usando el banco
            banco.realizarTransferencia(remitente.getId(), idReceptor, monto, categoria);

            // Mostrar mensaje de éxito
            UtilidadesVentana.mostrarAlerta("Transferencia exitosa", "La transferencia se realizó correctamente.");

        } catch (NumberFormatException e) {
            // Manejar error si el monto no es un número válido
            UtilidadesVentana.mostrarError("Formato inválido", "El monto debe ser un número válido.");
        } catch (Exception e) {
            // Manejar errores de la lógica de transferencia
            UtilidadesVentana.mostrarError("Error al transferir", e.getMessage());
        }
    }
}

