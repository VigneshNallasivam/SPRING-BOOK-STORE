package com.example.BookStore.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Table(name = "cart")
@NoArgsConstructor
public class CartModel
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "cart_user_id")
    private UserModel userId;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "cart_book_id")
    private BookModel bookId;
    private long quantity;
    public CartModel(UserModel user,BookModel book,long quantity)
    {
        this.userId=user;
        this.bookId=book;
        this.quantity=quantity;
    }
}
