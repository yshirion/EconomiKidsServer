package com.example.server2.familyEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FamilyRepository extends CrudRepository<Family, Integer> {

}

