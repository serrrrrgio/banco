package co.edu.uniquindio.banco.controlador;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class UtilidadesVentana {

    /**
     * Muestra una alerta informativa.
     * @param titulo Título de la alerta
     * @param mensaje Contenido de la alerta
     */
    public static void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Muestra un cuadro de diálogo de error.
     * @param titulo Título del cuadro
     * @param mensaje Contenido del error
     */
    public static void mostrarError(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText("Ha ocurrido un error");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Carga y navega hacia una nueva ventana (Stage).
     * @param nombreArchivoFxml Ruta del archivo FXML
     * @param tituloVentana Título de la nueva ventana
     */
    public static void navegarVentana(String nombreArchivoFxml, String tituloVentana) {
        try {
            FXMLLoader loader = new FXMLLoader(UtilidadesVentana.class.getResource(nombreArchivoFxml));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);
            stage.show();

        } catch (Exception e) {
            mostrarError("Error al cargar ventana", e.getMessage());
            e.printStackTrace();
        }
    }
}
