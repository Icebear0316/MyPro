package cn.tedu.tea.admin.server.core.ex.handler;

import cn.tedu.tea.admin.server.common.ex.ServiceException;
import cn.tedu.tea.admin.server.common.web.JsonResult;
import cn.tedu.tea.admin.server.common.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {
        log.info("创建全局异常处理器对象：GlobalExceptionHandler");
    }

    @ExceptionHandler
    public JsonResult handleServiceException(ServiceException e) {
        log.debug("全局异常处理器开始处理ServiceException");
        return JsonResult.fail(e);
    }

    @ExceptionHandler
    public JsonResult handleBindException(BindException e) {
        log.debug("全局异常处理器开始处理BindException");
        // 以下代码将提示所有格式错误的文本
        // StringJoiner stringJoiner = new StringJoiner("，", "请求参数格式错误，", "！");
        // List<FieldError> fieldErrors = e.getFieldErrors();
        // for (FieldError fieldError : fieldErrors) {
        //     String defaultMessage = fieldError.getDefaultMessage();
        //     stringJoiner.add(defaultMessage);
        // }
        // String message = stringJoiner.toString();
        // log.warn(message);
        // return JsonResult.fail(ServiceCode.ERROR_BAD_REQUEST, message);
        // 以下代码将提示随机的某个格式错误的文本
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("请求参数格式错误，");
        stringBuilder.append(e.getFieldError().getDefaultMessage());
        stringBuilder.append("！");
        String message = stringBuilder.toString();
        log.warn(message);
        return JsonResult.fail(ServiceCode.ERROR_BAD_REQUEST, message);
    }

    @ExceptionHandler
    public JsonResult handleThrowable(Throwable e) {
        log.debug("全局异常处理器开始处理Throwable");
        log.debug("异常跟踪信息如下：", e);
        String message = "服务器忙，请稍后再试！【同学们，看到这句时，你应该检查服务器端的控制台，并在全局异常处理器中添加处理异常的方法】";
        return JsonResult.fail(ServiceCode.ERROR_UNKNOWN, message);
    }

}
