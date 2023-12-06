package com.enbw.configurationdemo.repo;

import com.enbw.configurationdemo.model.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepo extends CrudRepository<Configuration, Long> {
}
