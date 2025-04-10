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
        UtilidadesVentana.navegarVentana("/PanelCliente.fxml", "Banco - Panel Cliente");
        ((Stage) btnCancelar.getScene().getWindow()).close();
    }
    @FXML
    public void initialize() {
    // Obtener el usuario actual desde la sesión
    Usuario usuario = Sesion.getInstance().getUsuarioActual();

    // Validar que haya un usuario activo
    if (usuario != null) {
        txtNombre.setText(usuario.getNombre());
        txtCorreo.setText(usuario.getEmail());
        txtDireccion.setText(usuario.getDireccion());
        txtPassword.setText(usuario.getPassword());
    }
}

    @FXML
    public void guardarCambios(ActionEvent actionEvent){
        try {

        Usuario usuario = Sesion.getInstance().getUsuarioActual();

        if (usuario == null) {
            UtilidadesVentana.mostrarError("Error de sesión", "No hay ningún usuario logueado.");
            return;
        }


        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String password = txtPassword.getText().trim();


        if (nombre.isEmpty() || correo.isEmpty() || direccion.isEmpty() || password.isEmpty()) {
            UtilidadesVentana.mostrarError("Campos vacíos", "Todos los campos deben estar llenos.");
            return;
        }

        usuario.setNombre(nombre);
        usuario.setEmail(correo);
        usuario.setDireccion(direccion);
        usuario.setPassword(password);

        UtilidadesVentana.mostrarAlerta("Datos actualizados", "Tus datos han sido actualizados correctamente.");

        Stage stage = (Stage) btnGuardar.getScene().getWindow();
        stage.close();

        UtilidadesVentana.navegarVentana("/co/edu/uniquindio/banco/vista/PanelCliente.fxml", "Banco - Panel Cliente");

    } catch (Exception e) {
        UtilidadesVentana.mostrarError("Error", "Ocurrió un error al actualizar los datos: " + e.getMessage());
        e.printStackTrace();
    }
    }


}
