package com.example.BookStore.service;
import com.example.BookStore.dto.OrderDTO;
import com.example.BookStore.exception.OrderException;
import com.example.BookStore.model.BookModel;
import com.example.BookStore.model.OrderModel;
import com.example.BookStore.model.UserModel;
import com.example.BookStore.repository.BookRepository;
import com.example.BookStore.repository.CartRepository;
import com.example.BookStore.repository.OrderRepository;
import com.example.BookStore.repository.UserRepository;
import com.example.BookStore.utility.EmailSenderService;
import com.example.BookStore.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class OrderService implements IOrderService
{
    @Autowired
    OrderRepository orderRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    BookRepository bookRepo;
    @Autowired
    CartRepository cartRepo;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService emailSenderService;
    @Override
    public OrderModel insert(OrderDTO orderDTO) throws Exception
    {
        Optional<UserModel> user = userRepo.findById(orderDTO.getUser());
        List<BookModel> bookList=orderDTO.getBook().stream().map(book -> bookRepo.findById(book).get()).collect(Collectors.toList());
        long price=bookList.stream().mapToLong(n-> (n.getPrice())).sum();
        long quantity=bookList.stream().mapToLong(n-> n.getQuantity()).sum();
        if (user.isPresent())
        {
            OrderModel order = new OrderModel(user.get(), bookList, price, quantity, orderDTO.getAddress(),orderDTO.getCancel());
            orderRepo.save(order);
            String token=tokenUtil.createToken(order.getOrderId());
            emailSenderService.sendEmail(user.get().getEmail(), "Order placed!","Hii...."+user.get().getFirstName()+" ! \n\n Your order has been placed successfully! Order details are below: \n\n Order id:  "+order.getOrderId()+"\n\n Order date:  "+order.getLocalDate()+"\n\n Order Price:  "+price+"\n Order quantity:  "+quantity+"\n Order address:  "+order.getAddress()+"\n Order user id:  "+order.getUser()+"\n Order book id:  "+bookList+"\n Order cancellation status:  "+order.isCancel());
            return order;
        }
        else
        {
            throw new OrderException("Please check & try again!");
        }
    }
    @Override
    public List<OrderModel> getAll()
    {
        List<OrderModel> orderList=orderRepo.findAll();
        return orderList;
    }
    @Override
    public OrderModel getById(long id)
    {
        Optional<OrderModel> order=orderRepo.findById(id);
        if(order.isPresent())
        {
            OrderModel order1=orderRepo.findById(id).get();
            return order1;
        }
        else
        {
            throw new OrderException("Cannot find order id: "+id);
        }
    }
    @Override
    public String deleteById(long orderId, long userId)
    {
        Optional<OrderModel> order=orderRepo.findById(orderId);
        Optional<UserModel> user = userRepo.findById(userId);
        if(order.isPresent() && user.isPresent())
        {
            orderRepo.deleteById(orderId);
            emailSenderService.sendEmail(user.get().getEmail(), "Order is deleted!","Hii...."+user.get().getFirstName()+" ! \n\n Your order has been deleted successfully! Order id: "+order.get().getOrderId());
            return "Details has been deleted!";
        }
        else
        {
            throw new OrderException("Cannot find order id: "+orderId);
        }
    }
    @Override
    public OrderModel updateById(long id, OrderDTO orderDTO)
    {
        Optional<UserModel> user=userRepo.findById(orderDTO.getUser());
        List<BookModel> bookList=orderDTO.getBook().stream().map(book->bookRepo.findById(book).get()).collect(Collectors.toList());
        OrderModel order=orderRepo.findById(id).get();
        if (orderRepo.findById(id).isPresent() && user.isPresent())
        {
            order.setAddress(orderDTO.getAddress());
            order.setUser(user.get());
            order.setBook(bookList.stream().map(book -> bookRepo.findById(book.getBookId()).get()).collect(Collectors.toList()));
            order.setCancel(orderDTO.getCancel());
            long price=bookList.stream().mapToLong(n->(n.getPrice())).sum();
            long quantity=bookList.stream().mapToLong(n-> (n.getQuantity())).sum();
            orderRepo.save(order);
            String token=tokenUtil.createToken(order.getOrderId());
            emailSenderService.sendEmail(user.get().getEmail(), "Order is updated!","Hii...."+user.get().getFirstName()+" ! \n\n Your order has been updated successfully! Order details are below: \n\n Order id:  "+order.getOrderId()+"\n Order date:  "+ order.getLocalDate()+"\n Order Price:  "+price+"\n Order quantity:  "+quantity+"\n Order address:  "+order.getAddress()+"\n Order user id:  "+order.getUser()+"\n Order book id:  "+bookList+"\n Order cancellation status:  "+order.isCancel());
            return order;
        }
        else
        {
            throw new OrderException("Please check & try again!");
        }
    }
}
