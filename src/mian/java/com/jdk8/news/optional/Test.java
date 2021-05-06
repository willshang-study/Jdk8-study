package mian.java.com.jdk8.news.optional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Test {

    static Map policy = new HashMap();

    public static void main(String[] args) {

        // 1. 传统判空方式
        String name = "will";
        if(name != null){
            System.out.println(name);
        }

        // 1.1 有值的时候才需要执行Consumer
        Optional<String> optional = Optional.ofNullable("Will can user Optional correctly!");
        optional.ifPresent(item -> System.out.println(item));

        /*********************分割线***************************/

        // 2. 传统判空方式if,else
        Date proposalDate1;
        if(policy.get("ProposalDate") != null){
            proposalDate1 = (Date)policy.get("ProposalDate");
        }else{
            proposalDate1 = new Date();
        }
        System.out.println(proposalDate1);

        // 2.1
        // 1.ofNullable构造Optional
        // 2.orElse判断不为null，则使用Optional内的value，为null则使用 传递给orElse的参数
        Date proposalDate2 = Optional.ofNullable((Date)policy.get("ProposalDate")).orElse(new Date());
        System.out.println(proposalDate2);
    }
}
