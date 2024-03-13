package com.zhongfeng.shortlink.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mbg
 */
public class ShortLinkReportDayExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShortLinkReportDayExample() {
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

        public Criteria andOsIsNull() {
            addCriterion("(os='' or os is null)");
            return (Criteria) this;
        }

        public Criteria andOsIsNotNull() {
            addCriterion("(os!='' or os is not null)");
            return (Criteria) this;
        }

        public Criteria andOsEqualTo(String value) {
            addCriterion("os =", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsNotEqualTo(String value) {
            addCriterion("os <>", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsGreaterThan(String value) {
            addCriterion("os >", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsGreaterThanOrEqualTo(String value) {
            addCriterion("os >=", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsLessThan(String value) {
            addCriterion("os <", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsLessThanOrEqualTo(String value) {
            addCriterion("os <=", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsLike(String value) {
            addCriterion("os like", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsNotLike(String value) {
            addCriterion("os not like", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsIn(List<String> values) {
            addCriterion("os in", values, "os");
            return (Criteria) this;
        }

        public Criteria andOsNotIn(List<String> values) {
            addCriterion("os not in", values, "os");
            return (Criteria) this;
        }

        public Criteria andOsBetween(String value1, String value2) {
            addCriterion("os between", value1, value2, "os");
            return (Criteria) this;
        }

        public Criteria andOsNotBetween(String value1, String value2) {
            addCriterion("os not between", value1, value2, "os");
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

        public Criteria andPvNumIsNull() {
            addCriterion("(pv_num='' or pv_num is null)");
            return (Criteria) this;
        }

        public Criteria andPvNumIsNotNull() {
            addCriterion("(pv_num!='' or pv_num is not null)");
            return (Criteria) this;
        }

        public Criteria andPvNumEqualTo(Integer value) {
            addCriterion("pv_num =", value, "pvNum");
            return (Criteria) this;
        }

        public Criteria andPvNumNotEqualTo(Integer value) {
            addCriterion("pv_num <>", value, "pvNum");
            return (Criteria) this;
        }

        public Criteria andPvNumGreaterThan(Integer value) {
            addCriterion("pv_num >", value, "pvNum");
            return (Criteria) this;
        }

        public Criteria andPvNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("pv_num >=", value, "pvNum");
            return (Criteria) this;
        }

        public Criteria andPvNumLessThan(Integer value) {
            addCriterion("pv_num <", value, "pvNum");
            return (Criteria) this;
        }

        public Criteria andPvNumLessThanOrEqualTo(Integer value) {
            addCriterion("pv_num <=", value, "pvNum");
            return (Criteria) this;
        }

        public Criteria andPvNumIn(List<Integer> values) {
            addCriterion("pv_num in", values, "pvNum");
            return (Criteria) this;
        }

        public Criteria andPvNumNotIn(List<Integer> values) {
            addCriterion("pv_num not in", values, "pvNum");
            return (Criteria) this;
        }

        public Criteria andPvNumBetween(Integer value1, Integer value2) {
            addCriterion("pv_num between", value1, value2, "pvNum");
            return (Criteria) this;
        }

        public Criteria andPvNumNotBetween(Integer value1, Integer value2) {
            addCriterion("pv_num not between", value1, value2, "pvNum");
            return (Criteria) this;
        }

        public Criteria andUvNumIsNull() {
            addCriterion("(uv_num='' or uv_num is null)");
            return (Criteria) this;
        }

        public Criteria andUvNumIsNotNull() {
            addCriterion("(uv_num!='' or uv_num is not null)");
            return (Criteria) this;
        }

        public Criteria andUvNumEqualTo(Integer value) {
            addCriterion("uv_num =", value, "uvNum");
            return (Criteria) this;
        }

        public Criteria andUvNumNotEqualTo(Integer value) {
            addCriterion("uv_num <>", value, "uvNum");
            return (Criteria) this;
        }

        public Criteria andUvNumGreaterThan(Integer value) {
            addCriterion("uv_num >", value, "uvNum");
            return (Criteria) this;
        }

        public Criteria andUvNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("uv_num >=", value, "uvNum");
            return (Criteria) this;
        }

        public Criteria andUvNumLessThan(Integer value) {
            addCriterion("uv_num <", value, "uvNum");
            return (Criteria) this;
        }

        public Criteria andUvNumLessThanOrEqualTo(Integer value) {
            addCriterion("uv_num <=", value, "uvNum");
            return (Criteria) this;
        }

        public Criteria andUvNumIn(List<Integer> values) {
            addCriterion("uv_num in", values, "uvNum");
            return (Criteria) this;
        }

        public Criteria andUvNumNotIn(List<Integer> values) {
            addCriterion("uv_num not in", values, "uvNum");
            return (Criteria) this;
        }

        public Criteria andUvNumBetween(Integer value1, Integer value2) {
            addCriterion("uv_num between", value1, value2, "uvNum");
            return (Criteria) this;
        }

        public Criteria andUvNumNotBetween(Integer value1, Integer value2) {
            addCriterion("uv_num not between", value1, value2, "uvNum");
            return (Criteria) this;
        }

        public Criteria andIpNumIsNull() {
            addCriterion("(ip_num='' or ip_num is null)");
            return (Criteria) this;
        }

        public Criteria andIpNumIsNotNull() {
            addCriterion("(ip_num!='' or ip_num is not null)");
            return (Criteria) this;
        }

        public Criteria andIpNumEqualTo(Integer value) {
            addCriterion("ip_num =", value, "ipNum");
            return (Criteria) this;
        }

        public Criteria andIpNumNotEqualTo(Integer value) {
            addCriterion("ip_num <>", value, "ipNum");
            return (Criteria) this;
        }

        public Criteria andIpNumGreaterThan(Integer value) {
            addCriterion("ip_num >", value, "ipNum");
            return (Criteria) this;
        }

        public Criteria andIpNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("ip_num >=", value, "ipNum");
            return (Criteria) this;
        }

        public Criteria andIpNumLessThan(Integer value) {
            addCriterion("ip_num <", value, "ipNum");
            return (Criteria) this;
        }

        public Criteria andIpNumLessThanOrEqualTo(Integer value) {
            addCriterion("ip_num <=", value, "ipNum");
            return (Criteria) this;
        }

        public Criteria andIpNumIn(List<Integer> values) {
            addCriterion("ip_num in", values, "ipNum");
            return (Criteria) this;
        }

        public Criteria andIpNumNotIn(List<Integer> values) {
            addCriterion("ip_num not in", values, "ipNum");
            return (Criteria) this;
        }

        public Criteria andIpNumBetween(Integer value1, Integer value2) {
            addCriterion("ip_num between", value1, value2, "ipNum");
            return (Criteria) this;
        }

        public Criteria andIpNumNotBetween(Integer value1, Integer value2) {
            addCriterion("ip_num not between", value1, value2, "ipNum");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNull() {
            addCriterion("(report_date='' or report_date is null)");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNotNull() {
            addCriterion("(report_date!='' or report_date is not null)");
            return (Criteria) this;
        }

        public Criteria andReportDateEqualTo(String value) {
            addCriterion("report_date =", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotEqualTo(String value) {
            addCriterion("report_date <>", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThan(String value) {
            addCriterion("report_date >", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThanOrEqualTo(String value) {
            addCriterion("report_date >=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThan(String value) {
            addCriterion("report_date <", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThanOrEqualTo(String value) {
            addCriterion("report_date <=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLike(String value) {
            addCriterion("report_date like", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotLike(String value) {
            addCriterion("report_date not like", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateIn(List<String> values) {
            addCriterion("report_date in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotIn(List<String> values) {
            addCriterion("report_date not in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateBetween(String value1, String value2) {
            addCriterion("report_date between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotBetween(String value1, String value2) {
            addCriterion("report_date not between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNull() {
            addCriterion("(operator_id='' or operator_id is null)");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNotNull() {
            addCriterion("(operator_id!='' or operator_id is not null)");
            return (Criteria) this;
        }

        public Criteria andOperatorIdEqualTo(String value) {
            addCriterion("operator_id =", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotEqualTo(String value) {
            addCriterion("operator_id <>", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThan(String value) {
            addCriterion("operator_id >", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("operator_id >=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThan(String value) {
            addCriterion("operator_id <", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThanOrEqualTo(String value) {
            addCriterion("operator_id <=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLike(String value) {
            addCriterion("operator_id like", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotLike(String value) {
            addCriterion("operator_id not like", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIn(List<String> values) {
            addCriterion("operator_id in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotIn(List<String> values) {
            addCriterion("operator_id not in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdBetween(String value1, String value2) {
            addCriterion("operator_id between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotBetween(String value1, String value2) {
            addCriterion("operator_id not between", value1, value2, "operatorId");
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

        public Criteria andMobileIspLikeInsensitive(String value) {
            addCriterion("upper(mobile_isp) like", value.toUpperCase(), "mobileIsp");
            return (Criteria) this;
        }

        public Criteria andMobileProvinceLikeInsensitive(String value) {
            addCriterion("upper(mobile_province) like", value.toUpperCase(), "mobileProvince");
            return (Criteria) this;
        }

        public Criteria andIpIspLikeInsensitive(String value) {
            addCriterion("upper(ip_isp) like", value.toUpperCase(), "ipIsp");
            return (Criteria) this;
        }

        public Criteria andIpProvinceLikeInsensitive(String value) {
            addCriterion("upper(ip_province) like", value.toUpperCase(), "ipProvince");
            return (Criteria) this;
        }

        public Criteria andIpCityLikeInsensitive(String value) {
            addCriterion("upper(ip_city) like", value.toUpperCase(), "ipCity");
            return (Criteria) this;
        }

        public Criteria andBrowserLikeInsensitive(String value) {
            addCriterion("upper(browser) like", value.toUpperCase(), "browser");
            return (Criteria) this;
        }

        public Criteria andOsLikeInsensitive(String value) {
            addCriterion("upper(os) like", value.toUpperCase(), "os");
            return (Criteria) this;
        }

        public Criteria andReportDateLikeInsensitive(String value) {
            addCriterion("upper(report_date) like", value.toUpperCase(), "reportDate");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLikeInsensitive(String value) {
            addCriterion("upper(operator_id) like", value.toUpperCase(), "operatorId");
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