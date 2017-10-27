package com.arrow.user.model;

import com.byrd.com.dto.BaseModel;

/**
 * DESCRIPTION:
 * Created by BYRD on 26/10/2017
 * Version 0.1
 */
public class User extends BaseModel {
    private Integer id;
    private String name;
    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
