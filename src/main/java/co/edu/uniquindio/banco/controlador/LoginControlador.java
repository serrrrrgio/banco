package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.modelo.Singleton.BancoSingleton;
import co.edu.uniquindio.banco.modelo.Singleton.Sesion;
import co.edu.uniquindio.banco.modelo.entidades.Banco;
import co.edu.uniquindio.banco.modelo.entidades.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Clase que representa el controlador de la vista de login
 * @author caflorezvi
 */
public class LoginControlador {

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPassword;

    private Banco banco;

    public LoginControlador() {
        this.banco = BancoSingleton.getBanco();
    }

    @FXML
    public void iniciarSesion(ActionEvent actionEvent) {
        String id = txtId.getText();
        String password = txtPassword.getText();

        Usuario usuario = banco.iniciarSesion(id, password);

        if (usuario != null) {
            Sesion.getInstance().setUsuarioActual(usuario);

            try {
                // Cerramos la ventana actual
                Stage stageActual = (Stage) txtId.getScene().getWindow();
                stageActual.close();

                // Abrimos la ventana del panel del cliente
                UtilidadesVentana.navegarVentana("/co/edu/uniquindio/banco/vista/panelCliente.fxml", "Banco - Panel Cliente");

            } catch (Exception e) {
                UtilidadesVentana.mostrarError("Error al cargar ventana", "No se pudo cargar el panel del cliente.\n" + e.getMessage());
            }

        } else {
            UtilidadesVentana.mostrarAlerta("Inicio de sesión", "Credenciales incorrectas. Por favor, inténtalo de nuevo.");
        }
    }
}

