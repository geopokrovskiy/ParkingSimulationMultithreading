package com.geopokrovskiy.org.example.model;

import java.util.Objects;

public abstract class AbstractAuto {
    protected String type;
    protected int size;

    protected long id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAuto that = (AbstractAuto) o;
        return size == that.size && id == that.id && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, size, id);
    }
}
