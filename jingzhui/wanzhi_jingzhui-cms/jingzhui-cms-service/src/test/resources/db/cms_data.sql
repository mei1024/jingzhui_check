


-- 帮助分类
INSERT INTO `cms_category` 
( `id`, `name`, `parent_id`,  `dstatus`, create_date, `last_mod_date`) 
VALUES 
('10009', '意见反馈', '-1', 'N',1537433704505, 1537433704505),
('100002', '不能提交', '10009', 'N',1537433704505, 1537433704505),
('100003', '其它', '10009', 'N',1537433704505, 1537433704505),

 
('10011', '帮助中心', '-1', 'N',1537433704505, 1537433704505),
('100004', '常见问题', '10011', 'N',1537433704505, 1537433704505),
('100005', '提交问题', '10011', 'N',1537433704505, 1537433704505)
;