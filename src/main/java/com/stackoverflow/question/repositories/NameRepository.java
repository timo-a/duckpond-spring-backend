package com.stackoverflow.question.repositories;

import com.stackoverflow.question.entities.Name;
import org.springframework.data.repository.CrudRepository;

public interface NameRepository extends CrudRepository<Name, Long> {
}
