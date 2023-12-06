package com.enbw.configurationdemo.repo;

import com.enbw.configurationdemo.model.FileContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileContentRepo extends CrudRepository<FileContent, Long> {
}
