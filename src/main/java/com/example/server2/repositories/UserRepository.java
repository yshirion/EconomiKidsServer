package com.example.server2.repositories;

import com.example.server2.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE users.family_id = ?1 and users.parent = ?2",nativeQuery = true)
    List<User> findByFamilyId(Long familyId, boolean ifParent);

    @Query(value = "SELECT * FROM users WHERE users.user_name = ?1 and users.password  = ?2",nativeQuery = true)
    Optional<User> checkUser(String name, String password);

    @Query(value = "SELECT * FROM users WHERE users.user_name = ?",nativeQuery = true)
    Optional<User> checkUserByName(String name);

    @Query(value = "SELECT * FROM users WHERE users.family_id = ?1 and users.parent = ?2",nativeQuery = true)
    Optional<User> findParentByFamily(Long familyId, boolean ifParent);

    @Query(value = "SELECT balance FROM users u WHERE u.id =?1", nativeQuery = true)
    Double getBalance(Long id);

    @Modifying
    @Query("UPDATE User u SET u.balance =:new_balance WHERE u.id =:id")
    void updateUser(@Param("new_balance") double new_balance, @Param("id") long id);
}
