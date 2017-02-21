package com.ironyard.data;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jasonskipper on 2/6/17.
 */
@Entity
public class MovieUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movieuser_sequence")
    @SequenceGenerator(name="movieuser_sequence", sequenceName = "mvusr_sequence")
    private long id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String displayName;

    @ManyToMany
    @JsonIdentityReference(alwaysAsId=true) // otherwise first ref as POJO, others as id
    private List<Movie> favorites;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Movie> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Movie> favorites) {
        this.favorites = favorites;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
