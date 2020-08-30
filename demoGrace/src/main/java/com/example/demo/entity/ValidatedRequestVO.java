package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/** 
* demo
* @author seal 876651109@qq.com
* @date 6/28/2020 9:44 PM
*/
@AllArgsConstructor
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
@Accessors(chain = true)
public class ValidatedRequestVO implements Serializable {
    private static final long serialVersionUID = -4362139990889081422L;
    @Field("str")
    private String str;
    @Field("start_date")
    private Date startDate;
    @Field("end_date")
    private LocalDateTime endDate;
    @Field("begin_date")
    private Date beginDate;

    public String getStr() {
        return this.str;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public Date getBeginDate() {
        return this.beginDate;
    }

    public ValidatedRequestVO setStr(String str) {
        this.str = str;
        return this;
    }

    public ValidatedRequestVO setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public ValidatedRequestVO setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public ValidatedRequestVO setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ValidatedRequestVO)) return false;
        final ValidatedRequestVO other = (ValidatedRequestVO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$str = this.getStr();
        final Object other$str = other.getStr();
        if (this$str == null ? other$str != null : !this$str.equals(other$str)) return false;
        final Object this$startDate = this.getStartDate();
        final Object other$startDate = other.getStartDate();
        if (this$startDate == null ? other$startDate != null : !this$startDate.equals(other$startDate)) return false;
        final Object this$endDate = this.getEndDate();
        final Object other$endDate = other.getEndDate();
        if (this$endDate == null ? other$endDate != null : !this$endDate.equals(other$endDate)) return false;
        final Object this$beginDate = this.getBeginDate();
        final Object other$beginDate = other.getBeginDate();
        if (this$beginDate == null ? other$beginDate != null : !this$beginDate.equals(other$beginDate)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ValidatedRequestVO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $str = this.getStr();
        result = result * PRIME + ($str == null ? 43 : $str.hashCode());
        final Object $startDate = this.getStartDate();
        result = result * PRIME + ($startDate == null ? 43 : $startDate.hashCode());
        final Object $endDate = this.getEndDate();
        result = result * PRIME + ($endDate == null ? 43 : $endDate.hashCode());
        final Object $beginDate = this.getBeginDate();
        result = result * PRIME + ($beginDate == null ? 43 : $beginDate.hashCode());
        return result;
    }
}
