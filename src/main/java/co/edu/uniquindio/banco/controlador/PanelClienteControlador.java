package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.modelo.entidades.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;


public class PanelClienteControlador {

/**
 * Clase que se encarga de gestionar las acciones de la interfaz gráfica del panel del cliente.
 * @author caflorezvi
 */
    private Usuario usuario;

    public void setUsuario(Usuario usaurio) {
        this.usuario = usuario;
    }

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

    public void mostrarDatosUsuario(Usuario usuario) {
        lblBienvenido.setText( usuario.getNombre() + "bienvenido a su banco, aquí podrá ver sus transacciones");
        lblNumeroDeCuenta.setText("Nro. Cuenta: " + usuario.getId());
    }

    @FXML
    public void cerrarSesion(ActionEvent actionEvent) {
    
    navegarVentana("/vista/login.fxml", "Banco - Iniciar Sesión");
    ((Stage) btnCerrarSesion.getScene().getWindow()).close();
}


public void consultar(ActionEvent actionEvent){
    

}

    public void navegarVentana(String nombreArchivoFxml, String tituloVentana) {
        try {

            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();

            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);

            // Mostrar la nueva ventana
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }




    



}



