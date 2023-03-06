package com.example.BookStore.repository;
import com.example.BookStore.model.CartModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface CartRepository extends JpaRepository<CartModel,Long>
{
//    @Transactional
//    @Modifying
//    @Query(value = "delete from stores.cart where cart.cart_id=:id",nativeQuery = true)
//    void deleteById(Long id);
}
