package sk.jaro.simpleormframework.sql;

import java.util.List;
import java.util.Map;

public class SqlBuilder {

    public static String buildQuery(Long id, String tableName, String idColumnName, List<String> tableColumns) {
        StringBuilder stlpce = new StringBuilder();
        for (String s : tableColumns) {
            stlpce.append(s).append(",");
        }
        String stlpceQuery = stlpce.toString().substring(0, stlpce.lastIndexOf(","));
        return String.format("SELECT %s FROM %s WHERE %s=%d", stlpceQuery, tableName, idColumnName, id);
    }

    //PRIDANA METODA Z DOMACEJ ULOHY
    public static String buildInsertQuery(String tableName, List<String> tableColumns, Map<String, Object> data) {
        return String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (%s, %s, %s, %s)", tableName, tableColumns.get(0), tableColumns.get(1), tableColumns.get(2), tableColumns.get(3), data.get(tableColumns.get(0)), data.get(tableColumns.get(1)), data.get(tableColumns.get(2)), data.get(tableColumns.get(3)));
    }
}
