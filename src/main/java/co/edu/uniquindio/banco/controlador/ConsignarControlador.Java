import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ConsignarControlador {

    @FXML
    private TextField txtMonto;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnConsignar;

    @FXML
    public void cancelar (ActionEvent actionEvent){
        UtilidadesVentana.navegarVentana("/co/edu/uniquindio/banco/vista/PanelCliente.fxml", "Banco - Panel Cliente")
        ((stage) btnCancelar.getScene.getWindow()).close()
    }

    @FXML
    public void consignarDinero(ActionEvent actionEvent){
        String textoMonto = txtMonto.getText();

        if(textoMonto == null || textoMonto.isEmpty()){
        UtilidadesVentana.navegarVentana("Campo Vacio", "Por favor ingrese una monto");
        return;
        }

        float Monto = Float.parseFloat(textoMonto);

        if(monto <= 0){
            UtilidadesVentana.navegarVentana("Error", "El momto debe ser superior a cero, intente nuevamente");
            return;
        }

        Usuario usuarioActual = sesion.getInstance().getUsuarioActual;
        BilleteraVirtual billetera = BancoSingleton.getBanco().buscarBilletera(usuarioActual.getId())

        if(billetera != null){
            billetera.recargarSaldo(monto);
            UtilidadesVentana.navegarVentana("Recarga exitosa", "Se han consignado $" + monto + "correctamente");
            UtilidadesVentana.navegarVentana("/co/edu/uniquindio/banco/vista/PanelCliente.fxml", "Banco - Panel Cliente")
            // Cerrar esta ventana (la de recarga)
            ((Stage) txtMonto.getScene().getWindow()).close();
            
        }else{
            UtilidadesVentana.navegarVentana("Error", "No se ha encontrado una billetera asociada con el Id")
        }

        } catch (NumberFormatException e) {
            // Capturar si el usuario ingresa letras o símbolos no válidos
            UtilidadesVentana.mostrarError("Formato inválido", "Ingresa un número válido para recargar.");
        } catch (Exception e) {
            // Capturar cualquier otro error inesperado
            UtilidadesVentana.mostrarError("Error inesperado", "Ocurrió un error al realizar la recarga.");
            e.printStackTrace();
        }



    }

