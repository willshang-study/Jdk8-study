package mian.java.com.jdk8.examination.model;

import java.time.LocalDateTime;
import java.util.List;

public class Policy {
    private String PolicyNo;
    private Integer Premium;
    private List<Risk> RiskList;
    private LocalDateTime EffectiveDate;
    private LocalDateTime ExpireDate;

    public Policy(String policyNo, Integer premium, LocalDateTime effectiveDate, LocalDateTime expireDate) {
        PolicyNo = policyNo;
        Premium = premium;
        EffectiveDate = effectiveDate;
        ExpireDate = expireDate;
    }

    public String getPolicyNo() {
        return PolicyNo;
    }

    public void setPolicyNo(String policyNo) {
        PolicyNo = policyNo;
    }

    public List<Risk> getRiskList() {
        return RiskList;
    }

    public void setRiskList(List<Risk> riskList) {
        RiskList = riskList;
    }

    public LocalDateTime getEffectiveDate() {
        return EffectiveDate;
    }

    public void setEffectiveDate(LocalDateTime effectiveDate) {
        EffectiveDate = effectiveDate;
    }

    public LocalDateTime getExpireDate() {
        return ExpireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        ExpireDate = expireDate;
    }

    public Integer getPremium() {
        return Premium;
    }

    public void setPremium(Integer premium) {
        Premium = premium;
    }
}
