package mian.java.com.jdk8.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private String name = "default";
    private Integer price = 0;

    @Override
    public String toString() {
        return "name:"+this.name+",price:"+this.price;
    }
}