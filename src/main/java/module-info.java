module co.uquindio.edu.co.banco {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.uquindio.edu.co.banco to javafx.fxml;
    exports co.uquindio.edu.co.banco;
}