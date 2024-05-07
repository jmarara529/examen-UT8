package model.dao;

import datasource.DataSource;
import model.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDaoImpl extends BaseDao implements ProductoDao{

    private Connection connection;

    public ProductoDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Producto> findAll() {
        List<Producto> productos = new ArrayList<Producto>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String sql = "select * from productos order by codigoProduct";
            ps = connection.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()){
                Producto producto = new Producto();
                producto.setCodProduct(rs.getInt("codigoProduct"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setStock(rs.getInt("stock"));
                productos.add(producto);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.closeStatement(ps);
            DataSource.closeResultSet(rs);
        }
        return productos;
    }

    @Override
    public List<Producto> findByCriteria(String descripcion, float precio) {
        List<Producto> productos = new ArrayList<Producto>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from productos where descripcion = ? and precio = ?";
            ps = connection.prepareStatement(sql);

            ps.setString(1,descripcion);
            ps.setFloat(2,precio);

            rs = ps.executeQuery();

            while (rs.next()){
                Producto producto = new Producto();
                producto.setCodProduct(rs.getInt("codigoProduct"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setStock(rs.getInt("stock"));
                productos.add(producto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.closeStatement(ps);
            DataSource.closeResultSet(rs);
        }
        return productos;

    }

    @Override
    public Producto findById(String codProduct) {
        Producto producto = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String sql = "select * from productos where codigoProduct = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(codProduct));

            rs = ps.executeQuery();

            if (rs.next()){
                producto = new Producto();
                producto.setCodProduct(rs.getInt("codigoProduct"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setStock(rs.getInt("stock"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.closeStatement(ps);
            DataSource.closeResultSet(rs);
        }
        return producto;
    }

    @Override
    public Producto save(Producto producto) {

        PreparedStatement ps = null;

        try {

            String sql = "insert into productos(codigoProduct, descripcion, precio, stock)" +
                    "values ?,?,?,?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, producto.getCodProduct());
            ps.setString(2,producto.getDescripcion());
            ps.setFloat(3,producto.getPrecio());
            ps.setInt(4,producto.getStock());

            int fila = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.closeStatement(ps);
        }
        return producto;
    }

    @Override
    public void update(Producto producto) {

        PreparedStatement ps = null;

        try {

            String sql = "update productos set descripcion = ?, precio = ?, stock = ? where codigoProduct = ?";
            ps = connection.prepareStatement(sql);

            ps.setString(1,producto.getDescripcion());
            ps.setFloat(2,producto.getPrecio());
            ps.setInt(3,producto.getStock());
            ps.setInt(4, producto.getCodProduct());

            ps.executeUpdate();


        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.closeStatement(ps);
        }

    }

    @Override
    public void delete(Producto producto) {

        PreparedStatement ps = null;

        try {

            String sql = "delete from productos where codigoProduct = ?";
            ps = connection.prepareStatement(sql);

            ps.setInt(1, producto.getCodProduct());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataSource.closeStatement(ps);
        }

    }
}
