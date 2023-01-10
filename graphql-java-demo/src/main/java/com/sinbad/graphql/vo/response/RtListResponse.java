package com.sinbad.graphql.vo.response;

import com.sinbad.graphql.enums.ErrorEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 通用的response
 *
 * @author sinbad on 2023/1/10 - 11:46 PM.
 **/
@Data
public class RtListResponse<T> implements Serializable {

    private int code;

    private String message;

    private long total;

    /**
     * 是否有下一页
     */
    private boolean hasMore;

    private List<T> dataList;


    public RtListResponse() {

    }

    public RtListResponse(List<T> dataList) {
        this.code = ErrorEnum.SUCCESS.getCode();
        this.message = ErrorEnum.SUCCESS.getDesc();
        this.dataList = dataList;
    }

    public RtListResponse(int code, String codeMsg, List<T> dataList) {
        this.code = code;
        this.message = codeMsg;
        this.dataList = dataList;
    }

    public static <T> RtListResponse<T> success(List<T> resultList) {
        RtListResponse<T> response = new RtListResponse<>();
        response.setCode(ErrorEnum.SUCCESS.getCode());
        response.setMessage(ErrorEnum.SUCCESS.getDesc());
        response.setDataList(resultList);
        return response;
    }

    public void setError() {
        this.setMessage(ErrorEnum.ERROR.getDesc());
        this.setCode(ErrorEnum.ERROR.getCode());
    }

    public void setParamError() {
        this.setMessage(ErrorEnum.ERROR_PARAM.getDesc());
        this.setCode(ErrorEnum.ERROR_PARAM.getCode());
    }
}
