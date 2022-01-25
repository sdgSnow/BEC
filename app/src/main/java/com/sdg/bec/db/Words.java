package com.sdg.bec.db;

import org.litepal.crud.LitePalSupport;

public class Words extends LitePalSupport {

    private String wID;//单词id
    private String pID;//短语id
    private String sID;//橘子id
    private String aID;//文章id
    private String cnName;//中文解释
    private String enName;//英文解释
    private int classify;//分类
    private String createTime;//创建时间
    private int important;//重要程度
    private String type;//单词类型（动名词形容词副词等）
    private String phraseCnName;//短语
    private String phraseEnName;//短语
    private String phraseSentenceCnName;//短语句子
    private String phraseSentenceEnName;//短语句子

    private String PhoneticSymbols;//音标
    private int status;//单词当前状态 0 新增；1 熟悉；2熟记
    private boolean isCollect;//是否收藏
    private int sort;//排序

    public Words() {
    }

    public Words(String wID, int important,String type, String cnName, String enName, int classify, String createTime, int status) {
        this.wID = wID;
        this.important = important;
        this.type = type;
        this.cnName = cnName;
        this.enName = enName;
        this.classify = classify;
        this.createTime = createTime;
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getPhoneticSymbols() {
        return PhoneticSymbols;
    }

    public void setPhoneticSymbols(String phoneticSymbols) {
        PhoneticSymbols = phoneticSymbols;
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

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getPhraseCnName() {
        return phraseCnName;
    }

    public void setPhraseCnName(String phraseCnName) {
        this.phraseCnName = phraseCnName;
    }

    public String getPhraseEnName() {
        return phraseEnName;
    }

    public void setPhraseEnName(String phraseEnName) {
        this.phraseEnName = phraseEnName;
    }

    public String getPhraseSentenceCnName() {
        return phraseSentenceCnName;
    }

    public void setPhraseSentenceCnName(String phraseSentenceCnName) {
        this.phraseSentenceCnName = phraseSentenceCnName;
    }

    public String getPhraseSentenceEnName() {
        return phraseSentenceEnName;
    }

    public void setPhraseSentenceEnName(String phraseSentenceEnName) {
        this.phraseSentenceEnName = phraseSentenceEnName;
    }
}
