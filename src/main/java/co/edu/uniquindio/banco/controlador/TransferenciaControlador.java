package co.edu.uniquindio.banco.controlador;

import co.edu.uniquindio.banco.modelo.Singleton.BancoSingleton;
import co.edu.uniquindio.banco.modelo.Singleton.Sesion;
import co.edu.uniquindio.banco.modelo.entidades.Banco;
import co.edu.uniquindio.banco.modelo.entidades.Usuario;
import co.edu.uniquindio.banco.modelo.enums.Categoria;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TransferenciaControlador {

    @FXML
    private TextField txtReceptor;

    @FXML
    private TextField txtMonto;

    @FXML
    private ComboBox<Categoria> comboCategoria;

    private Banco banco;

    @FXML
    public void initialize() {
        banco = BancoSingleton.getBanco();
        comboCategoria.getItems().setAll(Categoria.values());
    }

    @FXML
    public void realizarTransferencia() {
        Usuario remitente = Sesion.getInstance().getUsuarioActual();

        if (remitente == null) {
            UtilidadesVentana.mostrarError("Error de sesión", "No hay un usuario logueado.");
            return;
        }

        String idReceptor = txtReceptor.getText().trim();
        String montoTexto = txtMonto.getText().trim();
        Categoria categoria = comboCategoria.getValue();

        if (idReceptor.isEmpty() || montoTexto.isEmpty() || categoria == null) {
            UtilidadesVentana.mostrarError("Campos incompletos", "Completa todos los campos antes de continuar.");
            return;
        }

        try {
            float monto = Float.parseFloat(montoTexto);

            banco.realizarTransferencia(remitente.getId(), idReceptor, monto, categoria);

            UtilidadesVentana.mostrarAlerta("Transferencia exitosa", "La transferencia se realizó correctamente.");

        } catch (NumberFormatException e) {
            UtilidadesVentana.mostrarError("Formato inválido", "El monto debe ser un número válido.");
        } catch (Exception e) {
            UtilidadesVentana.mostrarError("Error al transferir", e.getMessage());
        }
    }
}
