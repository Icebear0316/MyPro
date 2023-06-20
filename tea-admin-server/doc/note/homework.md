**第1次作业（2023.06.13）**

- 创建`content_`系列数据表对应的实体类
  - 各实体类均需要配置`@TableName`、`@TableId`、`@TableField`
- 创建`content_`系列数据表对应的标准VO类和列表项VO类
- 创建`content_`系列数据表对应的Mapper接口，均继承自`BaseMapper`，泛型为对应的实体类型
- 创建各Mapper接口对应的XML文件
- 实现每种数据的“根据ID查询详情”（返回值类型为标准VO类型）功能
- 创建各Mapper接口对应的测试类，并在测试类中测试以上“根据ID查询详情”功能

提交时间：明天早上9点之前。



**第2次作业（2023.06.14）**

- 创建`content_`系列数据表的Repository接口与实现类
- 在各Repository中均实现“添加数据”的功能
- 对各Repository的“添加数据”功能进行测试
- 创建`content_category`、`content_article`、`content_comment`对应的Service接口与实现类
- 在各Service中均实现“添加数据”的功能，暂时不需要设计业务规则
- 对各Service的“添加数据”功能进行测试

提交时间：明天早上9点之前。



**第3次作业（2023.06.15）**

- 创建VUE脚本架项目，项目名称参考：`tea-admin-client`
- 在项目中安装Element UI、axios，并配置
- 修改项目的端口号为：`9000`
- 设计登录页，访问URL为：`/login`
  - 页面内容可参考此前的VUE脚手架案例
- 设计后台管理页框架，是上下结构的，下半部分的内部是左右结构的
  - 顶栏中添加标题文字：`学茶商城后台管理平台`
  - 左侧边栏添加菜单，菜单项暂不要求
  - 右侧主体使用`<router-view/>`
- 创建“新增标签类别”页面，将显示在以上框架页的`<router-view/>`，访问URL为：`/admin/content/tag/type/add-new`
  - 页面内容暂不要求

提交时间：明天早上9点之前。

注意：提交时，删除压缩包中的`node_modules`文件夹。

**第4次作业（2023.06.17）**

- 实现“添加标签”功能
  - DAO层无需开发新功能
  - Service层添加`void addNew(TagAddNewParam tagAddNewParam)`方法，并实现，业务规则：名称必须唯一
    - 相对标签类别，标签数据需要增加typeId（对应数据表中的parent_id）属性，表示此标签归属哪个标签类别
  - Controller层处理此请求，URL设计为`/content/tags/add-new`
  - 各层均需要测试
  - 前端页面不要求完成
- 实现“显示标签列表”功能
  - DAO层中：
    - Mapper实现`List<TagListItemVO> list()`查询
    - Repository实现`PageData<TagListItemVO> list(Integer pageNum, Integer pageSize)`查询
  - Service层添加方法并实现：
    - `PageData<TagListItemVO> list(Integer pageNum)`
    - `PageData<TagListItemVO> list(Integer pageNum, Integer pageSize)`
    - 以上方法均无业务规则
  - Controller层处理此请求，URL设计为`/content/tags`
    - 在方法上配置为`@GetMapping("")`
  - 各层均需要测试
  - 要求完成前端页面的数据显示，各管理功能（例如编辑、删除等）可不实现

提交时间：下周一早上9点之前。
