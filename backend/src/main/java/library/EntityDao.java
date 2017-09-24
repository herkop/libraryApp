package library;

import java.util.List;

public interface EntityDao {
    public <T> List<T> getEntity(String query, Object param);
    public <T> List<T> getEntity(String query, Object param1, Object param2, Object param3);
    public <T> T addEntity(T entity);
    public <T> T deleteEntity(T entity);
}
