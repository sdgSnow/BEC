package com.sdg.bec.db;

import org.litepal.crud.LitePalSupport;

public class Sentence extends LitePalSupport {

    private String wID;//单词id
    private String sID;//橘子id
    private String aID;//文章id
    private String cnName;//中文解释
    private String enName;//英文解释
    private int classify;//分类
    private String createTime;//创建时间
    private int important;//重要程度

    private int status;//单词当前状态 0 新增；1 熟悉；2熟记
    private boolean isCollect;//是否收藏
    private int sort;//排序

    public Sentence() {
    }

    public Sentence(String sID, int important, String cnName, String enName, int classify, String createTime, int status) {
        this.sID = sID;
        this.important = important;
        this.cnName = cnName;
        this.enName = enName;
        this.classify = classify;
        this.createTime = createTime;
        this.status = status;
    }

    public String getwID() {
        return wID;
    }

    public void setwID(String wID) {
        this.wID = wID;
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getaID() {
        return aID;
    }

    public void setaID(String aID) {
        this.aID = aID;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getImportant() {
        return important;
    }

    public void setImportant(int important) {
        this.important = important;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
