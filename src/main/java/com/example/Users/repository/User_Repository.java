package com.example.Users.repository;
import com.example.Users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface User_Repository extends JpaRepository <User, Long> {

    Optional<User> findUserByDocumentIs(int document);


    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.document = ?1")
    int enableUser(int document);

    @Modifying
    @Query("UPDATE User u SET u.user_name = :firstName, u.user_lastname = :lastName WHERE u.document = :document")
    int updateUserByDocument(@Param("document") int document, @Param("firstName") String firstName, @Param("lastName") String lastName);

    @Modifying
    @Query("DELETE FROM User u WHERE u.document = :document")
    int deleteUserByDocument(@Param("document") int document);


}