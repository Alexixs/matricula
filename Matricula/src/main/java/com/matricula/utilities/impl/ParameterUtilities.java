package com.matricula.utilities.impl;

import java.lang.reflect.ParameterizedType;

/**
 * clase que define los parametros para una consulta
 *
 * @author Alexis
 */
public class ParameterUtilities<T> {

    private Integer position;
    private String name;
    private T value;
    private int type;

    /*
     * ====================================================================
     * Contructors
     * ====================================================================
     */
    public ParameterUtilities() {
    }

    public ParameterUtilities(String name) {
        this(name, null, java.sql.Types.VARCHAR);
    }

    public ParameterUtilities(String name, T value) {
        this(name, value, java.sql.Types.VARCHAR);
    }

    public ParameterUtilities(String name, T value, int type) {
        this.name = name;
        this.value = value;
        this.type = type;

        this.position = null;
    }

    public ParameterUtilities(Integer position, T value) {
        this(position, value, java.sql.Types.VARCHAR);
    }

    public ParameterUtilities(Integer position, T value, int type) {
        this.position = position;
        this.value = value;
        this.type = type;
        this.name = null;
    }

    public Class<T> getClazz() {
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }

    /*
     * ====================================================================
     * Getter and Setter
     * ====================================================================
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    @SuppressWarnings("unchecked")
    public void setValue(Double value) {
        this.value = (T) value;

    }

    @SuppressWarnings("unchecked")
    public void setValue(Long value) {
        this.value = (T) value;

    }

    @SuppressWarnings("unchecked")
    public void setValue(Integer value) {
        this.value = (T) value;
    }
}
