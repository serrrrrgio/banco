package co.edu.uniquindio.banco.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.stage.Stage;

/**
 * Clase que representa el controlador de la vista Inicio
 * @author caflorezvi
 */

public class InicioControlador {
    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Button btnRegistrarse;


    /**
     * Método que permite ir a la vista de Iniciar Sesión
     * @param actionEvent Evento que representa el clic del botón
     */
    public void irIniciarSesion(ActionEvent actionEvent) {
        UtilidadesVentana.navegarVentana("/login.fxml", "Banco - Iniciar Sesión");
    }

    /**
     * Método que permite ir a la vista de Registro de Cliente
     * @param actionEvent Evento que representa el clic del botón
     */
    public void irRegistroCliente(ActionEvent actionEvent) {
        UtilidadesVentana.navegarVentana("/registro.fxml", "Banco - Registro de Cliente");
    }
}
