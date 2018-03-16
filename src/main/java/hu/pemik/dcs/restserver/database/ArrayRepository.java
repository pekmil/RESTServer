package hu.pemik.dcs.restserver.database;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;

public class ArrayRepository<T extends Model> extends ArrayList<T> implements Serializable, Repository<T> {

    private static final long serialVersionUID = 2;

    /**
     * Check unique constraints.
     * If not exists add the model to the repository.
     * Else return false
     *
     * @return boolean
     * @throws Exception [Already exists exception]
     */
    @Override
    public boolean insert(T model) throws Exception {

        for (String uniqueKey : (String[]) model.getAttribute("uniqueKeys")) {
            String keyType = model.getAttributeType(uniqueKey);

            if (keyType == "java.lang.String" || keyType == "String") {
                if (this.where(uniqueKey, (String) model.getAttribute(uniqueKey)).exists()) {
                    String modelName = model.toString().split(" ", 2)[0];
                    throw new Exception(modelName + " with this " + uniqueKey + " already exists!");
                }
            } else if (keyType == "int") {
                if (this.where(uniqueKey, (int) model.getAttribute(uniqueKey)).exists()) {
                    String modelName = model.toString().split(" ", 2)[0];
                    throw new Exception(modelName + " with this " + uniqueKey + " already exists!");
                }
            }

        }

        return super.add(model);

    }

