package model.services;

import model.entities.Producto;

import java.util.List;

public interface ProductService {

    List<Producto> getList();
    List<Producto> getByCriteria(String descripcion, String precio);
    Producto getById(String codProduct);
    Producto save(Producto producto);
    void update(Producto producto);
    void delete(Producto producto);

}
