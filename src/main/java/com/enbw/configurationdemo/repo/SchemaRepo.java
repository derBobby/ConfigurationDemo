package com.enbw.configurationdemo.repo;

import com.enbw.configurationdemo.model.Schema;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchemaRepo extends CrudRepository<Schema, Long> {
}