    /**
     * Check unique constraints.
     * If not exists add the model to the repository.
     * Else return false
     *
     * @return boolean
     */
    public boolean insertIfNotExists(T model) {

        try {
            return this.insert(model);
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Check if there are any rows in the collection
     *
     * @return boolean
     */
    public boolean exists() {

        return this.size() > 0;

    }

    /**
     * Remove model by id
     *
     * @param id [which model to delete]
     * @return boolean
     */
    public boolean delete(int id) {
        return this.remove(this.find(id));
    }

    /**
     * Synonym for size()
     *
     * @return int
     */
    public int count() {

        return this.size();

    }

    /**
     * Find model by "id" attribute value
     *
     * @param id [which model to find]
     * @return T
     */
    public T find(int id) {
        try {
            return this.where("id", id).get();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Find model by "id" attribute value
     *
     * @param id [which model to find]
     * @return T
     * @throws Exception [modelName not found]
     */
    public T findOrFail(int id) throws Exception {

        try {
            return this.where("id", id).get();
        } catch (Exception e) {
            throw new Exception("Model not found" + this.getClass().getName() + " ID: " + id);
        }

    }

    /**
     * Get the first (probably the only) object
     *
     * @return T
     * @throws Exception [empty repository]
     */
    public T get() throws Exception {

        if (this.exists())
            return this.get(0);

        throw new Exception("The repository is empty (check filters...)");

    }

    /**
     * Filter models by attribute values
     *
     * @param attribute [name of the attribute]
     * @param value     [expected value of the attribute]
     * @return ArrayRepository<T>
     */
    @SuppressWarnings("unchecked")
    public ArrayRepository<T> where(String attribute, String value) {

        ArrayRepository<T> results = new ArrayRepository<T>();

        for (Model model : this) {

            String currentValue = (String) model.getAttribute(attribute);

            // Add object to results if the currentValue of the attribute
            // is equals to the expected value...
            if (value.equals(currentValue)) {
                results.insertIfNotExists((T) model);
            }

        }

        return results;

    }

    /**
     * Filter models by attribute values with int type
     *
     * @param attribute [name of the attribute]
     * @param value     [expected value of the attribute]
     * @return ArrayRepositoryInterface<T>
     */
    @SuppressWarnings("unchecked")
    public ArrayRepository<T> where(String attribute, int value) {

        ArrayRepository<T> results = new ArrayRepository<T>();

        for (Model model : this) {

            int currentValue = (int) model.getAttribute(attribute);

            // Add object to results if the currentValue of the attribute
            // is equals to the expected value...
            if (value == currentValue) {
                results.insertIfNotExists((T) model);
            }

        }

        return results;

    }

    /**
     * Filter models by attribute values with int type
     *
     * @param attribute [name of the attribute]
     * @param value     [expected value of the attribute]
     * @return ArrayRepositoryInterface<T>
     */
    @SuppressWarnings("unchecked")
    public ArrayRepository<T> where(String attribute, boolean value) {

        ArrayRepository<T> results = new ArrayRepository<T>();

        for (Model model : this) {

            boolean currentValue = (boolean) model.getAttribute(attribute);

            // Add object to results if the currentValue of the attribute
            // is equals to the expected value...
            if (value == currentValue) {
                results.insertIfNotExists((T) model);
            }

        }

        return results;

    }

    /**
     * Find models where Timestamp attribute is between startsAt and endsAt.
     *
     * @param attribute [name of the attribute]
     * @param startsAt  [start timestamp]
     * @param endsAt    [end timestamp]
     * @return ArrayRepositoryInterface<T>
     */
    @SuppressWarnings("unchecked")
    public ArrayRepository<T> whereBetween(String attribute, Timestamp startsAt, Timestamp endsAt) {

        ArrayRepository<T> results = new ArrayRepository<T>();
        long start = startsAt.getTime();
        long end = endsAt.getTime();

        for (Model model : this) {

            Timestamp timestampValue = (Timestamp) model.getAttribute(attribute);
            long currentValue = timestampValue.getTime();

            // Add object to results if the currentValue of the attribute
            // is equals to the expected value...
            if (start <= currentValue && currentValue <= end) {
                results.insertIfNotExists((T) model);
            }

        }

        return results;

    }

    /**
     * Find models where interval [startsAt; endsAt]
     * overlaps with [startAttribute, endAttribute] intervals
     *
     * @param startAttribute [name of the attribute which contains the start timestamp]
     * @param endAttribute   [name of the attribute which contains the end timestamp]
     * @param startsAt       [start timestamp]
     * @param endsAt         [end timestamp]
     * @return ArrayRepositoryInterface<T>
     */
    @SuppressWarnings("unchecked")
    public ArrayRepository<T> whereOverlaps(String startAttribute, String endAttribute, Timestamp startsAt, Timestamp endsAt) {

        ArrayRepository<T> results = new ArrayRepository<T>();
        long start = startsAt.getTime();
        long end = endsAt.getTime();

        for (Model model : this) {

            Timestamp startTimestampValue = (Timestamp) model.getAttribute(startAttribute);
            long startValue = startTimestampValue.getTime();
            Timestamp endTimestampValue = (Timestamp) model.getAttribute(endAttribute);
            long endValue = endTimestampValue.getTime();

            // Add object to results if the currentValue of the attribute
            // is equals to the expected value...
            if ((start <= startValue && startValue <= end) || (start <= endValue && endValue <= end)) {
                results.insertIfNotExists((T) model);
            }

        }

        return results;

    }

    /**
     * Find models where Timestamp attribute is in the future
     *
     * @param attribute [name of timestamp attribute]
     * @return ArrayRepositoryInterface<T>
     */
    @SuppressWarnings("unchecked")
    public ArrayRepository<T> whereInFuture(String attribute) {

        ArrayRepository<T> results = new ArrayRepository<T>();
        long now = System.currentTimeMillis();

        for (Model model : this) {

            Timestamp timestampValue = (Timestamp) model.getAttribute(attribute);
            long currentValue = timestampValue.getTime();

            // Add object to results if the currentValue of the attribute
            // is equals to the expected value...
            if (now < currentValue) {
                results.insertIfNotExists((T) model);
            }

        }

        return results;

    }

    /**
     * Sum values (type of attribute must be int)
     *
     * @param attribute [name of the attribute]
     * @return int
     */
    public int sum(String attribute) {

        int sum = 0;

        for (Model model : this) {
            sum += (int) model.getAttribute(attribute);
        }

        return sum;

    }

    /**
     * Calculate average (type of attribute must be int)
     *
     * @param attribute [name of the attribute]
     * @return float
     */
    public float avg(String attribute) {

        return this.sum(attribute) / this.count();

    }

    /**
     * Sort repository by attribute
     *
     * @param attribute [name of the attribute]
     * @return ArrayRepositoryInterface<T>
     */
    public ArrayRepository<T> orderBy(String attribute) {

        if (this.count() == 0) return this;

        String type = "java.lang.String";
        Comparator<T> compare = (o1, o2) -> o1.getAttribute(attribute).toString().compareTo(o2.getAttribute(attribute).toString());

        try {
            type = this.get().getAttributeType(attribute);
        } catch (Exception e) {
            // Suppress messages and errors.
        }

        if (type == "java.sql.Timestamp" || type == "Timestamp") {
            compare = (o1, o2) -> ((Timestamp) o1.getAttribute(attribute)).compareTo((Timestamp) o2.getAttribute(attribute));
        } else if (type == "java.lang.Integer" || type == "int") {
            compare = (o1, o2) -> (int) o1.getAttribute(attribute) - (int) o2.getAttribute(attribute);
        }

        super.sort(compare);

        return this;
    }

    /**
     * Reverse sorting by attribute
     *
     * @param attribute [name of the attribute]
     * @return ArrayRepositoryInterface<T>
     */
    public ArrayRepository<T> orderByDesc(String attribute) {

        String type = "java.lang.String";
        Comparator<T> compare = (o1, o2) -> o2.getAttribute(attribute).toString().compareTo(o1.getAttribute(attribute).toString());

        try {
            type = this.get().getAttributeType(attribute);
        } catch (Exception e) {
            // Suppress messages and errors.
        }

        if (type == "java.sql.Timestamp" || type == "Timestamp") {
            compare = (o1, o2) -> ((Timestamp) o2.getAttribute(attribute)).compareTo((Timestamp) o1.getAttribute(attribute));
        } else if (type == "java.lang.Integer" || type == "int") {
            compare = (o1, o2) -> (int) o2.getAttribute(attribute) - (int) o1.getAttribute(attribute);
        }

        super.sort(compare);

        return this;
    }

    /**
     * Override toString to show ArrayRepository object attributes
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Array Repository...";
    }

}