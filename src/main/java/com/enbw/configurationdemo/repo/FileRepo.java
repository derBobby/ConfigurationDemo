package com.enbw.configurationdemo.repo;

import com.enbw.configurationdemo.model.File;
import com.enbw.configurationdemo.model.FileContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepo extends CrudRepository<File, Long> {
}
