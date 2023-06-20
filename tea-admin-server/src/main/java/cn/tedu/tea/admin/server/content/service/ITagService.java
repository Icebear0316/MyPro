package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.content.pojo.param.TagAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.param.TagTypeAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.param.TagUpdateInfoParam;
import cn.tedu.tea.admin.server.content.pojo.vo.TagListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.TagStandardVO;
import cn.tedu.tea.admin.server.content.pojo.vo.TagTypeListItemVO;

/**
 * 处理标签数据的业务接口
 *
 */
public interface ITagService {

    /**
     * 启用状态的显示文本
     */
    String ENABLE_TEXT[] = {"禁用" , "启用"};

    /**
     * 新增标签类型
     *
     * @param tagTypeAddNewParam 新增的标签类型数据
     */
    void addNew(TagTypeAddNewParam tagTypeAddNewParam);

    /**
     * 新增标签
     *
     * @param tagAddNewParam 新增的标签数据
     */
    void addNew(TagAddNewParam tagAddNewParam);

    /**
     * 删除标签
     *
     * @param id 尝试删除的标签的ID
     */
    void delete(Long id);

    /**
     * 修改标签
     *
     * @param tagUpdateInfoParam 封装了被修改数据的ID和新数据的对象
     */
    void updateInfoById(TagUpdateInfoParam tagUpdateInfoParam);

    /**
     * 启用标签
     *
     * @param id 标签ID
     */
    void setEnable(Long id);

    /**
     * 禁用标签
     *
     * @param id 标签ID
     */
    void setDisable(Long id);

    /**
     * 根据ID查询标签
     *
     * @param id 标签ID
     * @return 匹配的标签，如果没有匹配的数据，则返回null
     */
    TagStandardVO getStandardById(Long id);

    /**
     * 查询标签类别列表，将使用默认的“每页记录数”
     *
     * @param pageNum 页码
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

    /**
     * 查询标签列表，将使用默认的每页记录数
     *
     * @param pageNum 页码
     * @return 查询标签列表
     */
    PageData<TagListItemVO> list(Integer pageNum);

    /**
     * 查询标签列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 标签列表
     */
    PageData<TagListItemVO> list(Integer pageNum, Integer pageSize);

}
