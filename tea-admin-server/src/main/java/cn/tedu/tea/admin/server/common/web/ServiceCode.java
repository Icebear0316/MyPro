package cn.tedu.tea.admin.server.common.web;

/**
 * 业务状态码
 *
 * @author java@tedu.cn
 * @version 1.0
 */
public enum ServiceCode {

    /**
     * 操作成功
     */
    OK(20000),
    /**
     * 数据不存在
     */
    ERROR_NOT_FOUND(40400),
    /**
     * 数据冲突
     */
    ERROR_CONFLICT(40900)
    ;

    private Integer value;

    ServiceCode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
