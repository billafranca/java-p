package api;
import java.util.List;

public class AppUtil <T>{

    public static <T> void verificarObjeto(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("O objeto da API não pode ser nulo");
        }
        System.out.println("Processando tipo: " + obj.getClass().getSimpleName());
    }
}
