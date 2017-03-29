package com.qhtr.model;

import java.util.Date;

public class SystemLog {
    private Long id;

    private String name;

    private Integer operationPeopleId;

    private Integer type;

    private Date createTime;

    private String operation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getOperationPeopleId() {
        return operationPeopleId;
    }

    public void setOperationPeopleId(Integer operationPeopleId) {
        this.operationPeopleId = operationPeopleId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }
}