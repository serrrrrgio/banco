package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.modelo.entidades.Usuario;
import co.edu.uniquindio.banco.modelo.Singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;



/**
 * Clase que se encarga de gestionar las acciones de la interfaz gráfica del panel del cliente.
 * @author caflorezvi
 */

public class PanelClienteControlador {


        @FXML
        private TableColumn<?, ?> tlcFecha;

        @FXML
        private TableColumn<?, ?> tlcCategoria;

        @FXML
        private TableColumn<?, ?> tlcUsuario;

        @FXML
        private Label lblNumeroDeCuenta;

        @FXML
        private TableColumn<?, ?> tlcTipo;

        @FXML
        private Button btnCerrarSesion;

        @FXML
        private Button btnTransferir;

        @FXML
        private TableColumn<?, ?> tlcValor;

        @FXML
        private Button btnConsultar;

        @FXML
        private Label lblBienvenido;

    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        Sesion.getInstance().setUsuarioActual(usuario);
        mostrarDatosUsuario(usuario);
    }

        public void mostrarDatosUsuario(Usuario usuario) {
            lblBienvenido.setText(usuario.getNombre() + ", bienvenido a su banco. Aquí podrá ver sus transacciones.");
            lblNumeroDeCuenta.setText("Nro. Cuenta: " + usuario.getId());
        }

        @FXML
        public void cerrarSesion(ActionEvent actionEvent) {
            Sesion.getInstance().cerrarSesion();
            UtilidadesVentana.navegarVentana("/co/edu/uniquindio/banco/vista/login.fxml", "Banco - Iniciar Sesión");
            ((Stage) btnCerrarSesion.getScene().getWindow()).close();
        }

        @FXML
        public void consultar(ActionEvent actionEvent) {

            System.out.println("Consultar movimientos ");
        }

        @FXML
        public void transferir(ActionEvent actionEvent) {
            UtilidadesVentana.navegarVentana("/co/edu/uniquindio/banco/vista/transferencia.fxml", "Banco - Transferencia");
        }


    }




