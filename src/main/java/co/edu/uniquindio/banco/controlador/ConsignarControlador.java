package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.modelo.Singleton.BancoSingleton;
import co.edu.uniquindio.banco.modelo.Singleton.Sesion;
import co.edu.uniquindio.banco.modelo.entidades.BilleteraVirtual;
import co.edu.uniquindio.banco.modelo.entidades.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConsignarControlador {

    @FXML
    private TextField txtMonto;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnConsignar;

    /**
     * Método para cancelar la operación de consignar y volver al panel del cliente.
     */
    @FXML
    public void cancelar(ActionEvent actionEvent) {
        // Navega al panel del cliente
        UtilidadesVentana.navegarVentana("/PanelCliente.fxml", "Banco - Panel Cliente");

        // Cierra la ventana actual
        ((Stage) btnCancelar.getScene().getWindow()).close();
    }

    /**
     * Método que realiza la recarga del monto ingresado a la billetera del usuario actual.
     */
    @FXML
    public void consignarDinero(ActionEvent actionEvent) {
        try {
            // Obtener el texto ingresado en el campo de monto
            String textoMonto = txtMonto.getText();

            // Validar que el campo no esté vacío
            if (textoMonto == null || textoMonto.isEmpty()) {
                UtilidadesVentana.mostrarError("Campo Vacío", "Por favor ingrese un monto");
                return;
            }

            // Convertir el texto a un número flotante
            float monto = Float.parseFloat(textoMonto);

            // Validar que el monto sea mayor a cero
            if (monto <= 0) {
                UtilidadesVentana.mostrarError("Error", "El monto debe ser superior a cero, intente nuevamente");
                return;
            }

            // Obtener el usuario actual desde la sesión
            Usuario usuarioActual = Sesion.getInstance().getUsuarioActual();

            // Buscar la billetera asociada al usuario
            BilleteraVirtual billetera = BancoSingleton.getBanco().buscarBilletera(usuarioActual.getId());

            // Validar que se encontró la billetera
            if (billetera != null) {
                // Realizar la recarga en la billetera
                BancoSingleton.getBanco().recargarBilletera(
                    BancoSingleton.getBanco().buscarBilleteraUsuario(usuarioActual.getId()).getNumero(), 
                    monto
                );

                // Mostrar mensaje de éxito
                UtilidadesVentana.mostrarAlerta("Recarga exitosa", "Se han consignado $" + monto + " correctamente");

                // Cerrar la ventana de recarga
                ((Stage) txtMonto.getScene().getWindow()).close();
            } else {
                // Mostrar error si no se encuentra la billetera
                UtilidadesVentana.mostrarError("Error", "No se ha encontrado una billetera asociada con el Id.");
            }

        } catch (NumberFormatException e) {
            // Manejar error si el valor ingresado no es un número válido
            UtilidadesVentana.mostrarError("Formato inválido", "Ingresa un número válido para recargar.");
        } catch (Exception e) {
            // Manejar errores inesperados
            UtilidadesVentana.mostrarError("Error inesperado", "Ocurrió un error al realizar la recarga.");
            e.printStackTrace();
        }
    }
}


