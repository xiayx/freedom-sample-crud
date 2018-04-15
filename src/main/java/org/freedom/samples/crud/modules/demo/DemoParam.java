package org.freedom.samples.crud.modules.demo;

import java.util.Date;

/**
 * 示例查询参数
 *
 * @author xiayx
 */
public class DemoParam {

    private String name;
    private Date beginCreatedTime;
    private Date endCreatedTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginCreatedTime() {
        return beginCreatedTime;
    }

    public void setBeginCreatedTime(Date beginCreatedTime) {
        this.beginCreatedTime = beginCreatedTime;
    }

    public Date getEndCreatedTime() {
        return endCreatedTime;
    }

    public void setEndCreatedTime(Date endCreatedTime) {
        this.endCreatedTime = endCreatedTime;
    }
}
