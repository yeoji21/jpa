package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor

    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("memberA", "서울");
            em.persist(member);

            Book book1 = createBook("JPA 1", 10_000, 100);
            em.persist(book1);

            Book book2 = createBook("JPA 2", 20_000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, book1.getPrice(), 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, book2.getPrice(), 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1,orderItem2);
            em.persist(order);
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }


        public void dbInit2() {
            Member member = createMember("memberB", "울산");
            em.persist(member);

            Book book1 = createBook("Spring 1", 20_000, 200);
            em.persist(book1);

            Book book2 = createBook("Spring 2", 40_000, 300);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, book1.getPrice(), 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, book2.getPrice(), 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1,orderItem2);
            em.persist(order);
        }

        private Book createBook(String s, int i, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(s);
            book1.setPrice(i);
            book1.setStockQuantity(stockQuantity);
            return book1;
        }

        private Member createMember(String memberA, String 서울) {
            Member member = new Member();
            member.setName(memberA);
            member.setAddress(new Address(서울, "11", "111-111"));
            return member;
        }
    }

}
