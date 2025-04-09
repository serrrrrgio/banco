package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.modelo.entidades.BilleteraVirtual;
import co.edu.uniquindio.banco.modelo.entidades.Usuario;
import co.edu.uniquindio.banco.modelo.Singleton.BancoSingleton;
import co.edu.uniquindio.banco.modelo.Singleton.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;


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
        private Button btnActualizar;

        @FXML
        private Button btnRecargar;

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
            // Obtener el usuario que ha iniciado sesión actualmente
            Usuario usuario = Sesion.getInstance().getUsuarioActual();
        
            // Buscar la billetera virtual asociada al usuario en el banco
            BilleteraVirtual billetera = BancoSingleton.getBanco().buscarBilletera(usuario.getId());
        
            // Verificar si se encontró una billetera
            if (billetera != null) {
                // Consultar el saldo de la billetera
                float saldo = billetera.consultarSaldo();
        
                // Mostrar una alerta informativa con el saldo actual del usuario
                UtilidadesVentana.mostrarAlerta("Saldo actual", "Tu saldo actual es: $" + saldo);
            } else {
                // Mostrar un mensaje de error si no se encuentra la billetera
                UtilidadesVentana.mostrarError("Billetera no encontrada", "No se encontró una billetera asociada a tu usuario.");
            }
        }
        
        @FXML
        public void recargarSaldo(ActionEvent actionEvent){
            UtilidadesVentana.navegarVentana("/co/edu/uniquindio/banco/vista/consignar.fxml", "Banco - Recarga");
        }

        @FXML
        public void transferir(ActionEvent actionEvent) {
            UtilidadesVentana.navegarVentana("/co/edu/uniquindio/banco/vista/transferencia.fxml", "Banco - Transferencia");
        }

        @FXML
        public void actualizar(ActionEvent actionEvent){
            UtilidadesVentana.navegarVentana("/co/edu/uniquindio/banco/vista/actualizarDatos.fxml", "Banco - Actualizar Datos");

        }


    }




