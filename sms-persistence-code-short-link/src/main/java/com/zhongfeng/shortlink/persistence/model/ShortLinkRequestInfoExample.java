package com.zhongfeng.shortlink.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mbg
 */
public class ShortLinkRequestInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShortLinkRequestInfoExample() {
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

        public Criteria andCustomerNoIsNull() {
            addCriterion("(customer_no='' or customer_no is null)");
            return (Criteria) this;
        }

        public Criteria andCustomerNoIsNotNull() {
            addCriterion("(customer_no!='' or customer_no is not null)");
            return (Criteria) this;
        }

        public Criteria andCustomerNoEqualTo(String value) {
            addCriterion("customer_no =", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoNotEqualTo(String value) {
            addCriterion("customer_no <>", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoGreaterThan(String value) {
            addCriterion("customer_no >", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoGreaterThanOrEqualTo(String value) {
            addCriterion("customer_no >=", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoLessThan(String value) {
            addCriterion("customer_no <", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoLessThanOrEqualTo(String value) {
            addCriterion("customer_no <=", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoLike(String value) {
            addCriterion("customer_no like", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoNotLike(String value) {
            addCriterion("customer_no not like", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoIn(List<String> values) {
            addCriterion("customer_no in", values, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoNotIn(List<String> values) {
            addCriterion("customer_no not in", values, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoBetween(String value1, String value2) {
            addCriterion("customer_no between", value1, value2, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoNotBetween(String value1, String value2) {
            addCriterion("customer_no not between", value1, value2, "customerNo");
            return (Criteria) this;
        }

        public Criteria andUserNoIsNull() {
            addCriterion("(user_no='' or user_no is null)");
            return (Criteria) this;
        }

        public Criteria andUserNoIsNotNull() {
            addCriterion("(user_no!='' or user_no is not null)");
            return (Criteria) this;
        }

        public Criteria andUserNoEqualTo(String value) {
            addCriterion("user_no =", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotEqualTo(String value) {
            addCriterion("user_no <>", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoGreaterThan(String value) {
            addCriterion("user_no >", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoGreaterThanOrEqualTo(String value) {
            addCriterion("user_no >=", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLessThan(String value) {
            addCriterion("user_no <", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLessThanOrEqualTo(String value) {
            addCriterion("user_no <=", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLike(String value) {
            addCriterion("user_no like", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotLike(String value) {
            addCriterion("user_no not like", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoIn(List<String> values) {
            addCriterion("user_no in", values, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotIn(List<String> values) {
            addCriterion("user_no not in", values, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoBetween(String value1, String value2) {
            addCriterion("user_no between", value1, value2, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotBetween(String value1, String value2) {
            addCriterion("user_no not between", value1, value2, "userNo");
            return (Criteria) this;
        }

        public Criteria andAddressMd5IsNull() {
            addCriterion("(address_md5='' or address_md5 is null)");
            return (Criteria) this;
        }

        public Criteria andAddressMd5IsNotNull() {
            addCriterion("(address_md5!='' or address_md5 is not null)");
            return (Criteria) this;
        }

        public Criteria andAddressMd5EqualTo(String value) {
            addCriterion("address_md5 =", value, "addressMd5");
            return (Criteria) this;
        }

        public Criteria andAddressMd5NotEqualTo(String value) {
            addCriterion("address_md5 <>", value, "addressMd5");
            return (Criteria) this;
        }

        public Criteria andAddressMd5GreaterThan(String value) {
            addCriterion("address_md5 >", value, "addressMd5");
            return (Criteria) this;
        }

        public Criteria andAddressMd5GreaterThanOrEqualTo(String value) {
            addCriterion("address_md5 >=", value, "addressMd5");
            return (Criteria) this;
        }

        public Criteria andAddressMd5LessThan(String value) {
            addCriterion("address_md5 <", value, "addressMd5");
            return (Criteria) this;
        }

        public Criteria andAddressMd5LessThanOrEqualTo(String value) {
            addCriterion("address_md5 <=", value, "addressMd5");
            return (Criteria) this;
        }

        public Criteria andAddressMd5Like(String value) {
            addCriterion("address_md5 like", value, "addressMd5");
            return (Criteria) this;
        }

        public Criteria andAddressMd5NotLike(String value) {
            addCriterion("address_md5 not like", value, "addressMd5");
            return (Criteria) this;
        }

        public Criteria andAddressMd5In(List<String> values) {
            addCriterion("address_md5 in", values, "addressMd5");
            return (Criteria) this;
        }

        public Criteria andAddressMd5NotIn(List<String> values) {
            addCriterion("address_md5 not in", values, "addressMd5");
            return (Criteria) this;
        }

        public Criteria andAddressMd5Between(String value1, String value2) {
            addCriterion("address_md5 between", value1, value2, "addressMd5");
            return (Criteria) this;
        }

        public Criteria andAddressMd5NotBetween(String value1, String value2) {
            addCriterion("address_md5 not between", value1, value2, "addressMd5");
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

        public Criteria andUserAgentIsNull() {
            addCriterion("(user_agent='' or user_agent is null)");
            return (Criteria) this;
        }

        public Criteria andUserAgentIsNotNull() {
            addCriterion("(user_agent!='' or user_agent is not null)");
            return (Criteria) this;
        }

        public Criteria andUserAgentEqualTo(String value) {
            addCriterion("user_agent =", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentNotEqualTo(String value) {
            addCriterion("user_agent <>", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentGreaterThan(String value) {
            addCriterion("user_agent >", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentGreaterThanOrEqualTo(String value) {
            addCriterion("user_agent >=", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentLessThan(String value) {
            addCriterion("user_agent <", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentLessThanOrEqualTo(String value) {
            addCriterion("user_agent <=", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentLike(String value) {
            addCriterion("user_agent like", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentNotLike(String value) {
            addCriterion("user_agent not like", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentIn(List<String> values) {
            addCriterion("user_agent in", values, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentNotIn(List<String> values) {
            addCriterion("user_agent not in", values, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentBetween(String value1, String value2) {
            addCriterion("user_agent between", value1, value2, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentNotBetween(String value1, String value2) {
            addCriterion("user_agent not between", value1, value2, "userAgent");
            return (Criteria) this;
        }

        public Criteria andBrowserIsNull() {
            addCriterion("(browser='' or browser is null)");
            return (Criteria) this;
        }

        public Criteria andBrowserIsNotNull() {
            addCriterion("(browser!='' or browser is not null)");
            return (Criteria) this;
        }

        public Criteria andBrowserEqualTo(String value) {
            addCriterion("browser =", value, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserNotEqualTo(String value) {
            addCriterion("browser <>", value, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserGreaterThan(String value) {
            addCriterion("browser >", value, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserGreaterThanOrEqualTo(String value) {
            addCriterion("browser >=", value, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserLessThan(String value) {
            addCriterion("browser <", value, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserLessThanOrEqualTo(String value) {
            addCriterion("browser <=", value, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserLike(String value) {
            addCriterion("browser like", value, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserNotLike(String value) {
            addCriterion("browser not like", value, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserIn(List<String> values) {
            addCriterion("browser in", values, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserNotIn(List<String> values) {
            addCriterion("browser not in", values, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserBetween(String value1, String value2) {
            addCriterion("browser between", value1, value2, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserNotBetween(String value1, String value2) {
            addCriterion("browser not between", value1, value2, "browser");
            return (Criteria) this;
        }

        public Criteria andDataValidateIsNull() {
            addCriterion("(data_validate='' or data_validate is null)");
            return (Criteria) this;
        }

        public Criteria andDataValidateIsNotNull() {
            addCriterion("(data_validate!='' or data_validate is not null)");
            return (Criteria) this;
        }

        public Criteria andDataValidateEqualTo(Byte value) {
            addCriterion("data_validate =", value, "dataValidate");
            return (Criteria) this;
        }

        public Criteria andDataValidateNotEqualTo(Byte value) {
            addCriterion("data_validate <>", value, "dataValidate");
            return (Criteria) this;
        }

        public Criteria andDataValidateGreaterThan(Byte value) {
            addCriterion("data_validate >", value, "dataValidate");
            return (Criteria) this;
        }

        public Criteria andDataValidateGreaterThanOrEqualTo(Byte value) {
            addCriterion("data_validate >=", value, "dataValidate");
            return (Criteria) this;
        }

        public Criteria andDataValidateLessThan(Byte value) {
            addCriterion("data_validate <", value, "dataValidate");
            return (Criteria) this;
        }

        public Criteria andDataValidateLessThanOrEqualTo(Byte value) {
            addCriterion("data_validate <=", value, "dataValidate");
            return (Criteria) this;
        }

        public Criteria andDataValidateIn(List<Byte> values) {
            addCriterion("data_validate in", values, "dataValidate");
            return (Criteria) this;
        }

        public Criteria andDataValidateNotIn(List<Byte> values) {
            addCriterion("data_validate not in", values, "dataValidate");
            return (Criteria) this;
        }

        public Criteria andDataValidateBetween(Byte value1, Byte value2) {
            addCriterion("data_validate between", value1, value2, "dataValidate");
            return (Criteria) this;
        }

        public Criteria andDataValidateNotBetween(Byte value1, Byte value2) {
            addCriterion("data_validate not between", value1, value2, "dataValidate");
            return (Criteria) this;
        }

        public Criteria andBrandIsNull() {
            addCriterion("(brand='' or brand is null)");
            return (Criteria) this;
        }

        public Criteria andBrandIsNotNull() {
            addCriterion("(brand!='' or brand is not null)");
            return (Criteria) this;
        }

        public Criteria andBrandEqualTo(Byte value) {
            addCriterion("brand =", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotEqualTo(Byte value) {
            addCriterion("brand <>", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThan(Byte value) {
            addCriterion("brand >", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThanOrEqualTo(Byte value) {
            addCriterion("brand >=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThan(Byte value) {
            addCriterion("brand <", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThanOrEqualTo(Byte value) {
            addCriterion("brand <=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandIn(List<Byte> values) {
            addCriterion("brand in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotIn(List<Byte> values) {
            addCriterion("brand not in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandBetween(Byte value1, Byte value2) {
            addCriterion("brand between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotBetween(Byte value1, Byte value2) {
            addCriterion("brand not between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andIpNumberIsNull() {
            addCriterion("(ip_number='' or ip_number is null)");
            return (Criteria) this;
        }

        public Criteria andIpNumberIsNotNull() {
            addCriterion("(ip_number!='' or ip_number is not null)");
            return (Criteria) this;
        }

        public Criteria andIpNumberEqualTo(Long value) {
            addCriterion("ip_number =", value, "ipNumber");
            return (Criteria) this;
        }

        public Criteria andIpNumberNotEqualTo(Long value) {
            addCriterion("ip_number <>", value, "ipNumber");
            return (Criteria) this;
        }

        public Criteria andIpNumberGreaterThan(Long value) {
            addCriterion("ip_number >", value, "ipNumber");
            return (Criteria) this;
        }

        public Criteria andIpNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("ip_number >=", value, "ipNumber");
            return (Criteria) this;
        }

        public Criteria andIpNumberLessThan(Long value) {
            addCriterion("ip_number <", value, "ipNumber");
            return (Criteria) this;
        }

        public Criteria andIpNumberLessThanOrEqualTo(Long value) {
            addCriterion("ip_number <=", value, "ipNumber");
            return (Criteria) this;
        }

        public Criteria andIpNumberIn(List<Long> values) {
            addCriterion("ip_number in", values, "ipNumber");
            return (Criteria) this;
        }

        public Criteria andIpNumberNotIn(List<Long> values) {
            addCriterion("ip_number not in", values, "ipNumber");
            return (Criteria) this;
        }

        public Criteria andIpNumberBetween(Long value1, Long value2) {
            addCriterion("ip_number between", value1, value2, "ipNumber");
            return (Criteria) this;
        }

        public Criteria andIpNumberNotBetween(Long value1, Long value2) {
            addCriterion("ip_number not between", value1, value2, "ipNumber");
            return (Criteria) this;
        }

        public Criteria andIpIspIsNull() {
            addCriterion("(ip_isp='' or ip_isp is null)");
            return (Criteria) this;
        }

        public Criteria andIpIspIsNotNull() {
            addCriterion("(ip_isp!='' or ip_isp is not null)");
            return (Criteria) this;
        }

        public Criteria andIpIspEqualTo(String value) {
            addCriterion("ip_isp =", value, "ipIsp");
            return (Criteria) this;
        }

        public Criteria andIpIspNotEqualTo(String value) {
            addCriterion("ip_isp <>", value, "ipIsp");
            return (Criteria) this;
        }

        public Criteria andIpIspGreaterThan(String value) {
            addCriterion("ip_isp >", value, "ipIsp");
            return (Criteria) this;
        }

        public Criteria andIpIspGreaterThanOrEqualTo(String value) {
            addCriterion("ip_isp >=", value, "ipIsp");
            return (Criteria) this;
        }

        public Criteria andIpIspLessThan(String value) {
            addCriterion("ip_isp <", value, "ipIsp");
            return (Criteria) this;
        }

        public Criteria andIpIspLessThanOrEqualTo(String value) {
            addCriterion("ip_isp <=", value, "ipIsp");
            return (Criteria) this;
        }

        public Criteria andIpIspLike(String value) {
            addCriterion("ip_isp like", value, "ipIsp");
            return (Criteria) this;
        }

        public Criteria andIpIspNotLike(String value) {
            addCriterion("ip_isp not like", value, "ipIsp");
            return (Criteria) this;
        }

        public Criteria andIpIspIn(List<String> values) {
            addCriterion("ip_isp in", values, "ipIsp");
            return (Criteria) this;
        }

        public Criteria andIpIspNotIn(List<String> values) {
            addCriterion("ip_isp not in", values, "ipIsp");
            return (Criteria) this;
        }

        public Criteria andIpIspBetween(String value1, String value2) {
            addCriterion("ip_isp between", value1, value2, "ipIsp");
            return (Criteria) this;
        }

        public Criteria andIpIspNotBetween(String value1, String value2) {
            addCriterion("ip_isp not between", value1, value2, "ipIsp");
            return (Criteria) this;
        }

        public Criteria andIpCityIsNull() {
            addCriterion("(ip_city='' or ip_city is null)");
            return (Criteria) this;
        }

        public Criteria andIpCityIsNotNull() {
            addCriterion("(ip_city!='' or ip_city is not null)");
            return (Criteria) this;
        }

        public Criteria andIpCityEqualTo(String value) {
            addCriterion("ip_city =", value, "ipCity");
            return (Criteria) this;
        }

        public Criteria andIpCityNotEqualTo(String value) {
            addCriterion("ip_city <>", value, "ipCity");
            return (Criteria) this;
        }

        public Criteria andIpCityGreaterThan(String value) {
            addCriterion("ip_city >", value, "ipCity");
            return (Criteria) this;
        }

        public Criteria andIpCityGreaterThanOrEqualTo(String value) {
            addCriterion("ip_city >=", value, "ipCity");
            return (Criteria) this;
        }

        public Criteria andIpCityLessThan(String value) {
            addCriterion("ip_city <", value, "ipCity");
            return (Criteria) this;
        }

        public Criteria andIpCityLessThanOrEqualTo(String value) {
            addCriterion("ip_city <=", value, "ipCity");
            return (Criteria) this;
        }

        public Criteria andIpCityLike(String value) {
            addCriterion("ip_city like", value, "ipCity");
            return (Criteria) this;
        }

        public Criteria andIpCityNotLike(String value) {
            addCriterion("ip_city not like", value, "ipCity");
            return (Criteria) this;
        }

        public Criteria andIpCityIn(List<String> values) {
            addCriterion("ip_city in", values, "ipCity");
            return (Criteria) this;
        }

        public Criteria andIpCityNotIn(List<String> values) {
            addCriterion("ip_city not in", values, "ipCity");
            return (Criteria) this;
        }

        public Criteria andIpCityBetween(String value1, String value2) {
            addCriterion("ip_city between", value1, value2, "ipCity");
            return (Criteria) this;
        }

        public Criteria andIpCityNotBetween(String value1, String value2) {
            addCriterion("ip_city not between", value1, value2, "ipCity");
            return (Criteria) this;
        }

        public Criteria andMobileProvinceIsNull() {
            addCriterion("(mobile_province='' or mobile_province is null)");
            return (Criteria) this;
        }

        public Criteria andMobileProvinceIsNotNull() {
            addCriterion("(mobile_province!='' or mobile_province is not null)");
            return (Criteria) this;
        }

        public Criteria andMobileProvinceEqualTo(String value) {
            addCriterion("mobile_province =", value, "mobileProvince");
            return (Criteria) this;
        }

        public Criteria andMobileProvinceNotEqualTo(String value) {
            addCriterion("mobile_province <>", value, "mobileProvince");
            return (Criteria) this;
        }

        public Criteria andMobileProvinceGreaterThan(String value) {
            addCriterion("mobile_province >", value, "mobileProvince");
            return (Criteria) this;
        }

        public Criteria andMobileProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("mobile_province >=", value, "mobileProvince");
            return (Criteria) this;
        }

        public Criteria andMobileProvinceLessThan(String value) {
            addCriterion("mobile_province <", value, "mobileProvince");
            return (Criteria) this;
        }

        public Criteria andMobileProvinceLessThanOrEqualTo(String value) {
            addCriterion("mobile_province <=", value, "mobileProvince");
            return (Criteria) this;
        }

        public Criteria andMobileProvinceLike(String value) {
            addCriterion("mobile_province like", value, "mobileProvince");
            return (Criteria) this;
        }

        public Criteria andMobileProvinceNotLike(String value) {
            addCriterion("mobile_province not like", value, "mobileProvince");
            return (Criteria) this;
        }

        public Criteria andMobileProvinceIn(List<String> values) {
            addCriterion("mobile_province in", values, "mobileProvince");
            return (Criteria) this;
        }

        public Criteria andMobileProvinceNotIn(List<String> values) {
            addCriterion("mobile_province not in", values, "mobileProvince");
            return (Criteria) this;
        }

        public Criteria andMobileProvinceBetween(String value1, String value2) {
            addCriterion("mobile_province between", value1, value2, "mobileProvince");
            return (Criteria) this;
        }

        public Criteria andMobileProvinceNotBetween(String value1, String value2) {
            addCriterion("mobile_province not between", value1, value2, "mobileProvince");
            return (Criteria) this;
        }

        public Criteria andMobileCityIsNull() {
            addCriterion("(mobile_city='' or mobile_city is null)");
            return (Criteria) this;
        }

        public Criteria andMobileCityIsNotNull() {
            addCriterion("(mobile_city!='' or mobile_city is not null)");
            return (Criteria) this;
        }

        public Criteria andMobileCityEqualTo(String value) {
            addCriterion("mobile_city =", value, "mobileCity");
            return (Criteria) this;
        }

        public Criteria andMobileCityNotEqualTo(String value) {
            addCriterion("mobile_city <>", value, "mobileCity");
            return (Criteria) this;
        }

        public Criteria andMobileCityGreaterThan(String value) {
            addCriterion("mobile_city >", value, "mobileCity");
            return (Criteria) this;
        }

        public Criteria andMobileCityGreaterThanOrEqualTo(String value) {
            addCriterion("mobile_city >=", value, "mobileCity");
            return (Criteria) this;
        }

        public Criteria andMobileCityLessThan(String value) {
            addCriterion("mobile_city <", value, "mobileCity");
            return (Criteria) this;
        }

        public Criteria andMobileCityLessThanOrEqualTo(String value) {
            addCriterion("mobile_city <=", value, "mobileCity");
            return (Criteria) this;
        }

        public Criteria andMobileCityLike(String value) {
            addCriterion("mobile_city like", value, "mobileCity");
            return (Criteria) this;
        }

        public Criteria andMobileCityNotLike(String value) {
            addCriterion("mobile_city not like", value, "mobileCity");
            return (Criteria) this;
        }

        public Criteria andMobileCityIn(List<String> values) {
            addCriterion("mobile_city in", values, "mobileCity");
            return (Criteria) this;
        }

        public Criteria andMobileCityNotIn(List<String> values) {
            addCriterion("mobile_city not in", values, "mobileCity");
            return (Criteria) this;
        }

        public Criteria andMobileCityBetween(String value1, String value2) {
            addCriterion("mobile_city between", value1, value2, "mobileCity");
            return (Criteria) this;
        }

        public Criteria andMobileCityNotBetween(String value1, String value2) {
            addCriterion("mobile_city not between", value1, value2, "mobileCity");
            return (Criteria) this;
        }

        public Criteria andMobileIspIsNull() {
            addCriterion("(mobile_isp='' or mobile_isp is null)");
            return (Criteria) this;
        }

        public Criteria andMobileIspIsNotNull() {
            addCriterion("(mobile_isp!='' or mobile_isp is not null)");
            return (Criteria) this;
        }

        public Criteria andMobileIspEqualTo(String value) {
            addCriterion("mobile_isp =", value, "mobileIsp");
            return (Criteria) this;
        }

        public Criteria andMobileIspNotEqualTo(String value) {
            addCriterion("mobile_isp <>", value, "mobileIsp");
            return (Criteria) this;
        }

        public Criteria andMobileIspGreaterThan(String value) {
            addCriterion("mobile_isp >", value, "mobileIsp");
            return (Criteria) this;
        }

        public Criteria andMobileIspGreaterThanOrEqualTo(String value) {
            addCriterion("mobile_isp >=", value, "mobileIsp");
            return (Criteria) this;
        }

        public Criteria andMobileIspLessThan(String value) {
            addCriterion("mobile_isp <", value, "mobileIsp");
            return (Criteria) this;
        }

        public Criteria andMobileIspLessThanOrEqualTo(String value) {
            addCriterion("mobile_isp <=", value, "mobileIsp");
            return (Criteria) this;
        }

        public Criteria andMobileIspLike(String value) {
            addCriterion("mobile_isp like", value, "mobileIsp");
            return (Criteria) this;
        }

        public Criteria andMobileIspNotLike(String value) {
            addCriterion("mobile_isp not like", value, "mobileIsp");
            return (Criteria) this;
        }

        public Criteria andMobileIspIn(List<String> values) {
            addCriterion("mobile_isp in", values, "mobileIsp");
            return (Criteria) this;
        }

        public Criteria andMobileIspNotIn(List<String> values) {
            addCriterion("mobile_isp not in", values, "mobileIsp");
            return (Criteria) this;
        }

        public Criteria andMobileIspBetween(String value1, String value2) {
            addCriterion("mobile_isp between", value1, value2, "mobileIsp");
            return (Criteria) this;
        }

        public Criteria andMobileIspNotBetween(String value1, String value2) {
            addCriterion("mobile_isp not between", value1, value2, "mobileIsp");
            return (Criteria) this;
        }

        public Criteria andIpProvinceIsNull() {
            addCriterion("(ip_province='' or ip_province is null)");
            return (Criteria) this;
        }

        public Criteria andIpProvinceIsNotNull() {
            addCriterion("(ip_province!='' or ip_province is not null)");
            return (Criteria) this;
        }

        public Criteria andIpProvinceEqualTo(String value) {
            addCriterion("ip_province =", value, "ipProvince");
            return (Criteria) this;
        }

        public Criteria andIpProvinceNotEqualTo(String value) {
            addCriterion("ip_province <>", value, "ipProvince");
            return (Criteria) this;
        }

        public Criteria andIpProvinceGreaterThan(String value) {
            addCriterion("ip_province >", value, "ipProvince");
            return (Criteria) this;
        }

        public Criteria andIpProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("ip_province >=", value, "ipProvince");
            return (Criteria) this;
        }

        public Criteria andIpProvinceLessThan(String value) {
            addCriterion("ip_province <", value, "ipProvince");
            return (Criteria) this;
        }

        public Criteria andIpProvinceLessThanOrEqualTo(String value) {
            addCriterion("ip_province <=", value, "ipProvince");
            return (Criteria) this;
        }

        public Criteria andIpProvinceLike(String value) {
            addCriterion("ip_province like", value, "ipProvince");
            return (Criteria) this;
        }

        public Criteria andIpProvinceNotLike(String value) {
            addCriterion("ip_province not like", value, "ipProvince");
            return (Criteria) this;
        }

        public Criteria andIpProvinceIn(List<String> values) {
            addCriterion("ip_province in", values, "ipProvince");
            return (Criteria) this;
        }

        public Criteria andIpProvinceNotIn(List<String> values) {
            addCriterion("ip_province not in", values, "ipProvince");
            return (Criteria) this;
        }

        public Criteria andIpProvinceBetween(String value1, String value2) {
            addCriterion("ip_province between", value1, value2, "ipProvince");
            return (Criteria) this;
        }

        public Criteria andIpProvinceNotBetween(String value1, String value2) {
            addCriterion("ip_province not between", value1, value2, "ipProvince");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIsNull() {
            addCriterion("(request_time='' or request_time is null)");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIsNotNull() {
            addCriterion("(request_time!='' or request_time is not null)");
            return (Criteria) this;
        }

        public Criteria andRequestTimeEqualTo(Date value) {
            addCriterion("request_time =", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotEqualTo(Date value) {
            addCriterion("request_time <>", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeGreaterThan(Date value) {
            addCriterion("request_time >", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("request_time >=", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeLessThan(Date value) {
            addCriterion("request_time <", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeLessThanOrEqualTo(Date value) {
            addCriterion("request_time <=", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIn(List<Date> values) {
            addCriterion("request_time in", values, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotIn(List<Date> values) {
            addCriterion("request_time not in", values, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeBetween(Date value1, Date value2) {
            addCriterion("request_time between", value1, value2, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotBetween(Date value1, Date value2) {
            addCriterion("request_time not between", value1, value2, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestIpIsNull() {
            addCriterion("(request_ip='' or request_ip is null)");
            return (Criteria) this;
        }

        public Criteria andRequestIpIsNotNull() {
            addCriterion("(request_ip!='' or request_ip is not null)");
            return (Criteria) this;
        }

        public Criteria andRequestIpEqualTo(String value) {
            addCriterion("request_ip =", value, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpNotEqualTo(String value) {
            addCriterion("request_ip <>", value, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpGreaterThan(String value) {
            addCriterion("request_ip >", value, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpGreaterThanOrEqualTo(String value) {
            addCriterion("request_ip >=", value, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpLessThan(String value) {
            addCriterion("request_ip <", value, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpLessThanOrEqualTo(String value) {
            addCriterion("request_ip <=", value, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpLike(String value) {
            addCriterion("request_ip like", value, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpNotLike(String value) {
            addCriterion("request_ip not like", value, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpIn(List<String> values) {
            addCriterion("request_ip in", values, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpNotIn(List<String> values) {
            addCriterion("request_ip not in", values, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpBetween(String value1, String value2) {
            addCriterion("request_ip between", value1, value2, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpNotBetween(String value1, String value2) {
            addCriterion("request_ip not between", value1, value2, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestDeviceIsNull() {
            addCriterion("(request_device='' or request_device is null)");
            return (Criteria) this;
        }

        public Criteria andRequestDeviceIsNotNull() {
            addCriterion("(request_device!='' or request_device is not null)");
            return (Criteria) this;
        }

        public Criteria andRequestDeviceEqualTo(String value) {
            addCriterion("request_device =", value, "requestDevice");
            return (Criteria) this;
        }

        public Criteria andRequestDeviceNotEqualTo(String value) {
            addCriterion("request_device <>", value, "requestDevice");
            return (Criteria) this;
        }

        public Criteria andRequestDeviceGreaterThan(String value) {
            addCriterion("request_device >", value, "requestDevice");
            return (Criteria) this;
        }

        public Criteria andRequestDeviceGreaterThanOrEqualTo(String value) {
            addCriterion("request_device >=", value, "requestDevice");
            return (Criteria) this;
        }

        public Criteria andRequestDeviceLessThan(String value) {
            addCriterion("request_device <", value, "requestDevice");
            return (Criteria) this;
        }

        public Criteria andRequestDeviceLessThanOrEqualTo(String value) {
            addCriterion("request_device <=", value, "requestDevice");
            return (Criteria) this;
        }

        public Criteria andRequestDeviceLike(String value) {
            addCriterion("request_device like", value, "requestDevice");
            return (Criteria) this;
        }

        public Criteria andRequestDeviceNotLike(String value) {
            addCriterion("request_device not like", value, "requestDevice");
            return (Criteria) this;
        }

        public Criteria andRequestDeviceIn(List<String> values) {
            addCriterion("request_device in", values, "requestDevice");
            return (Criteria) this;
        }

        public Criteria andRequestDeviceNotIn(List<String> values) {
            addCriterion("request_device not in", values, "requestDevice");
            return (Criteria) this;
        }

        public Criteria andRequestDeviceBetween(String value1, String value2) {
            addCriterion("request_device between", value1, value2, "requestDevice");
            return (Criteria) this;
        }

        public Criteria andRequestDeviceNotBetween(String value1, String value2) {
            addCriterion("request_device not between", value1, value2, "requestDevice");
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

        public Criteria andCustomerNoLikeInsensitive(String value) {
            addCriterion("upper(customer_no) like", value.toUpperCase(), "customerNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLikeInsensitive(String value) {
            addCriterion("upper(user_no) like", value.toUpperCase(), "userNo");
            return (Criteria) this;
        }

        public Criteria andAddressMd5LikeInsensitive(String value) {
            addCriterion("upper(address_md5) like", value.toUpperCase(), "addressMd5");
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

        public Criteria andUserAgentLikeInsensitive(String value) {
            addCriterion("upper(user_agent) like", value.toUpperCase(), "userAgent");
            return (Criteria) this;
        }

        public Criteria andBrowserLikeInsensitive(String value) {
            addCriterion("upper(browser) like", value.toUpperCase(), "browser");
            return (Criteria) this;
        }

        public Criteria andIpIspLikeInsensitive(String value) {
            addCriterion("upper(ip_isp) like", value.toUpperCase(), "ipIsp");
            return (Criteria) this;
        }

        public Criteria andIpCityLikeInsensitive(String value) {
            addCriterion("upper(ip_city) like", value.toUpperCase(), "ipCity");
            return (Criteria) this;
        }

        public Criteria andMobileProvinceLikeInsensitive(String value) {
            addCriterion("upper(mobile_province) like", value.toUpperCase(), "mobileProvince");
            return (Criteria) this;
        }

        public Criteria andMobileCityLikeInsensitive(String value) {
            addCriterion("upper(mobile_city) like", value.toUpperCase(), "mobileCity");
            return (Criteria) this;
        }

        public Criteria andMobileIspLikeInsensitive(String value) {
            addCriterion("upper(mobile_isp) like", value.toUpperCase(), "mobileIsp");
            return (Criteria) this;
        }

        public Criteria andIpProvinceLikeInsensitive(String value) {
            addCriterion("upper(ip_province) like", value.toUpperCase(), "ipProvince");
            return (Criteria) this;
        }

        public Criteria andRequestIpLikeInsensitive(String value) {
            addCriterion("upper(request_ip) like", value.toUpperCase(), "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestDeviceLikeInsensitive(String value) {
            addCriterion("upper(request_device) like", value.toUpperCase(), "requestDevice");
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