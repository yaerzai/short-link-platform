package com.zhongfeng.shortlink.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mbg
 */
public class ShortLinkInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShortLinkInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class BaseGeneratedCriteria {
        protected List<Criterion> criteria;

        protected BaseGeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (org.springframework.util.ObjectUtils.isEmpty(value) || value.toString().trim().length()==0) {
                return;
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("(id='' or id is null)");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("(id!='' or id is not null)");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andShortLinkNoIsNull() {
            addCriterion("(short_link_no='' or short_link_no is null)");
            return (Criteria) this;
        }

        public Criteria andShortLinkNoIsNotNull() {
            addCriterion("(short_link_no!='' or short_link_no is not null)");
            return (Criteria) this;
        }

        public Criteria andShortLinkNoEqualTo(String value) {
            addCriterion("short_link_no =", value, "shortLinkNo");
            return (Criteria) this;
        }

        public Criteria andShortLinkNoNotEqualTo(String value) {
            addCriterion("short_link_no <>", value, "shortLinkNo");
            return (Criteria) this;
        }

        public Criteria andShortLinkNoGreaterThan(String value) {
            addCriterion("short_link_no >", value, "shortLinkNo");
            return (Criteria) this;
        }

        public Criteria andShortLinkNoGreaterThanOrEqualTo(String value) {
            addCriterion("short_link_no >=", value, "shortLinkNo");
            return (Criteria) this;
        }

        public Criteria andShortLinkNoLessThan(String value) {
            addCriterion("short_link_no <", value, "shortLinkNo");
            return (Criteria) this;
        }

        public Criteria andShortLinkNoLessThanOrEqualTo(String value) {
            addCriterion("short_link_no <=", value, "shortLinkNo");
            return (Criteria) this;
        }

        public Criteria andShortLinkNoLike(String value) {
            addCriterion("short_link_no like", value, "shortLinkNo");
            return (Criteria) this;
        }

        public Criteria andShortLinkNoNotLike(String value) {
            addCriterion("short_link_no not like", value, "shortLinkNo");
            return (Criteria) this;
        }

        public Criteria andShortLinkNoIn(List<String> values) {
            addCriterion("short_link_no in", values, "shortLinkNo");
            return (Criteria) this;
        }

        public Criteria andShortLinkNoNotIn(List<String> values) {
            addCriterion("short_link_no not in", values, "shortLinkNo");
            return (Criteria) this;
        }

        public Criteria andShortLinkNoBetween(String value1, String value2) {
            addCriterion("short_link_no between", value1, value2, "shortLinkNo");
            return (Criteria) this;
        }

        public Criteria andShortLinkNoNotBetween(String value1, String value2) {
            addCriterion("short_link_no not between", value1, value2, "shortLinkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoIsNull() {
            addCriterion("(link_no='' or link_no is null)");
            return (Criteria) this;
        }

        public Criteria andLinkNoIsNotNull() {
            addCriterion("(link_no!='' or link_no is not null)");
            return (Criteria) this;
        }

        public Criteria andLinkNoEqualTo(String value) {
            addCriterion("link_no =", value, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoNotEqualTo(String value) {
            addCriterion("link_no <>", value, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoGreaterThan(String value) {
            addCriterion("link_no >", value, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoGreaterThanOrEqualTo(String value) {
            addCriterion("link_no >=", value, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoLessThan(String value) {
            addCriterion("link_no <", value, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoLessThanOrEqualTo(String value) {
            addCriterion("link_no <=", value, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoLike(String value) {
            addCriterion("link_no like", value, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoNotLike(String value) {
            addCriterion("link_no not like", value, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoIn(List<String> values) {
            addCriterion("link_no in", values, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoNotIn(List<String> values) {
            addCriterion("link_no not in", values, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoBetween(String value1, String value2) {
            addCriterion("link_no between", value1, value2, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoNotBetween(String value1, String value2) {
            addCriterion("link_no not between", value1, value2, "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkDomainIsNull() {
            addCriterion("(link_domain='' or link_domain is null)");
            return (Criteria) this;
        }

        public Criteria andLinkDomainIsNotNull() {
            addCriterion("(link_domain!='' or link_domain is not null)");
            return (Criteria) this;
        }

        public Criteria andLinkDomainEqualTo(String value) {
            addCriterion("link_domain =", value, "linkDomain");
            return (Criteria) this;
        }

        public Criteria andLinkDomainNotEqualTo(String value) {
            addCriterion("link_domain <>", value, "linkDomain");
            return (Criteria) this;
        }

        public Criteria andLinkDomainGreaterThan(String value) {
            addCriterion("link_domain >", value, "linkDomain");
            return (Criteria) this;
        }

        public Criteria andLinkDomainGreaterThanOrEqualTo(String value) {
            addCriterion("link_domain >=", value, "linkDomain");
            return (Criteria) this;
        }

        public Criteria andLinkDomainLessThan(String value) {
            addCriterion("link_domain <", value, "linkDomain");
            return (Criteria) this;
        }

        public Criteria andLinkDomainLessThanOrEqualTo(String value) {
            addCriterion("link_domain <=", value, "linkDomain");
            return (Criteria) this;
        }

        public Criteria andLinkDomainLike(String value) {
            addCriterion("link_domain like", value, "linkDomain");
            return (Criteria) this;
        }

        public Criteria andLinkDomainNotLike(String value) {
            addCriterion("link_domain not like", value, "linkDomain");
            return (Criteria) this;
        }

        public Criteria andLinkDomainIn(List<String> values) {
            addCriterion("link_domain in", values, "linkDomain");
            return (Criteria) this;
        }

        public Criteria andLinkDomainNotIn(List<String> values) {
            addCriterion("link_domain not in", values, "linkDomain");
            return (Criteria) this;
        }

        public Criteria andLinkDomainBetween(String value1, String value2) {
            addCriterion("link_domain between", value1, value2, "linkDomain");
            return (Criteria) this;
        }

        public Criteria andLinkDomainNotBetween(String value1, String value2) {
            addCriterion("link_domain not between", value1, value2, "linkDomain");
            return (Criteria) this;
        }

        public Criteria andMobileNoIsNull() {
            addCriterion("(mobile_no='' or mobile_no is null)");
            return (Criteria) this;
        }

        public Criteria andMobileNoIsNotNull() {
            addCriterion("(mobile_no!='' or mobile_no is not null)");
            return (Criteria) this;
        }

        public Criteria andMobileNoEqualTo(String value) {
            addCriterion("mobile_no =", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoNotEqualTo(String value) {
            addCriterion("mobile_no <>", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoGreaterThan(String value) {
            addCriterion("mobile_no >", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoGreaterThanOrEqualTo(String value) {
            addCriterion("mobile_no >=", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoLessThan(String value) {
            addCriterion("mobile_no <", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoLessThanOrEqualTo(String value) {
            addCriterion("mobile_no <=", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoLike(String value) {
            addCriterion("mobile_no like", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoNotLike(String value) {
            addCriterion("mobile_no not like", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoIn(List<String> values) {
            addCriterion("mobile_no in", values, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoNotIn(List<String> values) {
            addCriterion("mobile_no not in", values, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoBetween(String value1, String value2) {
            addCriterion("mobile_no between", value1, value2, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoNotBetween(String value1, String value2) {
            addCriterion("mobile_no not between", value1, value2, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andTableTimeIsNull() {
            addCriterion("(table_time='' or table_time is null)");
            return (Criteria) this;
        }

        public Criteria andTableTimeIsNotNull() {
            addCriterion("(table_time!='' or table_time is not null)");
            return (Criteria) this;
        }

        public Criteria andTableTimeEqualTo(String value) {
            addCriterion("table_time =", value, "tableTime");
            return (Criteria) this;
        }

        public Criteria andTableTimeNotEqualTo(String value) {
            addCriterion("table_time <>", value, "tableTime");
            return (Criteria) this;
        }

        public Criteria andTableTimeGreaterThan(String value) {
            addCriterion("table_time >", value, "tableTime");
            return (Criteria) this;
        }

        public Criteria andTableTimeGreaterThanOrEqualTo(String value) {
            addCriterion("table_time >=", value, "tableTime");
            return (Criteria) this;
        }

        public Criteria andTableTimeLessThan(String value) {
            addCriterion("table_time <", value, "tableTime");
            return (Criteria) this;
        }

        public Criteria andTableTimeLessThanOrEqualTo(String value) {
            addCriterion("table_time <=", value, "tableTime");
            return (Criteria) this;
        }

        public Criteria andTableTimeLike(String value) {
            addCriterion("table_time like", value, "tableTime");
            return (Criteria) this;
        }

        public Criteria andTableTimeNotLike(String value) {
            addCriterion("table_time not like", value, "tableTime");
            return (Criteria) this;
        }

        public Criteria andTableTimeIn(List<String> values) {
            addCriterion("table_time in", values, "tableTime");
            return (Criteria) this;
        }

        public Criteria andTableTimeNotIn(List<String> values) {
            addCriterion("table_time not in", values, "tableTime");
            return (Criteria) this;
        }

        public Criteria andTableTimeBetween(String value1, String value2) {
            addCriterion("table_time between", value1, value2, "tableTime");
            return (Criteria) this;
        }

        public Criteria andTableTimeNotBetween(String value1, String value2) {
            addCriterion("table_time not between", value1, value2, "tableTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("(create_time='' or create_time is null)");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("(create_time!='' or create_time is not null)");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andShortLinkNoLikeInsensitive(String value) {
            addCriterion("upper(short_link_no) like", value.toUpperCase(), "shortLinkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNoLikeInsensitive(String value) {
            addCriterion("upper(link_no) like", value.toUpperCase(), "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkDomainLikeInsensitive(String value) {
            addCriterion("upper(link_domain) like", value.toUpperCase(), "linkDomain");
            return (Criteria) this;
        }

        public Criteria andMobileNoLikeInsensitive(String value) {
            addCriterion("upper(mobile_no) like", value.toUpperCase(), "mobileNo");
            return (Criteria) this;
        }

        public Criteria andTableTimeLikeInsensitive(String value) {
            addCriterion("upper(table_time) like", value.toUpperCase(), "tableTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends BaseGeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}