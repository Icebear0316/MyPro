package cn.tedu.tea.admin.server.common.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author java@tedu.cn
 * @version 1.0
 */
@Data
public class PageData<T> implements Serializable {

    private List<T> list;
    private Long total;

}
