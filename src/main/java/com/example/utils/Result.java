package com.example.utils;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Result
 * @Description:结果封装
 * @Author: Richerlv
 * @Date: 2022/9/13 8:46
 */

@Data
@NoArgsConstructor
public class Result {
    private int code;
    private String msg;
    private Object data;

    public static Result SUCCESS(Object data){
        return SUCCESS(200,"操作成功",data);
    }

    public static Result SUCCESS(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

    public static Result SUCCESS(String msg, Object data) {
        Result r = new Result();
        r.setCode(200);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

    public static Result Fail(String msg){
        return Fail(500,msg,null);
    }

    public static Result Fail(int code, String msg, Object data) {
        Result r = new Result();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

    public static Result Fail(String msg, Object data) {
        Result r = new Result();
        r.setCode(500);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

    public static Result Fail(int code, String msg) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(null);
        return r;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
