package co.edu.uniquindio.banco.modelo.Singleton;

import co.edu.uniquindio.banco.modelo.entidades.Banco;

public class BancoSingleton {

    private static Banco banco;

    public static Banco getBanco() {
        if (banco == null) {
            banco = new Banco();
        }
        return banco;
    }
}
