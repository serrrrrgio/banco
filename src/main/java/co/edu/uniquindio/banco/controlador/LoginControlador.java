package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.modelo.entidades.Banco;
import co.edu.uniquindio.banco.modelo.entidades.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Clase que representa el controlador de la vista de login
 * @author caflorezvi
 */
public class LoginControlador {

    private final Banco banco;

    public LoginControlador(){
        banco = new Banco();
    }

    @FXML
    public void iniciarSesion(ActionEvent event) {
        String id = txtId.getText();
        String password = txtPassword.getText();

        Usuario usuario = banco.iniciarSesion(id, password);

        if (usuario != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/banco/vista/panelCliente.fxml"));
                Parent root = loader.load();

                PanelClienteControlador controlador = loader.getController();
                controlador.setUsuario(usuario);

                Stage stage = (Stage) txtUsuario.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Muestra una alerta de error
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de inicio de sesión");
            alerta.setHeaderText(null);
            alerta.setContentText("Usuario o contraseña incorrectos");
            alerta.showAndWait();
        }
    }





}
