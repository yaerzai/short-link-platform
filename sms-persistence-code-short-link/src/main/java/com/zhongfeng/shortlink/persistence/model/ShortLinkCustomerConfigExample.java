package com.zhongfeng.shortlink.persistence.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mbg
 */
public class ShortLinkCustomerConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShortLinkCustomerConfigExample() {
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

        public Criteria andLinkNameIsNull() {
            addCriterion("(link_name='' or link_name is null)");
            return (Criteria) this;
        }

        public Criteria andLinkNameIsNotNull() {
            addCriterion("(link_name!='' or link_name is not null)");
            return (Criteria) this;
        }

        public Criteria andLinkNameEqualTo(String value) {
            addCriterion("link_name =", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameNotEqualTo(String value) {
            addCriterion("link_name <>", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameGreaterThan(String value) {
            addCriterion("link_name >", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameGreaterThanOrEqualTo(String value) {
            addCriterion("link_name >=", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameLessThan(String value) {
            addCriterion("link_name <", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameLessThanOrEqualTo(String value) {
            addCriterion("link_name <=", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameLike(String value) {
            addCriterion("link_name like", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameNotLike(String value) {
            addCriterion("link_name not like", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameIn(List<String> values) {
            addCriterion("link_name in", values, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameNotIn(List<String> values) {
            addCriterion("link_name not in", values, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameBetween(String value1, String value2) {
            addCriterion("link_name between", value1, value2, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameNotBetween(String value1, String value2) {
            addCriterion("link_name not between", value1, value2, "linkName");
            return (Criteria) this;
        }

        public Criteria andPlatformNoIsNull() {
            addCriterion("(platform_no='' or platform_no is null)");
            return (Criteria) this;
        }

        public Criteria andPlatformNoIsNotNull() {
            addCriterion("(platform_no!='' or platform_no is not null)");
            return (Criteria) this;
        }

        public Criteria andPlatformNoEqualTo(String value) {
            addCriterion("platform_no =", value, "platformNo");
            return (Criteria) this;
        }

        public Criteria andPlatformNoNotEqualTo(String value) {
            addCriterion("platform_no <>", value, "platformNo");
            return (Criteria) this;
        }

        public Criteria andPlatformNoGreaterThan(String value) {
            addCriterion("platform_no >", value, "platformNo");
            return (Criteria) this;
        }

        public Criteria andPlatformNoGreaterThanOrEqualTo(String value) {
            addCriterion("platform_no >=", value, "platformNo");
            return (Criteria) this;
        }

        public Criteria andPlatformNoLessThan(String value) {
            addCriterion("platform_no <", value, "platformNo");
            return (Criteria) this;
        }

        public Criteria andPlatformNoLessThanOrEqualTo(String value) {
            addCriterion("platform_no <=", value, "platformNo");
            return (Criteria) this;
        }

        public Criteria andPlatformNoLike(String value) {
            addCriterion("platform_no like", value, "platformNo");
            return (Criteria) this;
        }

        public Criteria andPlatformNoNotLike(String value) {
            addCriterion("platform_no not like", value, "platformNo");
            return (Criteria) this;
        }

        public Criteria andPlatformNoIn(List<String> values) {
            addCriterion("platform_no in", values, "platformNo");
            return (Criteria) this;
        }

        public Criteria andPlatformNoNotIn(List<String> values) {
            addCriterion("platform_no not in", values, "platformNo");
            return (Criteria) this;
        }

        public Criteria andPlatformNoBetween(String value1, String value2) {
            addCriterion("platform_no between", value1, value2, "platformNo");
            return (Criteria) this;
        }

        public Criteria andPlatformNoNotBetween(String value1, String value2) {
            addCriterion("platform_no not between", value1, value2, "platformNo");
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

        public Criteria andAddressIsNull() {
            addCriterion("(address='' or address is null)");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("(address!='' or address is not null)");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
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

        public Criteria andShortLinkAddressIsNull() {
            addCriterion("(short_link_address='' or short_link_address is null)");
            return (Criteria) this;
        }

        public Criteria andShortLinkAddressIsNotNull() {
            addCriterion("(short_link_address!='' or short_link_address is not null)");
            return (Criteria) this;
        }

        public Criteria andShortLinkAddressEqualTo(String value) {
            addCriterion("short_link_address =", value, "shortLinkAddress");
            return (Criteria) this;
        }

        public Criteria andShortLinkAddressNotEqualTo(String value) {
            addCriterion("short_link_address <>", value, "shortLinkAddress");
            return (Criteria) this;
        }

        public Criteria andShortLinkAddressGreaterThan(String value) {
            addCriterion("short_link_address >", value, "shortLinkAddress");
            return (Criteria) this;
        }

        public Criteria andShortLinkAddressGreaterThanOrEqualTo(String value) {
            addCriterion("short_link_address >=", value, "shortLinkAddress");
            return (Criteria) this;
        }

        public Criteria andShortLinkAddressLessThan(String value) {
            addCriterion("short_link_address <", value, "shortLinkAddress");
            return (Criteria) this;
        }

        public Criteria andShortLinkAddressLessThanOrEqualTo(String value) {
            addCriterion("short_link_address <=", value, "shortLinkAddress");
            return (Criteria) this;
        }

        public Criteria andShortLinkAddressLike(String value) {
            addCriterion("short_link_address like", value, "shortLinkAddress");
            return (Criteria) this;
        }

        public Criteria andShortLinkAddressNotLike(String value) {
            addCriterion("short_link_address not like", value, "shortLinkAddress");
            return (Criteria) this;
        }

        public Criteria andShortLinkAddressIn(List<String> values) {
            addCriterion("short_link_address in", values, "shortLinkAddress");
            return (Criteria) this;
        }

        public Criteria andShortLinkAddressNotIn(List<String> values) {
            addCriterion("short_link_address not in", values, "shortLinkAddress");
            return (Criteria) this;
        }

        public Criteria andShortLinkAddressBetween(String value1, String value2) {
            addCriterion("short_link_address between", value1, value2, "shortLinkAddress");
            return (Criteria) this;
        }

        public Criteria andShortLinkAddressNotBetween(String value1, String value2) {
            addCriterion("short_link_address not between", value1, value2, "shortLinkAddress");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNull() {
            addCriterion("(check_status='' or check_status is null)");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNotNull() {
            addCriterion("(check_status!='' or check_status is not null)");
            return (Criteria) this;
        }

        public Criteria andCheckStatusEqualTo(Byte value) {
            addCriterion("check_status =", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotEqualTo(Byte value) {
            addCriterion("check_status <>", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThan(Byte value) {
            addCriterion("check_status >", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("check_status >=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThan(Byte value) {
            addCriterion("check_status <", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThanOrEqualTo(Byte value) {
            addCriterion("check_status <=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIn(List<Byte> values) {
            addCriterion("check_status in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotIn(List<Byte> values) {
            addCriterion("check_status not in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusBetween(Byte value1, Byte value2) {
            addCriterion("check_status between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("check_status not between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andGroupNoIsNull() {
            addCriterion("(group_no='' or group_no is null)");
            return (Criteria) this;
        }

        public Criteria andGroupNoIsNotNull() {
            addCriterion("(group_no!='' or group_no is not null)");
            return (Criteria) this;
        }

        public Criteria andGroupNoEqualTo(String value) {
            addCriterion("group_no =", value, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoNotEqualTo(String value) {
            addCriterion("group_no <>", value, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoGreaterThan(String value) {
            addCriterion("group_no >", value, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoGreaterThanOrEqualTo(String value) {
            addCriterion("group_no >=", value, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoLessThan(String value) {
            addCriterion("group_no <", value, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoLessThanOrEqualTo(String value) {
            addCriterion("group_no <=", value, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoLike(String value) {
            addCriterion("group_no like", value, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoNotLike(String value) {
            addCriterion("group_no not like", value, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoIn(List<String> values) {
            addCriterion("group_no in", values, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoNotIn(List<String> values) {
            addCriterion("group_no not in", values, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoBetween(String value1, String value2) {
            addCriterion("group_no between", value1, value2, "groupNo");
            return (Criteria) this;
        }

        public Criteria andGroupNoNotBetween(String value1, String value2) {
            addCriterion("group_no not between", value1, value2, "groupNo");
            return (Criteria) this;
        }

        public Criteria andLinkNumIsNull() {
            addCriterion("(link_num='' or link_num is null)");
            return (Criteria) this;
        }

        public Criteria andLinkNumIsNotNull() {
            addCriterion("(link_num!='' or link_num is not null)");
            return (Criteria) this;
        }

        public Criteria andLinkNumEqualTo(Integer value) {
            addCriterion("link_num =", value, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumNotEqualTo(Integer value) {
            addCriterion("link_num <>", value, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumGreaterThan(Integer value) {
            addCriterion("link_num >", value, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("link_num >=", value, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumLessThan(Integer value) {
            addCriterion("link_num <", value, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumLessThanOrEqualTo(Integer value) {
            addCriterion("link_num <=", value, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumIn(List<Integer> values) {
            addCriterion("link_num in", values, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumNotIn(List<Integer> values) {
            addCriterion("link_num not in", values, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumBetween(Integer value1, Integer value2) {
            addCriterion("link_num between", value1, value2, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkNumNotBetween(Integer value1, Integer value2) {
            addCriterion("link_num not between", value1, value2, "linkNum");
            return (Criteria) this;
        }

        public Criteria andLinkTypeIsNull() {
            addCriterion("(link_type='' or link_type is null)");
            return (Criteria) this;
        }

        public Criteria andLinkTypeIsNotNull() {
            addCriterion("(link_type!='' or link_type is not null)");
            return (Criteria) this;
        }

        public Criteria andLinkTypeEqualTo(Byte value) {
            addCriterion("link_type =", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeNotEqualTo(Byte value) {
            addCriterion("link_type <>", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeGreaterThan(Byte value) {
            addCriterion("link_type >", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("link_type >=", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeLessThan(Byte value) {
            addCriterion("link_type <", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeLessThanOrEqualTo(Byte value) {
            addCriterion("link_type <=", value, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeIn(List<Byte> values) {
            addCriterion("link_type in", values, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeNotIn(List<Byte> values) {
            addCriterion("link_type not in", values, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeBetween(Byte value1, Byte value2) {
            addCriterion("link_type between", value1, value2, "linkType");
            return (Criteria) this;
        }

        public Criteria andLinkTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("link_type not between", value1, value2, "linkType");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("(source='' or source is null)");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("(source!='' or source is not null)");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(Byte value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(Byte value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(Byte value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(Byte value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(Byte value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(Byte value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<Byte> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<Byte> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(Byte value1, Byte value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(Byte value1, Byte value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("(remark='' or remark is null)");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("(remark!='' or remark is not null)");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeIsNull() {
            addCriterion("(expiration_time='' or expiration_time is null)");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeIsNotNull() {
            addCriterion("(expiration_time!='' or expiration_time is not null)");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeEqualTo(Date value) {
            addCriterion("expiration_time =", value, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeNotEqualTo(Date value) {
            addCriterion("expiration_time <>", value, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeGreaterThan(Date value) {
            addCriterion("expiration_time >", value, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expiration_time >=", value, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeLessThan(Date value) {
            addCriterion("expiration_time <", value, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeLessThanOrEqualTo(Date value) {
            addCriterion("expiration_time <=", value, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeIn(List<Date> values) {
            addCriterion("expiration_time in", values, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeNotIn(List<Date> values) {
            addCriterion("expiration_time not in", values, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeBetween(Date value1, Date value2) {
            addCriterion("expiration_time between", value1, value2, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andExpirationTimeNotBetween(Date value1, Date value2) {
            addCriterion("expiration_time not between", value1, value2, "expirationTime");
            return (Criteria) this;
        }

        public Criteria andMobileNoParamIsNull() {
            addCriterion("(mobile_no_param='' or mobile_no_param is null)");
            return (Criteria) this;
        }

        public Criteria andMobileNoParamIsNotNull() {
            addCriterion("(mobile_no_param!='' or mobile_no_param is not null)");
            return (Criteria) this;
        }

        public Criteria andMobileNoParamEqualTo(String value) {
            addCriterion("mobile_no_param =", value, "mobileNoParam");
            return (Criteria) this;
        }

        public Criteria andMobileNoParamNotEqualTo(String value) {
            addCriterion("mobile_no_param <>", value, "mobileNoParam");
            return (Criteria) this;
        }

        public Criteria andMobileNoParamGreaterThan(String value) {
            addCriterion("mobile_no_param >", value, "mobileNoParam");
            return (Criteria) this;
        }

        public Criteria andMobileNoParamGreaterThanOrEqualTo(String value) {
            addCriterion("mobile_no_param >=", value, "mobileNoParam");
            return (Criteria) this;
        }

        public Criteria andMobileNoParamLessThan(String value) {
            addCriterion("mobile_no_param <", value, "mobileNoParam");
            return (Criteria) this;
        }

        public Criteria andMobileNoParamLessThanOrEqualTo(String value) {
            addCriterion("mobile_no_param <=", value, "mobileNoParam");
            return (Criteria) this;
        }

        public Criteria andMobileNoParamLike(String value) {
            addCriterion("mobile_no_param like", value, "mobileNoParam");
            return (Criteria) this;
        }

        public Criteria andMobileNoParamNotLike(String value) {
            addCriterion("mobile_no_param not like", value, "mobileNoParam");
            return (Criteria) this;
        }

        public Criteria andMobileNoParamIn(List<String> values) {
            addCriterion("mobile_no_param in", values, "mobileNoParam");
            return (Criteria) this;
        }

        public Criteria andMobileNoParamNotIn(List<String> values) {
            addCriterion("mobile_no_param not in", values, "mobileNoParam");
            return (Criteria) this;
        }

        public Criteria andMobileNoParamBetween(String value1, String value2) {
            addCriterion("mobile_no_param between", value1, value2, "mobileNoParam");
            return (Criteria) this;
        }

        public Criteria andMobileNoParamNotBetween(String value1, String value2) {
            addCriterion("mobile_no_param not between", value1, value2, "mobileNoParam");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("(status='' or status is null)");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("(status!='' or status is not null)");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("(update_time='' or update_time is null)");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("(update_time!='' or update_time is not null)");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andLinkNoLikeInsensitive(String value) {
            addCriterion("upper(link_no) like", value.toUpperCase(), "linkNo");
            return (Criteria) this;
        }

        public Criteria andLinkNameLikeInsensitive(String value) {
            addCriterion("upper(link_name) like", value.toUpperCase(), "linkName");
            return (Criteria) this;
        }

        public Criteria andPlatformNoLikeInsensitive(String value) {
            addCriterion("upper(platform_no) like", value.toUpperCase(), "platformNo");
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

        public Criteria andAddressLikeInsensitive(String value) {
            addCriterion("upper(address) like", value.toUpperCase(), "address");
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

        public Criteria andShortLinkAddressLikeInsensitive(String value) {
            addCriterion("upper(short_link_address) like", value.toUpperCase(), "shortLinkAddress");
            return (Criteria) this;
        }

        public Criteria andGroupNoLikeInsensitive(String value) {
            addCriterion("upper(group_no) like", value.toUpperCase(), "groupNo");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(remark) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }

        public Criteria andMobileNoParamLikeInsensitive(String value) {
            addCriterion("upper(mobile_no_param) like", value.toUpperCase(), "mobileNoParam");
            return (Criteria) this;
        }

        public Criteria andTableTimeLikeInsensitive(String value) {
            addCriterion("upper(table_time) like", value.toUpperCase(), "tableTime");
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