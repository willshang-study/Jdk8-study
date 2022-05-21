package mian.java.com.jdk8.examination.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PolicyService {

    public static List<Policy> buildPolicyList(){

        return IntStream.rangeClosed(1,15).mapToObj(index->{
            Policy policy = new Policy("P0000"+index,1000+10*index,
                    LocalDateTime.of(2022,3,index*2,12,0),
                    LocalDateTime.of(2023,3,1,12,0));
            if(index % 3 == 0){
                policy.setRiskList(Arrays.asList(
                        new Risk("张三"+index,28+index),
                        new Risk("李四"+index,30+index)
                ));
            }else if(index % 3 == 1){
                policy.setRiskList(Arrays.asList(
                        new Risk("王五"+index,28+index)
                ));
            }
            return policy;
        }).collect(Collectors.toList());
    }

    public Policy save(Policy policy){
        try {
            Thread.sleep(2000);
            return policy;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
