package model.services;

import model.dao.DaoFactory;
import model.dao.ProductoDao;
import model.entities.Producto;

import java.util.List;

public class ProductServiceImpl implements ProductService{

    private ProductoDao dao;

    public ProductServiceImpl(ProductoDao dao) {
        this.dao = DaoFactory.createProductDao();
    }

    @Override
    public List<Producto> getList() {
        return dao.findAll();
    }

    @Override
    public List<Producto> getByCriteria(String descripcion, String precio) {
        return dao.findByCriteria(descripcion, Float.parseFloat(precio));
    }

    @Override
    public Producto getById(String codProduct) {
        return dao.findById(codProduct);
    }

    @Override
    public Producto save(Producto producto) {
        return dao.save(producto);
    }

    @Override
    public void update(Producto producto) {
        dao.update(producto);
    }

    @Override
    public void delete(Producto producto) {
        dao.delete(producto);
    }
}
