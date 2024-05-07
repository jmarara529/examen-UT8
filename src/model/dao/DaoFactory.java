package model.dao;

import datasource.DataSource;

public class DaoFactory {
    public static ProductoDao createProductDao(){
        return new ProductoDaoImpl(DataSource.getConnection());
    }
}
