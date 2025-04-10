package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.modelo.Singleton.Sesion;
import co.edu.uniquindio.banco.modelo.entidades.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ActualizarDatosControlador {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDireccion;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void cancelar(ActionEvent actionEvent){
        // Navega de regreso al panel del cliente
        UtilidadesVentana.navegarVentana("/PanelCliente.fxml", "Banco - Panel Cliente");
        // Cierra la ventana actual
        ((Stage) btnCancelar.getScene().getWindow()).close();
    }

    @FXML
    public void initialize() {
        // Obtener el usuario actual desde la sesión
        Usuario usuario = Sesion.getInstance().getUsuarioActual();

        // Validar que haya un usuario activo antes de cargar datos
        if (usuario != null) {
            // Rellenar los campos con los datos del usuario
            txtNombre.setText(usuario.getNombre());
            txtCorreo.setText(usuario.getEmail());
            txtDireccion.setText(usuario.getDireccion());
            txtPassword.setText(usuario.getPassword());
        }
    }

    @FXML
    public void guardarCambios(ActionEvent actionEvent){
        try {
            // Obtener el usuario actual desde la sesión
            Usuario usuario = Sesion.getInstance().getUsuarioActual();

            // Validar que haya sesión activa
            if (usuario == null) {
                UtilidadesVentana.mostrarError("Error de sesión", "No hay ningún usuario logueado.");
                return;
            }

            // Obtener los datos ingresados en los campos de texto
            String nombre = txtNombre.getText().trim();
            String correo = txtCorreo.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String password = txtPassword.getText().trim();

            // Verificar que todos los campos estén llenos
            if (nombre.isEmpty() || correo.isEmpty() || direccion.isEmpty() || password.isEmpty()) {
                UtilidadesVentana.mostrarError("Campos vacíos", "Todos los campos deben estar llenos.");
                return;
            }

            // Actualizar los datos del usuario con los nuevos valores
            usuario.setNombre(nombre);
            usuario.setEmail(correo);
            usuario.setDireccion(direccion);
            usuario.setPassword(password);

            // Mostrar mensaje de confirmación
            UtilidadesVentana.mostrarAlerta("Datos actualizados", "Tus datos han sido actualizados correctamente.");

            // Cerrar la ventana actual
            Stage stage = (Stage) btnGuardar.getScene().getWindow();
            stage.close();

            // Volver al panel del cliente
            UtilidadesVentana.navegarVentana("/co/edu/uniquindio/banco/vista/PanelCliente.fxml", "Banco - Panel Cliente");

        } catch (Exception e) {
            // Mostrar mensaje de error en caso de falla
            UtilidadesVentana.mostrarError("Error", "Ocurrió un error al actualizar los datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

