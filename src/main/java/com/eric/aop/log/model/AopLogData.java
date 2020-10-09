package com.eric.aop.log.model;

import java.util.Date;

/**
 * @author shiyu.du
 * @describe
 * @date 2020/9/21
 **/
public class AopLogData {

    /**
     * 请务必注意该对象 使用->释放 原则
     */
    private static final ThreadLocal<AopLogData> LOG_DATA = new ThreadLocal<>();
    /**应用名称*/
    private String appName;
    /**主机*/
    private String host;
    /**端口号*/
    private Integer port;
    /**请求客户端的Ip*/
    private String clintIp;
    /**	请求地址*/
    private String reqUrl;
    /**请求头部信息(可选择记录) 默认记录user-agent,content-type*/
    private Object headers;
    /**操作类型,默认值undefined*/
    private String type;
    /**方法步骤内容,默认是空,可使用LogData.step进行内容步骤记录*/
    private String content;
    /**请求的本地java方法*/
    private String method;
    /**方法请求参数*/
    private Object args;
    /**方法响应参数*/
    private Object respBody;
    /**整个方法耗时*/
    private Long costTime;
    /**Log产生时间,LogData对象初始化的时间*/
    private Date logDate;
    /**线程名称*/
    private String threadName = Thread.currentThread().getName();
    /**线程Id*/
    private Long threadId = Thread.currentThread().getId();
    /**执行状态,成功(true)/异常(false)*/
    private Boolean success = false;

    /**
     * 耗时计算
     */
    public void toCostTime() {
        AopLogData data = AopLogData.getCurrent();
        data.setCostTime((System.currentTimeMillis() - logDate.getTime()));
        AopLogData.setCurrent(data);
    }

    /**
     * 获取当前线程中的操作日志对象
     */
    public static AopLogData getCurrent() {
        AopLogData data = LOG_DATA.get();
        if (data == null) {
            data = new AopLogData();
            data.setLogDate(new Date());
            LOG_DATA.set(data);
        }
        return LOG_DATA.get();
    }

    public static void setCurrent(AopLogData data) {
        LOG_DATA.set(data);
    }

    /**
     * 移除当前线程操作日志对象
     */
    public static void removeCurrent() {
        LOG_DATA.remove();
    }

    /**
     * 内容记录记录 正常会在aop中结束释放
     *
     * @param step 这里可以使用 该方法记录每一个步骤 : 注意 调用该方法时 请注意释放 ; 不用此对象时，请 调用 移除当前线程操作日志对象
     */
    public static void step(String step) {
        AopLogData data = getCurrent();
        if (data.getContent() == null) data.setContent("");
        data.setContent(data.getContent() + step + "\n");
        setCurrent(data);
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getClintIp() {
        return clintIp;
    }

    public void setClintIp(String clintIp) {
        this.clintIp = clintIp;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public Object getHeaders() {
        return headers;
    }

    public void setHeaders(Object headers) {
        this.headers = headers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getArgs() {
        return args;
    }

    public void setArgs(Object args) {
        this.args = args;
    }

    public Object getRespBody() {
        return respBody;
    }

    public void setRespBody(Object respBody) {
        this.respBody = respBody;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
