package dao;

import java.util.List;

public interface Dao<T,k>{

    void insertar(T a);
    void modificar(T a);
    void eliminar(T a);
    List<T> obtenerTodos();
    void obtener(T a);


}
