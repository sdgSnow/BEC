package com.sdg.bec.network;

import java.util.List;

public class TranslateResult {

    /**
     * from : zh
     * to : en
     * trans_result : [{"src":"早上","dst":"morning"}]
     */

    private String from;
    private String to;
    private List<TransResultBean> trans_result;
    private int error_code;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<TransResultBean> getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(List<TransResultBean> trans_result) {
        this.trans_result = trans_result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class TransResultBean {
        /**
         * src : 早上
         * dst : morning
         */

        private String src;
        private String dst;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }
    }
}
