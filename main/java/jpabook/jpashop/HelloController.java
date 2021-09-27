package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.stream.Stream;


public class HelloController {

    public static void main(String[] args) {
        String[] lineArr = {
                "Believe or not It is true",
                "Do or do not There is no try"
        };
        Stream<String> stream = Arrays.stream(lineArr);
        Stream<String> stringStream = stream.flatMap(l -> Stream.of(l.split(" ")));
        stringStream.forEach(System.out::println);
    }
}
