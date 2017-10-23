package com.et.springwebapp.entity;

/**
 * Created by tony on 17/10/20.
 */
public class User extends BaseEntity {

    protected String tableName = "et_user";

    private String name;

    @Override
    public String getTableName() {
        return tableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
