package com.serviceone.repository;

import com.serviceone.entitys.Subject;
import com.serviceone.entitys.User;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE LOWER(u.mail) = LOWER(:mail) AND LOWER(u.password) = LOWER(:password)")
    public User login(@Param("mail") String mail, @Param("password") String password);

    @Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name)")
    public User findByName(@Param("name") String name);
    
     @Query("SELECT u.followingSubjects FROM User u WHERE LOWER(u.name) = LOWER(:name)")
    public List<Subject> getAllSubjectsPerUserFromOne(@Param("name") String name);

    
}
