package com.example.server2.userEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE users.family_id = ?1 and users.parent = ?2",nativeQuery = true)
    List<User> findByFamilyId(Long familyId, boolean ifParent);

    @Query(value = "SELECT * FROM users WHERE users.user_name = ?1 and users.password  = ?2",nativeQuery = true)
    Optional<User> checkUser(String name, String password);

    @Query(value = "SELECT * FROM users WHERE users.user_name = ?",nativeQuery = true)
    Optional<User> checkUserByName(String name);
}
