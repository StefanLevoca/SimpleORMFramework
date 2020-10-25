package sk.jaro.simpleormframework.dbaccess;

import sk.jaro.fiktivnyprogram.entity.Film;
import sk.jaro.simpleormframework.sql.SqlBuilder;
import sk.jaro.simpleormframework.vynimky.AnnotationMissingException;
import sk.jaro.simpleormframework.reflection.ObjectReflector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SormManager {

    public <T> T getEntityByid(Long id, Class<T> clazz) throws Exception {
        T o = null;
        if (id == null) {
            throw new IllegalArgumentException("id nemoze byt prazdne");
        }
        if (!ObjectReflector.isTable(clazz)) {
            throw new AnnotationMissingException("Objekt nemá anotáciu Tabulka.");
        } else {
            System.out.println("is table " + clazz.getName());
        }
        DatabaseAccess databaseAccess = null;
        ResultSet rs = null;
        try {
            databaseAccess = new DatabaseAccess();
            //nacitame data z db
            rs = loadData(id, clazz, databaseAccess);
            //nasetujeme na objekt
            if (rs.next())
                o = ObjectReflector.getFilledData(rs, clazz);
            rs.close();
            databaseAccess.commit();
        } catch (Exception e) {
            if (databaseAccess != null) {
                databaseAccess.rollBack();
            }
            throw new Exception(e);
        } finally {
            if (databaseAccess != null) {
                databaseAccess.disconnect();
            }
        }
        return o;
    }

    private <T> ResultSet loadData(Long id, Class<T> clazz, DatabaseAccess databaseAccess) throws Exception {
        String tableName = ObjectReflector.getTableName(clazz);
        List<String> tableColumns = ObjectReflector.getColumnNames(clazz);
        String idColumnName = ObjectReflector.getIdColumnName(clazz);
        String query = SqlBuilder.buildQuery(id, tableName, idColumnName, tableColumns);
        System.out.println(query);
        return databaseAccess.executeQuery(query);
    }

    //DOMACA ULOHA
    public <T> void insertEntity(T object) throws Exception {
        Class<?> clazz = object.getClass();
        String tableName = ObjectReflector.getTableName(clazz);
        List<String> tableColumns = ObjectReflector.getColumnNames(clazz);

        Map<String, Object> data = ObjectReflector.getObjectData(clazz);
        String query = SqlBuilder.buildInsertQuery(tableName, tableColumns, data);
        DatabaseAccess databaseAccess = new DatabaseAccess();
        databaseAccess.insert(query);
        System.out.println(query);

//        insert into MOVIE (REZISER,HLAVNA_ULOHA,ID) VALUES ('','',0);
    }
}
