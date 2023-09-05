### 熊博士智能BI平台：

基于 Spring Boot + MQ + AIGC+ React 的智能数据分析平台。区别于传统 BI，用户只需要导入原始数据集、并输入分析诉求，就能自动生成可视化图表及分析结论，实现数据分析的降本增效



### 技术选型

#### 前端

1. React 18
2. Ant Design Pro 5.x 脚手架
3. Umi 4 前端框架
4. Ant Design 组件库
5. Echarts 可视化库
6. OpenAPI 前端代码生成

#### 后端

1. Java Spring Boot（万用后端模板）
2. MySQL 数据库
3. MyBatis-Plus 及 MyBatis X 自动生成
4. Redis + Redisson 限流
5. RabbitMQ 消息队列
6. 鱼聪明 AI SDK（AI 能力）
7. JDK 线程池及异步化
8. Easy Excel 表格数据处理
9. Swagger + Knife4j 接口文档生成
10. Hutool、Apache Common Utils 等工具库

### 主要工作：

1. 后端自定义 Prompt 预设模板并封装用户输入的数据和分析诉求，通过对接 AIGC 接口生成可视化图表 json 配置和分析结论，返回给前端渲染。
2. 由于 AIGC 的输入 Token 限制，使用 Easy Excel 解析用户上传的 XLSX 表格数据文件并压缩为 CSV，实测提高了 20% 的单次输入数据量、并节约了成本。
3. 为保证系统的安全性，对用户上传的原始数据文件进行了后缀名、大小、内容等多重校验
4. 为防止某用户恶意占用系统资源，基于 Redisson 的 RateLimiter 实现分布式限流，控制单用户访问的频率。
5. 考虑到单个图表的原始数据量较大，基于 MyBatis + 业务层构建自定义 SQL 实现了对每份原始数据的分表存储，提高查询性能 50 %
6. 由于 AIGC 的响应时间较长，基于自定义 IO 密集型线程池+ 任务队列实现了 AIGC 的并发执行和异步化，提交任务后即可响应前端，提高用户体验。
7. 7由于本地任务队列重启丢失数据，使用 RabbitMQ（分布式消息队列）来接受并持久化任务消息，通过 Direct 交换机转发给解耦的 AI 生成模块消费并处理任务，提高了系统的可靠性。
8. 基于自己二次开发的 Spring Boot 初始化模板 + MyBatis X 插件，快速生成图表、用户数据的增删改查
9. 使用 Knife4j + Swagger 自动生成后端接口文档，并通过编写 ApiOperation 等注解补充接口注释，避免了人工编写维护文档的麻烦。

### 项目截图：

<img src="https://img-blog.csdnimg.cn/193d92541ae849ea8fb095d98b60eb9b.png#pic_center" style="zoom:50%;" />



<img src="https://img-blog.csdnimg.cn/f634e4e98b5649b381069563b2d4268b.png#pic_center" style="zoom:50%;" />

<img src="https://img-blog.csdnimg.cn/db67aff4b3184b2681cdd0fea6972deb.png#pic_center" style="zoom:50%;" />



<img src="https://img-blog.csdnimg.cn/78d7915b258e4358b78c487b44f84787.png#pic_center" style="zoom:50%;" />

<img src="https://img-blog.csdnimg.cn/406c1532c6024e1b9b8e82eff5681990.png#pic_center" style="zoom:50%;" />
