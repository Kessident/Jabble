package com.windspinks.Jabble.Repositories;

import com.windspinks.Jabble.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
