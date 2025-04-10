package co.edu.uniquindio.banco.controlador;
import co.edu.uniquindio.banco.controlador.UtilidadesVentana;
import co.edu.uniquindio.banco.modelo.Singleton.BancoSingleton;
import co.edu.uniquindio.banco.modelo.Singleton.Sesion;
import co.edu.uniquindio.banco.modelo.entidades.Banco;
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

    @FXML
    public void cancelar (ActionEvent actionEvent){
        UtilidadesVentana.navegarVentana("/co/edu/uniquindio/banco/vista/PanelCliente.fxml", "Banco - Panel Cliente");
        ((Stage) btnCancelar.getScene().getWindow()).close();
    }

    @FXML
    public void consignarDinero(ActionEvent actionEvent) {
        try {
            String textoMonto = txtMonto.getText();

            if (textoMonto == null || textoMonto.isEmpty()) {
                UtilidadesVentana.mostrarError("Campo Vacío", "Por favor ingrese un monto");
                return;
            }

            float monto = Float.parseFloat(textoMonto);

            if (monto <= 0) {
                UtilidadesVentana.mostrarError("Error", "El monto debe ser superior a cero, intente nuevamente");
                return;
            }

            Usuario usuarioActual = Sesion.getInstance().getUsuarioActual();
            BilleteraVirtual billetera = BancoSingleton.getBanco().buscarBilletera(usuarioActual.getId());

            if (billetera != null) {
                BancoSingleton.getBanco().recargarBilletera(BancoSingleton.getBanco().buscarBilleteraUsuario(usuarioActual.getId()).getNumero(), monto);
                UtilidadesVentana.mostrarAlerta("Recarga exitosa", "Se han consignado $" + monto + " correctamente");
                ((Stage) txtMonto.getScene().getWindow()).close();
            } else {
                UtilidadesVentana.mostrarError("Error", "No se ha encontrado una billetera asociada con el Id.");
            }


        } catch (NumberFormatException e) {

            UtilidadesVentana.mostrarError("Formato inválido", "Ingresa un número válido para recargar.");
        } catch (Exception e) {

            UtilidadesVentana.mostrarError("Error inesperado", "Ocurrió un error al realizar la recarga.");
            e.printStackTrace();
        }
    }



}

