package com.experiment.exp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.experiment.exp.model.SampleEntity;

/*	Use JpaRepository if you’re working with JPA and need advanced features like flushing or batch operations.
	Use CrudRepository if you only need basic CRUD functionality.
	Use PagingAndSortingRepository if you need pagination and sorting but don’t require JPA-specific features.
	Use MongoRepository if you’re working with MongoDB.
	Use ReactiveCrudRepository if you’re building a reactive application.
*/
public interface SampleRepository extends JpaRepository<SampleEntity, Integer>{

}
