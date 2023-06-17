package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.content.pojo.param.TagTypeAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.vo.TagTypeListItemVO;

/**
 * 处理标签数据的业务接口
 *
 */
public interface ITagService {

    /**
     * 新增标签类型
     *
     * @param tagTypeAddNewParam 新增的标签类型数据
     */
    void addNew(TagTypeAddNewParam tagTypeAddNewParam);

    // 新增标签
    // void addNew(TagAddNewParam tagAddNewParam);
    // TagAddNewParam: name, parent_id, enable, sort

    /**
     * 查询标签类别列表，将使用默认的“每页记录数”
     *
     * @param pageNum  页码
     * @return 标签类别列表的分页数据
     */
    PageData<TagTypeListItemVO> listTagType(Integer pageNum);

    /**
     * 查询标签类别列表
     *
     * @param pageNum  页码
     * @param pageSize 每条记录数
     * @return 标签类别列表的分页数据
     */
    PageData<TagTypeListItemVO> listTagType(Integer pageNum, Integer pageSize);

}
