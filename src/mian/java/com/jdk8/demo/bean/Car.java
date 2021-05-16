package mian.java.com.jdk8.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Machine{
    private String brand = "default";
    private String name = "default";
    private Integer price = 0;

    @Override
    public String toString() {
        return "{brand:"+this.brand+";name:"+this.name+";price:"+this.price+"}";
    }

    public int compareByPrice(Car car){
        return this.price - car.getPrice();
    }

    @Override
    public void start() {
        System.out.println("启动：：：");
    }

    @Override
    public void stop() {
        System.out.println("停止：：：");
    }
}