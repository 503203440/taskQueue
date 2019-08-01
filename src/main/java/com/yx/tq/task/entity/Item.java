package com.yx.tq.task.entity;


import lombok.Data;

import java.util.Date;
import java.util.Objects;

//@Data
public class Item {


    private String name;
    private Date startTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    /**
     * 重写对象的equals方法
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                Objects.equals(startTime, item.startTime);
    }

    /**
     * 重写对象的hashcode方法
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, startTime);
    }


    /**
     *
     * 这里对与为什么重写equals一定要重写hashcode做一个说明
     *
     * 其实都是关于hashcode的规定
     * equals() 与 hashCode() 使用契约
     * If two objects are equal according to the equals(Object) method,
     * then calling the hashcode() method on each of the two objects must produce the same integer result
     * 上面的话翻译成程序语言即是：
     * if obj1.equal(obj2) then:
     *     obj1.hashCode() == obj2.hashCode()
     *
     * 在实际应用当中，JDK提供的默认实现可能无法满足实际业务场景，这时，我们就需要根据业务场景来重载hashCode和equals方法，
     * 但需谨记：当我们重载一个对象的equals方法，就必须重载他的hashCode方法，
     * 如果我们仅仅重载equals但没有重载hashcode，实际应用可能会带来潜在问题
     * 比如hashset中，hashset的contains方法就是那参数的hashcode查看是否在hashset的内存中是否存在，
     * 而不是调用的equals，那么此时我们业务上认为存在的在hashset中就会被判定为不存在，所以实际开发中
     * 重写了equals一定要重写hashcode，那么怎么重写hashCode呢，比如我们的equals中只比较了name字段，
     * 只要name相同则视为相等，那么hashcode方法中就使用name字段作为参数调用Objects的hash方法得到哈希值
     * 这样就能保证计算hash值的字段一样，如果不重写，则是使用native的方法对对象进行哈希计算，那么肯定得到了不同的hash值
     * 而如果手动调用需要进行hash计算的字段，这样每个字段的值进行hash对比就能保证每个需要对比的hashcode得到的结果一致了
     */









}
