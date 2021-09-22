package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Address;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("B")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Item{

    private String author;
    private String isbn;

    public static Book bookOf(Item item, String author, String isbn){
        Book book = new Book();
        book.setName(item.getName());
        book.setPrice(item.getPrice());
        book.setStockQuantity(item.getStockQuantity());
        book.author = author;
        book.isbn = isbn;
        return book;
    }
}
