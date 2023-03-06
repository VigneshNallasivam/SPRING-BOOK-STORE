package com.example.BookStore.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@Table(name = "book_order")
public class OrderModel
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    LocalDate localDate = LocalDate.now();
    private long price;
    private long quantity;
    private String address;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_user_id")
    private UserModel user;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_book_id")
    private List<BookModel> book;
    private boolean cancel;

    public OrderModel(UserModel user, List<BookModel> book, long price, long quantity, String address, boolean cancel)
    {
        this.price=price;
        this.quantity=quantity;
        this.address=address;
        this.user=user;
        this.book=book;
        this.cancel=cancel;
    }
}