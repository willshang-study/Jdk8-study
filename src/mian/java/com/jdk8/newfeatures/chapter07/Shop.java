package mian.java.com.jdk8.newfeatures.chapter07;

public class Shop {

    public static Double getPrice(String productName){
        delay(2000);
        double price = Math.ceil(Math.random()*productName.charAt(0)+productName.charAt(1));
        return price;
    }

    public static Double getDiscount(String productName){
        delay(1000);
        return 0.05d;
    }

    public static void delay(long time){
        try{
            Thread.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
