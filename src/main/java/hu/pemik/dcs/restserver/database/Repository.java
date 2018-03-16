package hu.pemik.dcs.restserver.database;

import java.sql.Timestamp;

public interface Repository<T> extends Iterable<T> {

    /**
     * Check unique constraints.
     * If not exists add the model to the repository.
     *
     * @return boolean
     * @throws Exception [already exists]
     */
    boolean insert(T model) throws Exception;

    /**
     * Check unique constraints.
     * If not exists add the model to the repository.
     *
     * @return boolean
     */
    boolean insertIfNotExists(T model);

    /**
     * Check if there are any rows in the collection
     *
     * @return boolean
     */
    boolean exists();

    /**
     * Remove model by id
     *
     * @param id [which model to delete]
     * @return boolean
     */
    boolean delete(int id);

    /**
     * Get number of object in the repository
     *
     * @return int
     */
    int count();

    /**
     * Find model by "id" attribute value
     *
     * @param id [which model to find]
     * @return T
     */
    T find(int id);

    /**
     * Find model by "id" attribute value
     *
     * @param id [which model to find]
     * @return T
     * @throws Exception [modelName not found]
     */
    T findOrFail(int id) throws Exception;

    /**
     * Get the first (probably the only) object
     *
     * @return T
     * @throws Exception [empty repository]
     */
    T get() throws Exception;

    /**
     * Get the first (probably the only) object
     *
     * @return T
     * @throws Exception [empty repository]
     */
    T get(int i) throws Exception;

    /**
     * Filter objects by attribute values
     *
     * @param attribute [name of the attribute]
     * @param value     [expected value of the attribute]
     * @return Repository<T>
     */
    Repository<T> where(String attribute, String value);

    /**
     * Filter objects by attribute values with int type
     *
     * @param attribute [name of the attribute]
     * @param value     [expected value of the attribute]
     * @return Repository<T>
     */
    Repository<T> where(String attribute, int value);

    /**
     * Filter objects by attribute values with boolean type
     *
     * @param attribute [name of the attribute]
     * @param value     [expected value of the attribute]
     * @return Repository<T>
     */
    Repository<T> where(String attribute, boolean value);

    /**
     * Find objects where Timestamp attibute is between startsAt and endsAt.
     *
     * @param attribute [name of the attribute]
     * @param startsAt  [start timestamp]
     * @param endsAt    [end timestamp]
     * @return Repository<T>
     */
    Repository<T> whereBetween(String attribute, Timestamp startsAt, Timestamp endsAt);

    /**
     * Find objects where interval [startsAt; endsAt]
     * overlaps with [startAttribute, endAttribute] intervals
     *
     * @param startAttribute [name of the attribute which contains the start timestamp]
     * @param endAttribute   [name of the attribute which contains the end timestamp]
     * @param startsAt       [start timestamp]
     * @param endsAt         [end timestamp]
     * @return Repository<T>
     */
    Repository<T> whereOverlaps(String startAttribute, String endAttribute, Timestamp startsAt, Timestamp endsAt);

    /**
     * Find objects where Timestamp attribute is in the future
     *
     * @param attribute [name of the attribute]
     * @return Repository<T>
     */
    Repository<T> whereInFuture(String attribute);

    /**
     * Sum values (type of attribute must be int)
     *
     * @param attribute [name of the attribute]
     * @return int
     */
    int sum(String attribute);

    /**
     * Calculate average (type of attribute must be int)
     *
     * @param attribute [name of the attribute]
     * @return float
     */
    float avg(String attribute);

    /**
     * Sort repository by attribute
     *
     * @param attribute [name of the attribute]
     * @return Repository<T>
     */
    Repository<T> orderBy(String attribute);

    /**
     * Reverse sorting by attribute
     *
     * @param attribute [name of the attribute]
     * @return Repository<T>
     */
    Repository<T> orderByDesc(String attribute);

}