package com.example.BookStore.repository;
import com.example.BookStore.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<UserModel,Long>
{
    @Query(value = "select * from stores.user where user.email= :email",nativeQuery = true)
    UserModel findByEmail(String email);
}