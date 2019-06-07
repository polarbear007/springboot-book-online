package cn.itcast.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ChapterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChapterExample() {
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

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
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
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andChapterIdIsNull() {
            addCriterion("chapter_id is null");
            return (Criteria) this;
        }

        public Criteria andChapterIdIsNotNull() {
            addCriterion("chapter_id is not null");
            return (Criteria) this;
        }

        public Criteria andChapterIdEqualTo(Integer value) {
            addCriterion("chapter_id =", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdNotEqualTo(Integer value) {
            addCriterion("chapter_id <>", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdGreaterThan(Integer value) {
            addCriterion("chapter_id >", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("chapter_id >=", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdLessThan(Integer value) {
            addCriterion("chapter_id <", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdLessThanOrEqualTo(Integer value) {
            addCriterion("chapter_id <=", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdIn(List<Integer> values) {
            addCriterion("chapter_id in", values, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdNotIn(List<Integer> values) {
            addCriterion("chapter_id not in", values, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdBetween(Integer value1, Integer value2) {
            addCriterion("chapter_id between", value1, value2, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdNotBetween(Integer value1, Integer value2) {
            addCriterion("chapter_id not between", value1, value2, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterNumIsNull() {
            addCriterion("chapter_num is null");
            return (Criteria) this;
        }

        public Criteria andChapterNumIsNotNull() {
            addCriterion("chapter_num is not null");
            return (Criteria) this;
        }

        public Criteria andChapterNumEqualTo(Integer value) {
            addCriterion("chapter_num =", value, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumNotEqualTo(Integer value) {
            addCriterion("chapter_num <>", value, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumGreaterThan(Integer value) {
            addCriterion("chapter_num >", value, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("chapter_num >=", value, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumLessThan(Integer value) {
            addCriterion("chapter_num <", value, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumLessThanOrEqualTo(Integer value) {
            addCriterion("chapter_num <=", value, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumIn(List<Integer> values) {
            addCriterion("chapter_num in", values, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumNotIn(List<Integer> values) {
            addCriterion("chapter_num not in", values, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumBetween(Integer value1, Integer value2) {
            addCriterion("chapter_num between", value1, value2, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterNumNotBetween(Integer value1, Integer value2) {
            addCriterion("chapter_num not between", value1, value2, "chapterNum");
            return (Criteria) this;
        }

        public Criteria andChapterTitleIsNull() {
            addCriterion("chapter_title is null");
            return (Criteria) this;
        }

        public Criteria andChapterTitleIsNotNull() {
            addCriterion("chapter_title is not null");
            return (Criteria) this;
        }

        public Criteria andChapterTitleEqualTo(String value) {
            addCriterion("chapter_title =", value, "chapterTitle");
            return (Criteria) this;
        }

        public Criteria andChapterTitleNotEqualTo(String value) {
            addCriterion("chapter_title <>", value, "chapterTitle");
            return (Criteria) this;
        }

        public Criteria andChapterTitleGreaterThan(String value) {
            addCriterion("chapter_title >", value, "chapterTitle");
            return (Criteria) this;
        }

        public Criteria andChapterTitleGreaterThanOrEqualTo(String value) {
            addCriterion("chapter_title >=", value, "chapterTitle");
            return (Criteria) this;
        }

        public Criteria andChapterTitleLessThan(String value) {
            addCriterion("chapter_title <", value, "chapterTitle");
            return (Criteria) this;
        }

        public Criteria andChapterTitleLessThanOrEqualTo(String value) {
            addCriterion("chapter_title <=", value, "chapterTitle");
            return (Criteria) this;
        }

        public Criteria andChapterTitleLike(String value) {
            addCriterion("chapter_title like", value, "chapterTitle");
            return (Criteria) this;
        }

        public Criteria andChapterTitleNotLike(String value) {
            addCriterion("chapter_title not like", value, "chapterTitle");
            return (Criteria) this;
        }

        public Criteria andChapterTitleIn(List<String> values) {
            addCriterion("chapter_title in", values, "chapterTitle");
            return (Criteria) this;
        }

        public Criteria andChapterTitleNotIn(List<String> values) {
            addCriterion("chapter_title not in", values, "chapterTitle");
            return (Criteria) this;
        }

        public Criteria andChapterTitleBetween(String value1, String value2) {
            addCriterion("chapter_title between", value1, value2, "chapterTitle");
            return (Criteria) this;
        }

        public Criteria andChapterTitleNotBetween(String value1, String value2) {
            addCriterion("chapter_title not between", value1, value2, "chapterTitle");
            return (Criteria) this;
        }

        public Criteria andChapterLengthIsNull() {
            addCriterion("chapter_length is null");
            return (Criteria) this;
        }

        public Criteria andChapterLengthIsNotNull() {
            addCriterion("chapter_length is not null");
            return (Criteria) this;
        }

        public Criteria andChapterLengthEqualTo(Integer value) {
            addCriterion("chapter_length =", value, "chapterLength");
            return (Criteria) this;
        }

        public Criteria andChapterLengthNotEqualTo(Integer value) {
            addCriterion("chapter_length <>", value, "chapterLength");
            return (Criteria) this;
        }

        public Criteria andChapterLengthGreaterThan(Integer value) {
            addCriterion("chapter_length >", value, "chapterLength");
            return (Criteria) this;
        }

        public Criteria andChapterLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("chapter_length >=", value, "chapterLength");
            return (Criteria) this;
        }

        public Criteria andChapterLengthLessThan(Integer value) {
            addCriterion("chapter_length <", value, "chapterLength");
            return (Criteria) this;
        }

        public Criteria andChapterLengthLessThanOrEqualTo(Integer value) {
            addCriterion("chapter_length <=", value, "chapterLength");
            return (Criteria) this;
        }

        public Criteria andChapterLengthIn(List<Integer> values) {
            addCriterion("chapter_length in", values, "chapterLength");
            return (Criteria) this;
        }

        public Criteria andChapterLengthNotIn(List<Integer> values) {
            addCriterion("chapter_length not in", values, "chapterLength");
            return (Criteria) this;
        }

        public Criteria andChapterLengthBetween(Integer value1, Integer value2) {
            addCriterion("chapter_length between", value1, value2, "chapterLength");
            return (Criteria) this;
        }

        public Criteria andChapterLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("chapter_length not between", value1, value2, "chapterLength");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNull() {
            addCriterion("last_update_time is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNotNull() {
            addCriterion("last_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("last_update_time =", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("last_update_time <>", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("last_update_time >", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("last_update_time >=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThan(Date value) {
            addCriterionForJDBCDate("last_update_time <", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("last_update_time <=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("last_update_time in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("last_update_time not in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("last_update_time between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("last_update_time not between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNull() {
            addCriterion("book_id is null");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNotNull() {
            addCriterion("book_id is not null");
            return (Criteria) this;
        }

        public Criteria andBookIdEqualTo(Integer value) {
            addCriterion("book_id =", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotEqualTo(Integer value) {
            addCriterion("book_id <>", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThan(Integer value) {
            addCriterion("book_id >", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("book_id >=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThan(Integer value) {
            addCriterion("book_id <", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThanOrEqualTo(Integer value) {
            addCriterion("book_id <=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdIn(List<Integer> values) {
            addCriterion("book_id in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotIn(List<Integer> values) {
            addCriterion("book_id not in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdBetween(Integer value1, Integer value2) {
            addCriterion("book_id between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotBetween(Integer value1, Integer value2) {
            addCriterion("book_id not between", value1, value2, "bookId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

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