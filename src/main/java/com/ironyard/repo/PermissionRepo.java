package com.ironyard.repo;

import com.ironyard.data.Permission;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jasonskipper on 2/13/17.
 */
public interface PermissionRepo extends CrudRepository<Permission, Long>{
    public Permission findByKey(String key);
}
