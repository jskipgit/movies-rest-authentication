package com.ironyard.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jasonskipper on 2/13/17.
 */
@Entity
public class Permission {

    public static final String KEY_PERM_CREATE_USR = "PERM_CREATE_USR";
    public static final String KEY_PERM_CREATE_MOVIE = "PERM_CREATE_MOVIE";
    public static final String KEY_PERM_EDIT_USR = "PERM_EDIT_USR";
    public static final String KEY_PERM_EDIT_MOVIE = "PERM_EDIT_MOVIE";

    @Id @GeneratedValue
    private long id;
    private String name;
    private String key;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
