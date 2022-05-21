package mian.java.com.jdk8.examination;

import mian.java.com.jdk8.examination.model.Policy;
import mian.java.com.jdk8.examination.model.PolicyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问题1：找到已生效的保单列表；
 * 问题2：找到只含有一个被保险人的保单列表，把被保险人姓名用“,”连接起来并打印，如“张三,李四,王五”；
 * 问题3：得到一个Map，key为保单号码，value为每张保单的保期天数
 * 问题4：计算保单列表的平均保费是多少
 * 问题5：用并发流实现Policy的存储(PolicyService#save)，并计算总耗时
 * 问题6：用异步的方式实现Policy的存储(PolicyService#save)，并计算总耗时
 * 问题7: 得到一个新的保单列表，需要对被保险为空的保单用“法外狂徒,100岁”这个被保人兜底
 * 问题8：得到一个Map，key为被保险人名称，value为被保险人年龄
 * 问题9：计算今天开始往后数500天的是哪一天 "YYYY-MM-DD"的格式输出
 * 问题10：给PolicyService新增一个父类【接口】，将save方法提到父类接口内
 */
public class Test {
    public static void main(String[] args) {
        Map a = new HashMap(0);
        List<Policy> policyList = PolicyService.buildPolicyList();
    }
}
