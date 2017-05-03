package com.example.xiao.myappdemobaidu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名：MyAppDemoBaidu
 * 包名：com.example.xiao.myappdemobaidu.entity
 * 文件名：MyBeanEntibity
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/4/26 0026 下午 5:38
 * 描述：TODO
 */
public class MyBeanEntibity implements Serializable {

    /**
     * status : 1
     * msg : [{"m_id":"12","m_time":"2147483647","m_code":"111111111111111"},{"m_id":"11","m_time":"2147483647","m_code":"111111111111111"},{"m_id":"10","m_time":"1490161370","m_code":"Lambert"},{"m_id":"9","m_time":"1490161370","m_code":"1231231"},{"m_id":"8","m_time":"1490151370","m_code":""}]
     */

    private int status;
    private List<MsgBean> msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public static class MsgBean implements Serializable {
        /**
         * m_id : 12
         * m_time : 2147483647
         * m_code : 111111111111111
         */

        private String m_id;
        private String m_time;
        private String m_code;

        public String getM_id() {
            return m_id;
        }

        public void setM_id(String m_id) {
            this.m_id = m_id;
        }

        public String getM_time() {
            return m_time;
        }

        public void setM_time(String m_time) {
            this.m_time = m_time;
        }

        public String getM_code() {
            return m_code;
        }

        public void setM_code(String m_code) {
            this.m_code = m_code;
        }
    }
}
