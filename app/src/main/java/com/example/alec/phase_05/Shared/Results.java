package com.example.alec.phase_05.Shared;

public class Results {

    private boolean success;
    private String data;
    private String errorInfo;

    public Results(){

    }

    public Results(boolean _success, String _data, String _errorInfo){

        success = new Boolean(_success);
        data = new String(_data);
        errorInfo = new String(_errorInfo);

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

}
