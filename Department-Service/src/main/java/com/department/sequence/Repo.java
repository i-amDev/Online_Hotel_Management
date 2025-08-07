package com.department.sequence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repo extends MongoRepository<Sequence, String>{

}
