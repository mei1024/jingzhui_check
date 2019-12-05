
drop table if exists `cms_category`;
CREATE TABLE `cms_category` (
  `id` varchar(64) NOT NULL COMMENT '分类ID',
  `site_id` varchar(64) DEFAULT NULL COMMENT '站点ID',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '父分类ID',
  `type_id` varchar(64) DEFAULT NULL COMMENT '分类类型',
  `child_ids` text COMMENT '所有子分类ID',
  `tag_type_ids` text COMMENT '标签分类',
  `code` varchar(50) DEFAULT NULL COMMENT '编码',
  `template_path` varchar(255) DEFAULT NULL COMMENT '模板路径',
  `path` varchar(2000) DEFAULT NULL COMMENT '首页路径',
  `only_url` char(1) DEFAULT NULL COMMENT '外链',
  `has_static` char(1) DEFAULT NULL COMMENT '已经静态化',
  `url` varchar(2048) DEFAULT NULL COMMENT '首页地址',
  `content_path` varchar(500) DEFAULT NULL COMMENT '内容路径',
  `contain_child` char(1) DEFAULT NULL DEFAULT '1' COMMENT '包含子分类内容',
  `page_size` int(11) DEFAULT NULL COMMENT '每页数据条数',
  `allow_contribute` char(1) DEFAULT NULL COMMENT '允许投稿',
  `sortno` int(11) DEFAULT NULL DEFAULT '0' COMMENT '顺序',
  `hidden` char(1) DEFAULT NULL COMMENT '隐藏',
  `contents` int(11) NOT NULL DEFAULT '0' COMMENT '内容数',
  `extend_id` varchar(64) DEFAULT NULL COMMENT '扩展ID',
  
  `memo` varchar(512) DEFAULT NULL COMMENT '备注',
  `dstatus` char(1) NOT NULL COMMENT '数据状态 Y=已删除,N=未删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `last_modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `last_mod_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT 1 COMMENT '数据版本',
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='分类||category||cms';

drop table if exists `cms_category_attribute`;
CREATE TABLE `cms_category_attribute` (
  `id` varchar(64) NOT NULL COMMENT '分类ID category_id',
  `title` varchar(80) DEFAULT NULL COMMENT '标题',
  `keywords` varchar(100) DEFAULT NULL COMMENT '关键词',
  `description` varchar(300) DEFAULT NULL COMMENT '描述',
  `data` longtext COMMENT '数据JSON',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='分类扩展||category||cms';

drop table if exists `cms_category_model`;
CREATE TABLE `cms_category_model` (
  `id` varchar(64) NOT NULL COMMENT '分类ID category_id',
  `model_id` varchar(20) NOT NULL COMMENT '模型编码',
  `template_path` varchar(200) DEFAULT NULL COMMENT '内容模板路径',
  PRIMARY KEY (`id`,`model_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='分类模型||category||cms';

drop table if exists `cms_content`;
CREATE TABLE `cms_content` (
  `id` varchar(64) NOT NULL COMMENT '主键ID',
  `site_id` varchar(64) DEFAULT NULL COMMENT '站点ID',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `user_id` varchar(64) NOT NULL COMMENT '发表用户',
  `check_user_id` varchar(64) DEFAULT NULL COMMENT '审核用户',
  `category_id` varchar(64) NOT NULL COMMENT '分类',
  `model_id` varchar(64) DEFAULT NULL COMMENT '模型',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '父内容ID',
  `copied` char(1) NOT NULL COMMENT '是否转载 是=Y,否=N',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `editor` varchar(50) DEFAULT NULL COMMENT '编辑',
  `only_url` char(1) NOT NULL COMMENT '外链 是=Y,否=N',
  `has_images` char(1) NOT NULL COMMENT '拥有图片列表 是=Y,否=N',
  `has_files` char(1) NOT NULL COMMENT '拥有附件列表  是=Y,否=N',
  `has_static` char(1) NOT NULL COMMENT '已经静态化  是=Y,否=N',
  `url` varchar(2048) DEFAULT NULL COMMENT '地址',
  `description` varchar(300) DEFAULT NULL COMMENT '简介',
  `tag_ids` varchar(512) DEFAULT NULL COMMENT '标签',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `comments` int(11) NOT NULL COMMENT '评论数',
  `clicks` int(11) NOT NULL COMMENT '点击数',
  `publish_date` datetime NOT NULL COMMENT '发布日期',
  `check_date` datetime DEFAULT NULL COMMENT '审核日期',
  `sortno` int(11) NOT NULL DEFAULT '0' COMMENT '顺序',
  `status` varchar(32) NOT NULL COMMENT '状态：0、草稿 1、已发布 2、待审核',
  
  `memo` varchar(512) DEFAULT NULL COMMENT '备注',
  `dstatus` char(1) NOT NULL COMMENT '数据状态 Y=已删除,N=未删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `last_modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `last_mod_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT 1 COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='内容||content||cms';

drop table if exists `cms_content_attribute`;
CREATE TABLE `cms_content_attribute` (
  `id` varchar(64) NOT NULL COMMENT '内容ID',
  `source` varchar(50) DEFAULT NULL COMMENT '内容来源',
  `source_url` varchar(2048) DEFAULT NULL COMMENT '来源地址',
  `text` longtext COMMENT '内容',
  `word_count` int(11) NOT NULL COMMENT '字数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='内容扩展||content||cms';

drop table if exists `cms_content_file`;
CREATE TABLE `cms_content_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content_id` varchar(64) NOT NULL COMMENT '内容ID',
  `attachment_id` varchar(64) NOT NULL COMMENT '关联内部上传文件ID',
  `userId` varchar(64) DEFAULT NULL COMMENT '上传者ID',
  `name` varchar(200) DEFAULT NULL COMMENT '文件名称',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL访问地址',
  `path` varchar(200) DEFAULT NULL COMMENT '文件存储路径',
  `size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `image` tinyint(1) DEFAULT NULL COMMENT '是否图片',
  `clicks` int(11) DEFAULT 0 COMMENT '点击数',
  `sortno` int(11) DEFAULT 0 COMMENT '排序',
  
  `memo` varchar(512) DEFAULT NULL COMMENT '备注',
  `dstatus` char(1) NOT NULL COMMENT '数据状态 Y=已删除,N=未删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `last_modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `last_mod_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT 1 COMMENT '数据版本',
  PRIMARY KEY (`id`),
  KEY `idx_content_id` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='内容附件||content||cms';

drop table if exists `cms_pv_log`;
CREATE TABLE `cms_pv_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `object_type` varchar(64) DEFAULT NULL COMMENT '类型ID',
  `object_id` varchar(64) NOT NULL COMMENT '类型对象ID',
  `user_id` varchar(64) DEFAULT NULL COMMENT '访问用户ID 匿名用户=0',
  `pvtime` datetime NOT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='PV记录||pv||cms';

drop table if exists `cms_tag_type`;
CREATE TABLE `cms_tag_type` (
  `id` varchar(64) NOT NULL COMMENT '分类ID',
  `site_id` varchar(64) DEFAULT NULL COMMENT '站点ID',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `count` int(11) NOT NULL COMMENT '标签数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='标签类型||tag||cms';

drop table if exists `cms_tag`;
CREATE TABLE `cms_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `site_id` varchar(64) DEFAULT NULL COMMENT '站点ID',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `type_id` varchar(64) DEFAULT NULL COMMENT '分类ID',
  `search_count` int(11) DEFAULT NULL COMMENT '搜索次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='标签||tag||cms';

drop table if exists `cms_expert`;
CREATE TABLE `cms_expert` (
  `id` varchar(64) NOT NULL COMMENT '专家ID',
  `real_name` varchar(64) NOT NULL COMMENT '专家名称',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '专家图片',
  `duty` varchar(64) NOT NULL COMMENT '专家职务',
  `organization` varchar(255) NOT NULL COMMENT '所在机构',
  `introduction` varchar(2048) NOT NULL COMMENT '专家简介1000字以内',
  `genius_tag_ids` varchar(512) DEFAULT NULL COMMENT '擅长领域标签ID多个“,”号分割',
  `genius` varchar(512) DEFAULT NULL COMMENT '擅长领域200字以内',
  `sortno` int(11) DEFAULT NULL DEFAULT '0' COMMENT '顺序',
  `hidden` char(1) DEFAULT NULL COMMENT '隐藏 Y=是,N=否',
  
  `memo` varchar(512) DEFAULT NULL COMMENT '备注',
  `dstatus` char(1) NOT NULL COMMENT '数据状态 Y=已删除,N=未删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `last_modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `last_mod_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT 1 COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='专家||expert||cms';


drop table if exists `cms_expert_video`;
CREATE TABLE `cms_expert_video` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `expert_id` varchar(64) NOT NULL COMMENT '专家ID',
  `video_id` varchar(64) NOT NULL COMMENT '视频ID',
  
  `memo` varchar(512) DEFAULT NULL COMMENT '备注',
  `dstatus` char(1) NOT NULL COMMENT '数据状态 Y=已删除,N=未删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `last_modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `last_mod_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT 1 COMMENT '数据版本',
  PRIMARY KEY (`id`),
  KEY `idx_expert_id` (`expert_id`),
  KEY `idx_video_id` (`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='专家视频||expert/video||cms';


DROP TABLE IF EXISTS `cms_video`;
CREATE TABLE `cms_video` (
  `id` varchar(64) NOT NULL COMMENT '内容ID',
  `cover_url` varchar(255) DEFAULT NULL COMMENT '封面URL',
  `origin_url` varchar(255) NOT NULL COMMENT '原视频上传URL',
  `url240` varchar(255) DEFAULT NULL  COMMENT '视频转码后目标240pURL,即播放地址',
  `url480` varchar(255) DEFAULT NULL COMMENT '视频转码后目标480pURL,即播放地址',
  `url780` varchar(255) DEFAULT NULL COMMENT '视频转码后目标720pURL,即播放地址',
  `url1080` varchar(255)DEFAULT NULL  COMMENT '视频转码后目标1080pURL,即播放地址',

  `token240` varchar(255) DEFAULT NULL  COMMENT '私有240视频访问token',
  `token480` varchar(255) DEFAULT NULL COMMENT '私有480视频访问token',
  `token780` varchar(255) DEFAULT NULL COMMENT '私有780视频访问token',
  `token1080` varchar(255)DEFAULT NULL  COMMENT '私有1080视频访问token',

  `key240` varchar(128) DEFAULT NULL  COMMENT '240视频存储KEY',
  `key480` varchar(128) DEFAULT NULL COMMENT '480视频存储KEY',
  `key780` varchar(128) DEFAULT NULL COMMENT '780视频存储KEY',
  `key1080` varchar(128)DEFAULT NULL  COMMENT '1080视频存储KEY',

  `vstatus` char(1) DEFAULT '1' COMMENT '0=处理完成,1=解码处理中',
  `vkey` varchar(64)  NOT NULL COMMENT '原视频存储KEY',	
  `vavthumb_task_id` varchar(255) DEFAULT NULL COMMENT '视频转码任务id',
  `vavthumb_status` VARCHAR(10) NOT NULL DEFAULT 'N' COMMENT '转码完成: Y=是, N=否',
  `vsample_task_id` varchar(255) DEFAULT NULL COMMENT '视频缩略图采样任务id',
  `vsample_status` VARCHAR(10) NOT NULL DEFAULT 'N' COMMENT '视频缩略图采样完成: Y=是, N=否',
   
  `upload_ip` varchar(15) DEFAULT NULL COMMENT '上传IP',
  `upload_time` BIGINT(20) NOT NULL COMMENT '上传时间',
  `size` bigint(20) DEFAULT NULL COMMENT '视频大小,单位/字节',
  `length` bigint(20) DEFAULT NULL COMMENT '视频长度,单位秒',
  
  PRIMARY KEY (`id`),
  KEY `idx_vstatus` (`vstatus`),
  UNIQUE KEY `uidx_vkey` (`vkey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='视频||video||cms';


DROP TABLE IF EXISTS `cms_news`;
CREATE TABLE `cms_news` (
  `id` varchar(64) NOT NULL COMMENT '内容ID',
  `province_id` varchar(64) DEFAULT NULL COMMENT '省份ID',
  `city_id` varchar(64) DEFAULT NULL COMMENT '城市ID',
  `district_id` varchar(64) DEFAULT NULL COMMENT '地区ID',
  `villages_id` varchar(64) DEFAULT NULL COMMENT '乡镇ID',
  `hamlet_id` varchar(64) DEFAULT NULL COMMENT '村ID',
  PRIMARY KEY (`id`),
  KEY `idx_province_id` (`province_id`),
  KEY `idx_city_id` (`city_id`),
  KEY `idx_district_id` (`district_id`),
  KEY `idx_villages_id` (`villages_id`),
  KEY `idx_hamlet_id` (`hamlet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='咨讯||news||cms';

DROP TABLE IF EXISTS `cms_ad`;
CREATE TABLE `cms_ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(128) NOT NULL COMMENT '名称',
  `type` varchar(32) NOT NULL COMMENT '类型: CONTENT_DETAILS_BANNER=咨讯,视频详情轮播',
  `content_id` varchar(128) DEFAULT NULL COMMENT '内容ID,指定type类型内容ID',
  `img_url` varchar(1024) NOT NULL COMMENT '图片地址',
  `link_url` varchar(1024) DEFAULT NULL COMMENT '外链地址',
  `status` varchar(32) NOT NULL COMMENT '状态 ENABLED=正常，DISABLED=下线',
  `sortno` int(11) NOT NULL DEFAULT '0' COMMENT '顺序',

  `memo` varchar(512) DEFAULT NULL COMMENT '备注',
  `dstatus` char(1) NOT NULL COMMENT '数据状态 Y=已删除,N=未删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `last_modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `last_mod_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT 1 COMMENT '数据版本',
  PRIMARY KEY (`id`),
  KEY `idx_type` (`type`),
  KEY `idx_content_id` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='广告||ad||cms';


DROP TABLE IF EXISTS `cms_contact`;
CREATE TABLE `cms_contact` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `fullname` varchar(64) NOT NULL COMMENT '姓名',
  `first_name` varchar(64) DEFAULT NULL COMMENT '名',
  `last_name` varchar(64) DEFAULT NULL COMMENT '姓',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(32) NOT NULL COMMENT '电话号码',
  `company` varchar(64) DEFAULT NULL COMMENT '公司名字（30字以内）',
  `text` varchar(512) NOT NULL COMMENT '描述（200字以内）',
 
  `memo` varchar(512) DEFAULT NULL COMMENT '备注',
  `dstatus` char(1) NOT NULL COMMENT '数据状态 Y=已删除,N=未删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `last_modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `last_mod_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT 1 COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='联系我们||contact||cms';


DROP TABLE IF EXISTS `cms_feedback`;
CREATE TABLE `cms_feedback` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `category_id` varchar(64) NOT NULL COMMENT '反馈类型ID,cms_category ID',
  `user_id` varchar(64) NOT NULL COMMENT '0=匿名,其它=用户ID',
  `content` varchar(512) NOT NULL COMMENT '反馈内容',
  `source` varchar(50) DEFAULT NULL COMMENT '反馈来源',
  `img_urls` varchar(512) DEFAULT NULL COMMENT '图片地址,多个,号分割',
  `status` varchar(32) NOT NULL COMMENT '状态 READY=未处理 PROCESSED=已处理',

  `memo` varchar(512) DEFAULT NULL COMMENT '备注',
  `dstatus` char(1) NOT NULL COMMENT '数据状态 Y=已删除,N=未删除',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_date` bigint(20) NOT NULL COMMENT '创建时间',
  `last_modifier` bigint(20) DEFAULT NULL COMMENT '修改人',
  `last_mod_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT 1 COMMENT '数据版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='意见反馈||feedback||cms';

