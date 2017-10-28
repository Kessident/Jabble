package com.windspinks.Jabble.Repositories;

import com.windspinks.Jabble.Model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Integer>{
}
