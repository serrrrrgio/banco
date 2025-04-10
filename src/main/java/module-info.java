module co.edu.uniquindio.banco {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    exports co.edu.uniquindio.banco;
    exports co.edu.uniquindio.banco.modelo.entidades;
    exports co.edu.uniquindio.banco.modelo.vo;
    exports co.edu.uniquindio.banco.controlador;
    exports co.edu.uniquindio.banco.modelo.Singleton;


    opens co.edu.uniquindio.banco.controlador to javafx.fxml;
}
