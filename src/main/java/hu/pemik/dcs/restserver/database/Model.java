package hu.pemik.dcs.restserver.database;

import java.io.Serializable;
import java.lang.reflect.Field;

public abstract class Model implements Serializable {

    private static final long serialVersionUID = 3;

    /**
     * returns with unique keys on the model
     */
    public String[] uniqueKeys = {"id"};

    public int id;

    /**
     * Get attribute type by its name
     * 
     * @param  attributeName [name of the attribute]
     * @return String
     */
    public String getAttributeType(String attributeName) {

        Class<?> classReflection = this.getClass();
        String value = "";

        try {
            Field field = classReflection.getField(attributeName);
            field.setAccessible(true);
            value = field.getType().getName();
        } catch (Exception e) {
            System.out.println("Error while getting attribute type:" + e.getMessage());
        }

        return value;
    }

    /**
     * Get the current value of an attribute by its name
     *
     * @param  attributeName [name fo the attribute]
     * @return Object
     */
    public Object getAttribute(String attributeName) {

        Class<?> classReflection = this.getClass();
        Object value = null;

        try {
            Field field = classReflection.getField(attributeName);
            field.setAccessible(true);
            value = field.get(this);
        } catch (Exception e) {
            System.out.println("Error while getting attribute:" + e.getMessage());
        }

        return value;

    }

}