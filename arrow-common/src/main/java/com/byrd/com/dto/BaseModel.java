package com.byrd.com.dto;

import java.util.Date;

/**
 * DESCRIPTION: baseModel
 * Created by BYRD on 26/10/2017
 * Version 0.1
 */
public class BaseModel {
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }
}
