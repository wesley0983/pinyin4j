package com.example.pinyin4j;

import java.util.List;

public class Manager {
    private String apiId;
    private String apiName;
    private String apiNameOrder;
    private List<Manager>  managerList;

    public Manager() {
    }

    public Manager(String apiId, String apiName) {
        this.apiId = apiId;
        this.apiName = apiName;
    }

    public Manager(String apiId, String apiName, String apiNameOrder) {
        this.apiId = apiId;
        this.apiName = apiName;
        this.apiNameOrder = apiNameOrder;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiNameOrder() {
        return apiNameOrder;
    }

    public void setApiNameOrder(String apiNameOrder) {
        this.apiNameOrder = apiNameOrder;
    }

    public List<Manager> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<Manager> managerList) {
        this.managerList = managerList;
    }

    public String test(){
        return this.getApiNameOrder().substring(0,1);
    }
}
