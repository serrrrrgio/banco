package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.modelo.Singleton.BancoSingleton;
import co.edu.uniquindio.banco.modelo.entidades.Banco;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Clase que representa el controlador de la ventana de registro de usuario
 * @author caflorezvi
 */
public class RegistroControlador {

    @FXML
    private TextField txtIdentificacion;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtDireccion;
    @FXML
    private PasswordField txtPassword;

    private Banco banco;

    /**
     * Constructor de la clase, inicializa el banco
     */
    public RegistroControlador() {
        banco = BancoSingleton.getBanco(); // corregido: se asigna a la variable de clase
    }

    /**
     * Método que se ejecuta al presionar el botón de registrarse
     * @param actionEvent evento de acción
     */
    public void registrarse(ActionEvent actionEvent) {
        try {
            // Se intenta registrar al usuario con los datos del formulario
            banco.registrarUsuario(
                    txtIdentificacion.getText(),
                    txtNombre.getText(),
                    txtDireccion.getText(),
                    txtCorreo.getText(),
                    txtPassword.getText()
            );

            UtilidadesVentana.mostrarAlerta("Registro exitoso", "Usuario registrado correctamente");
            cerrarVentana();

        } catch (Exception e) {
            UtilidadesVentana.mostrarError("Error al registrar", e.getMessage());
        }
    }

    /**
     * Método para cerrar la ventana actual
     */
    private void cerrarVentana() {
        Stage stage = (Stage) txtIdentificacion.getScene().getWindow();
        stage.close();
    }
}
