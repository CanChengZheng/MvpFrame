package com.zcc.android.mvpframe.data;

/**
 * @author ZCC
 * @date 2018/1/2
 * @description
 */

public class ResponseResult {


    /**
     * successMessageCode : 1
     * successMessage : 登录成功
     * response : {"UserID":44,"UserName":"linzhiwei","NickName":"林志威","Email":"linzhiwei@gmail.com","Avatar":"http://www.jenkon.vip","SiteId":1,"DepartmentId":169,"CreatedAt":"0001-01-01T00:00:00"}
     */

    private int successMessageCode;
    private String successMessage;
    private String response;

    public int getSuccessMessageCode() {
        return successMessageCode;
    }

    public void setSuccessMessageCode(int successMessageCode) {
        this.successMessageCode = successMessageCode;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public static class String {
    }
}
