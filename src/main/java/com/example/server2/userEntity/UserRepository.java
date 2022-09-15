package com.example.server2.userEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE users.family_id = ?",nativeQuery = true)
    List<User> findByFamilyId(Long familyId);
}
