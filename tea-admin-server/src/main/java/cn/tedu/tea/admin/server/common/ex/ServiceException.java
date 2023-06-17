package cn.tedu.tea.admin.server.common.ex;

import cn.tedu.tea.admin.server.common.web.ServiceCode;
import lombok.Getter;

/**
 * 业务异常
 *
 *
 */
public class ServiceException extends RuntimeException {

    @Getter
    private ServiceCode serviceCode;

    public ServiceException(ServiceCode serviceCode, String message) {
        super(message);
        this.serviceCode = serviceCode;
    }

}
