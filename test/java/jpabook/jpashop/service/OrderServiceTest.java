package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void 상품주문(){
        //given
        Member member = createMember();

        Book book = createBook("시골 JPA", 10000, 10);

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), 2);

        //then
        Order findOrder = orderRepository.findOne(orderId);
        assertEquals(findOrder.getStatus(), OrderStatus.ORDER);
        assertEquals(findOrder.getOrderItems().size(), 1);
        assertEquals(20000, findOrder.getTotalPrice());
        assertEquals(8, book.getStockQuantity());
    }

    @Test
    void 주문수량_초과(){
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);

        int orderCount = 11;

        //when
//        orderService.order(member.getId(), item.getId(), orderCount)

        //then
        assertThrows(NotEnoughStockException.class,
                () -> orderService.order(member.getId(), item.getId(), orderCount));
    }


    @Test
    void 주문취소(){
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        //when
        orderService.cancelOrder(orderId);

        Order findOrder = orderRepository.findOne(orderId);
        //then
        assertEquals(findOrder.getStatus(), OrderStatus.CANCEL);
        assertEquals(item.getStockQuantity(), 10);
    }


    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }
}