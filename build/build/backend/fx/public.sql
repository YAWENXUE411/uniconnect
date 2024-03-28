/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : PostgreSQL
 Source Server Version : 140000
 Source Host           : localhost:5432
 Source Catalog        : fx-shop
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140000
 File Encoding         : 65001

 Date: 01/11/2023 11:02:21
*/

-- ----------------------------
-- Sequence structure for t_shop_type_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_shop_type_id_seq";
CREATE SEQUENCE "public"."t_shop_type_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for t_student_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_student_id_seq";
CREATE SEQUENCE "public"."t_student_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for t_sys_user_role_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_sys_user_role_id_seq";
CREATE SEQUENCE "public"."t_sys_user_role_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for t_university_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_university_id_seq";
CREATE SEQUENCE "public"."t_university_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;


-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_comment";
CREATE TABLE "public"."t_comment" (
  "id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "shop_id" varchar(64) COLLATE "pg_catalog"."default",
  "user_id" varchar(64) COLLATE "pg_catalog"."default",
  "tags" varchar(64) COLLATE "pg_catalog"."default",
  "content" text COLLATE "pg_catalog"."default",
  "like_num" int8 DEFAULT 0,
  "flag" int2,
  "create_time" timestamp(6),
  "update_time" timestamp(6) DEFAULT now(),
  "score" numeric(2,1) DEFAULT 0
)
;
COMMENT ON COLUMN "public"."t_comment"."id" IS '评论id';
COMMENT ON COLUMN "public"."t_comment"."shop_id" IS '店铺id';
COMMENT ON COLUMN "public"."t_comment"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."t_comment"."tags" IS '标签';
COMMENT ON COLUMN "public"."t_comment"."content" IS '评论内容';
COMMENT ON COLUMN "public"."t_comment"."like_num" IS '点赞数';
COMMENT ON COLUMN "public"."t_comment"."flag" IS '标识(1：用户评论 2：商家回复)';
COMMENT ON COLUMN "public"."t_comment"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_comment"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."t_comment"."score" IS '评分';
COMMENT ON TABLE "public"."t_comment" IS '店铺评论';

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO "public"."t_comment" VALUES ('21abc09055a3467ca8fef75f5e21cff7', 'f06665ee7b224d67b387b884cf49f78c', '455695888625782596', NULL, 'Test', 1, NULL, '2023-10-31 13:49:11.521', '2023-10-31 13:49:11.521912', 5.0);
INSERT INTO "public"."t_comment" VALUES ('86625593c5a0406a92e84e2c5b21179b', 'f06665ee7b224d67b387b884cf49f78c', '455695888625782596', NULL, 'Test2', 1, NULL, '2023-10-31 13:49:30.099', '2023-10-31 13:49:30.100897', 5.0);

-- ----------------------------
-- Table structure for t_comment_like
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_comment_like";
CREATE TABLE "public"."t_comment_like" (
  "comment_id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "user_id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "create_time" timestamp(6) DEFAULT now()
)
;
COMMENT ON COLUMN "public"."t_comment_like"."comment_id" IS '评论id';
COMMENT ON COLUMN "public"."t_comment_like"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."t_comment_like"."create_time" IS '创建时间';
COMMENT ON TABLE "public"."t_comment_like" IS '店铺评论点赞信息';

-- ----------------------------
-- Records of t_comment_like
-- ----------------------------
INSERT INTO "public"."t_comment_like" VALUES ('21abc09055a3467ca8fef75f5e21cff7', '455695888625782596', '2023-10-31 13:49:32.977');
INSERT INTO "public"."t_comment_like" VALUES ('86625593c5a0406a92e84e2c5b21179b', '455695888625782596', '2023-10-31 13:49:52.604');

-- ----------------------------
-- Table structure for t_shop
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_shop";
CREATE TABLE "public"."t_shop" (
  "id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "type_id" int2,
  "create_user_id" varchar(64) COLLATE "pg_catalog"."default",
  "status" int2,
  "lng" varchar(20) COLLATE "pg_catalog"."default",
  "lat" varchar(20) COLLATE "pg_catalog"."default",
  "phone" varchar(32) COLLATE "pg_catalog"."default",
  "logo" varchar(64) COLLATE "pg_catalog"."default",
  "address" varchar(255) COLLATE "pg_catalog"."default",
  "desc" text COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_time" timestamp(6) DEFAULT now(),
  "score" numeric(2,1) DEFAULT 0
)
;
COMMENT ON COLUMN "public"."t_shop"."id" IS '店铺id';
COMMENT ON COLUMN "public"."t_shop"."name" IS '店铺名';
COMMENT ON COLUMN "public"."t_shop"."type_id" IS '店铺类型';
COMMENT ON COLUMN "public"."t_shop"."create_user_id" IS '创建人id';
COMMENT ON COLUMN "public"."t_shop"."status" IS '状态(0:未审核 1:审核中 2:审核通过 -1:已注销 -2:审核不通过 )';
COMMENT ON COLUMN "public"."t_shop"."lng" IS '经度';
COMMENT ON COLUMN "public"."t_shop"."lat" IS '纬度';
COMMENT ON COLUMN "public"."t_shop"."phone" IS '联系电话';
COMMENT ON COLUMN "public"."t_shop"."logo" IS '店铺logo';
COMMENT ON COLUMN "public"."t_shop"."address" IS '地址';
COMMENT ON COLUMN "public"."t_shop"."desc" IS '描述';
COMMENT ON COLUMN "public"."t_shop"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_shop"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."t_shop"."score" IS '店铺平均评分';
COMMENT ON TABLE "public"."t_shop" IS '店铺信息';

-- ----------------------------
-- Records of t_shop
-- ----------------------------
INSERT INTO "public"."t_shop" VALUES ('f06665ee7b224d67b387b884cf49f78c', 'Test', NULL, '455695888625782596', 2, '-3.5400587478530623', '54.652910634462344', NULL, NULL, NULL, 'test', '2023-10-31 13:48:59.49', '2023-10-31 13:48:59.493498', 0.0);

-- ----------------------------
-- Table structure for t_shop_score
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_shop_score";
CREATE TABLE "public"."t_shop_score" (
  "shop_id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "user_id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "score" numeric(2,1) DEFAULT 0,
  "update_time" timestamp(6) DEFAULT now()
)
;
COMMENT ON COLUMN "public"."t_shop_score"."shop_id" IS '店铺id';
COMMENT ON COLUMN "public"."t_shop_score"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."t_shop_score"."score" IS '评分';
COMMENT ON COLUMN "public"."t_shop_score"."update_time" IS '创建时间';
COMMENT ON TABLE "public"."t_shop_score" IS '店铺有效评分';

-- ----------------------------
-- Records of t_shop_score
-- ----------------------------
INSERT INTO "public"."t_shop_score" VALUES ('f06665ee7b224d67b387b884cf49f78c', '455695888625782596', 5.0, '2023-10-31 13:49:11.521912');

-- ----------------------------
-- Table structure for t_shop_type
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_shop_type";
CREATE TABLE "public"."t_shop_type" (
  "id" int4 NOT NULL DEFAULT nextval('t_shop_type_id_seq'::regclass),
  "name" varchar(100) COLLATE "pg_catalog"."default",
  "parent_id" int4 DEFAULT 0,
  "icon" varchar(255) COLLATE "pg_catalog"."default",
  "sorter" int2,
  "status" int2,
  "desc" text COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_time" timestamp(6) DEFAULT now()
)
;
COMMENT ON COLUMN "public"."t_shop_type"."id" IS '店铺分类id';
COMMENT ON COLUMN "public"."t_shop_type"."name" IS '店铺分类名称';
COMMENT ON COLUMN "public"."t_shop_type"."parent_id" IS '上级分类id（一级分类默认为0）';
COMMENT ON COLUMN "public"."t_shop_type"."icon" IS '分类图片';
COMMENT ON COLUMN "public"."t_shop_type"."sorter" IS '排序';
COMMENT ON COLUMN "public"."t_shop_type"."status" IS '状态(0:启用 -1:已删除)';
COMMENT ON COLUMN "public"."t_shop_type"."desc" IS '描述';
COMMENT ON COLUMN "public"."t_shop_type"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_shop_type"."update_time" IS '更新时间';
COMMENT ON TABLE "public"."t_shop_type" IS '店铺类型';

-- ----------------------------
-- Records of t_shop_type
-- ----------------------------
INSERT INTO "public"."t_shop_type" VALUES (1, '蔬菜水果', 0, NULL, 1, 0, '一级分类', '2023-10-22 20:08:25.863369', '2023-10-22 20:08:25.863369');
INSERT INTO "public"."t_shop_type" VALUES (2, '超市便利', 0, NULL, 2, 0, '一级分类', '2023-10-22 20:08:30.913336', '2023-10-22 20:08:30.913336');
INSERT INTO "public"."t_shop_type" VALUES (3, '日用百货', 0, NULL, 3, 0, '一级分类', '2023-10-22 20:08:34.631313', '2023-10-22 20:08:34.631313');
INSERT INTO "public"."t_shop_type" VALUES (4, '休闲娱乐', 0, NULL, 4, 0, '一级分类', '2023-10-22 20:08:39.5045', '2023-10-22 20:08:39.5045');
INSERT INTO "public"."t_shop_type" VALUES (5, '家电家居', 0, NULL, 5, 0, '一级分类', '2023-10-22 20:08:43.630035', '2023-10-22 20:08:43.630035');
INSERT INTO "public"."t_shop_type" VALUES (6, '看病买药', 0, NULL, 6, 0, '一级分类', '2023-10-22 20:08:47.985717', '2023-10-22 20:08:47.985717');
INSERT INTO "public"."t_shop_type" VALUES (7, '酒店民宿', 0, NULL, 7, 0, '一级分类', '2023-10-22 20:08:52.002977', '2023-10-22 20:08:52.002977');
INSERT INTO "public"."t_shop_type" VALUES (8, '美食', 0, NULL, 8, 0, '一级分类', '2023-10-22 20:08:55.965476', '2023-10-22 20:08:55.965476');
INSERT INTO "public"."t_shop_type" VALUES (9, '生鲜菜场', 1, NULL, 9, 0, '蔬菜水果', '2023-10-22 20:08:59.99827', '2023-10-22 20:08:59.99827');
INSERT INTO "public"."t_shop_type" VALUES (10, '水果超市', 1, NULL, 10, 0, '蔬菜水果', '2023-10-22 20:09:04.543843', '2023-10-22 20:09:04.543843');
INSERT INTO "public"."t_shop_type" VALUES (11, '海鲜水产', 1, NULL, 11, 0, '蔬菜水果', '2023-10-22 20:31:19.60898', '2023-10-22 20:31:19.60898');
INSERT INTO "public"."t_shop_type" VALUES (12, '超市卖场', 2, NULL, 12, 0, '超市便利', '2023-10-22 20:31:23.634429', '2023-10-22 20:31:23.634429');
INSERT INTO "public"."t_shop_type" VALUES (13, '便利店', 2, NULL, 13, 0, '超市便利', '2023-10-22 20:31:27.200247', '2023-10-22 20:31:27.200247');
INSERT INTO "public"."t_shop_type" VALUES (14, '家居日用', 3, NULL, 14, 0, '日用百货', '2023-10-22 20:31:29.817479', '2023-10-22 20:31:29.817479');
INSERT INTO "public"."t_shop_type" VALUES (15, '服饰鞋包', 3, NULL, 15, 0, '日用百货', '2023-10-22 20:31:32.944399', '2023-10-22 20:31:32.944399');
INSERT INTO "public"."t_shop_type" VALUES (16, '母婴用品', 3, NULL, 16, 0, '日用百货', '2023-10-22 20:31:35.493857', '2023-10-22 20:31:35.493857');
INSERT INTO "public"."t_shop_type" VALUES (17, '宠物用品', 3, NULL, 17, 0, '日用百货', '2023-10-22 20:31:37.958538', '2023-10-22 20:31:37.958538');
INSERT INTO "public"."t_shop_type" VALUES (18, '手机数码', 3, NULL, 18, 0, '日用百货', '2023-10-22 20:31:40.142828', '2023-10-22 20:31:40.142828');
INSERT INTO "public"."t_shop_type" VALUES (19, '家用电器', 3, NULL, 19, 0, '日用百货', '2023-10-22 20:31:42.501878', '2023-10-22 20:31:42.501878');
INSERT INTO "public"."t_shop_type" VALUES (20, '彩妆护肤', 3, NULL, 20, 0, '日用百货', '2023-10-22 20:31:45.430239', '2023-10-22 20:31:45.430239');
INSERT INTO "public"."t_shop_type" VALUES (21, '按摩/足疗', 4, NULL, 21, 0, '休闲娱乐', '2023-10-22 20:47:47.24957', '2023-10-22 20:47:47.24957');
INSERT INTO "public"."t_shop_type" VALUES (22, '洗浴/蒸汗', 4, NULL, 22, 0, '休闲娱乐', '2023-10-22 20:47:50.01931', '2023-10-22 20:47:50.01931');
INSERT INTO "public"."t_shop_type" VALUES (23, 'KTV', 4, NULL, 23, 0, '休闲娱乐', '2023-10-22 20:47:52.294259', '2023-10-22 20:47:52.294259');
INSERT INTO "public"."t_shop_type" VALUES (24, '酒吧', 4, NULL, 24, 0, '休闲娱乐', '2023-10-22 20:47:54.400454', '2023-10-22 20:47:54.400454');
INSERT INTO "public"."t_shop_type" VALUES (25, '台球馆', 4, NULL, 25, 0, '休闲娱乐', '2023-10-22 20:47:56.590532', '2023-10-22 20:47:56.590532');
INSERT INTO "public"."t_shop_type" VALUES (26, '棋牌室', 4, NULL, 26, 0, '休闲娱乐', '2023-10-22 20:47:59.246456', '2023-10-22 20:47:59.246456');
INSERT INTO "public"."t_shop_type" VALUES (27, '网吧/电竞', 4, NULL, 27, 0, '休闲娱乐', '2023-10-22 20:48:02.038339', '2023-10-22 20:48:02.038339');
INSERT INTO "public"."t_shop_type" VALUES (28, '影院', 4, NULL, 28, 0, '休闲娱乐', '2023-10-22 20:48:04.995879', '2023-10-22 20:48:04.995879');
INSERT INTO "public"."t_shop_type" VALUES (29, '电玩游戏厅', 4, NULL, 29, 0, '休闲娱乐', '2023-10-22 20:48:07.698706', '2023-10-22 20:48:07.698706');
INSERT INTO "public"."t_shop_type" VALUES (30, '健身中心', 4, NULL, 30, 0, '休闲娱乐', '2023-10-22 20:48:10.322414', '2023-10-22 20:48:10.322414');
INSERT INTO "public"."t_shop_type" VALUES (31, '儿童乐园', 4, NULL, 31, 0, '休闲娱乐', '2023-10-22 20:50:41.621336', '2023-10-22 20:50:41.621336');
INSERT INTO "public"."t_shop_type" VALUES (32, '密室/轰趴', 4, NULL, 32, 0, '休闲娱乐', '2023-10-22 20:50:44.971195', '2023-10-22 20:50:44.971195');
INSERT INTO "public"."t_shop_type" VALUES (33, '小吃快餐', 8, NULL, 33, 0, '美食', '2023-10-22 20:50:47.190591', '2023-10-22 20:50:47.190591');
INSERT INTO "public"."t_shop_type" VALUES (34, '饮品甜点', 8, NULL, 34, 0, '美食', '2023-10-22 20:50:49.39997', '2023-10-22 20:50:49.39997');
INSERT INTO "public"."t_shop_type" VALUES (35, '火锅', 8, NULL, 35, 0, '美食', '2023-10-22 20:50:51.38093', '2023-10-22 20:50:51.38093');
INSERT INTO "public"."t_shop_type" VALUES (36, '自助餐', 8, NULL, 36, 0, '美食', '2023-10-22 20:50:53.613224', '2023-10-22 20:50:53.613224');
INSERT INTO "public"."t_shop_type" VALUES (37, '烤肉烧烤', 8, NULL, 37, 0, '美食', '2023-10-22 20:50:57.599255', '2023-10-22 20:50:57.599255');
INSERT INTO "public"."t_shop_type" VALUES (38, '地方菜系', 8, NULL, 38, 0, '美食', '2023-10-22 20:51:00.010502', '2023-10-22 20:51:00.010502');
INSERT INTO "public"."t_shop_type" VALUES (39, '异域料理', 8, NULL, 39, 0, '美食', '2023-10-22 20:51:02.288867', '2023-10-22 20:51:02.288867');
INSERT INTO "public"."t_shop_type" VALUES (40, '鱼线海鲜', 8, NULL, 40, 0, '美食', '2023-10-22 20:51:04.823034', '2023-10-22 20:51:04.823034');
INSERT INTO "public"."t_shop_type" VALUES (41, '假日精选', 7, NULL, 41, 0, '酒店民宿', '2023-10-22 20:51:07.004503', '2023-10-22 20:51:07.004503');
INSERT INTO "public"."t_shop_type" VALUES (42, '星级酒店', 7, NULL, 42, 0, '酒店民宿', '2023-10-22 20:51:09.386409', '2023-10-22 20:51:09.386409');
INSERT INTO "public"."t_shop_type" VALUES (43, '度假民宿', 7, NULL, 43, 0, '酒店民宿', '2023-10-22 20:51:13.449742', '2023-10-22 20:51:13.449742');
INSERT INTO "public"."t_shop_type" VALUES (44, '主题酒店', 7, NULL, 44, 0, '酒店民宿', '2023-10-22 20:51:15.994146', '2023-10-22 20:51:15.994146');
INSERT INTO "public"."t_shop_type" VALUES (45, '钟点房', 7, NULL, 45, 0, '酒店民宿', '2023-10-22 20:51:18.303999', '2023-10-22 20:51:18.303999');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_student";
CREATE TABLE "public"."t_student" (
  "id" int4 NOT NULL DEFAULT nextval('t_student_id_seq'::regclass),
  "name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "sex" int2,
  "age" int2 DEFAULT 0,
  "university" varchar(100) COLLATE "pg_catalog"."default",
  "college" varchar(100) COLLATE "pg_catalog"."default",
  "specialized" varchar(100) COLLATE "pg_catalog"."default",
  "grades" numeric(8,2) DEFAULT 0,
  "gpa" numeric(8,2) DEFAULT 0,
  "degree" varchar(100) COLLATE "pg_catalog"."default",
  "offer" varchar(100) COLLATE "pg_catalog"."default",
  "institution" varchar(100) COLLATE "pg_catalog"."default",
  "price" numeric(8,2) DEFAULT 0,
  "create_time" timestamp(6),
  "update_time" timestamp(6) DEFAULT now()
)
;
COMMENT ON COLUMN "public"."t_student"."id" IS 'id';
COMMENT ON COLUMN "public"."t_student"."name" IS '姓名';
COMMENT ON COLUMN "public"."t_student"."sex" IS '性别(1-男  2-女)';
COMMENT ON COLUMN "public"."t_student"."age" IS '年龄';
COMMENT ON COLUMN "public"."t_student"."university" IS '所属学校';
COMMENT ON COLUMN "public"."t_student"."college" IS '所属学院';
COMMENT ON COLUMN "public"."t_student"."specialized" IS '专业';
COMMENT ON COLUMN "public"."t_student"."grades" IS '成绩';
COMMENT ON COLUMN "public"."t_student"."gpa" IS 'gpa';
COMMENT ON COLUMN "public"."t_student"."degree" IS '学位';
COMMENT ON COLUMN "public"."t_student"."offer" IS 'offer';
COMMENT ON COLUMN "public"."t_student"."institution" IS '研究生机构';
COMMENT ON COLUMN "public"."t_student"."price" IS '金额';
COMMENT ON COLUMN "public"."t_student"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_student"."update_time" IS '更新时间';
COMMENT ON TABLE "public"."t_student" IS '学生信息';

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO "public"."t_student" VALUES (1, '王邦兆', NULL, 0, NULL, NULL, 'communicat_on_engineering', 91.20, 0.00, NULL, NULL, '环球留学', 15000.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (2, '郭欣', NULL, 0, NULL, NULL, 'communicat_on_engineering', 80.67, 0.00, NULL, NULL, '新东方', 18000.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (3, '李浩明', NULL, 0, NULL, NULL, 'communicat_on_engineering', 75.57, 0.00, NULL, NULL, '大学选择', 2000.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (4, '刘亦宣', NULL, 0, NULL, NULL, 'communicat_on_engineering', 83.02, 0.00, NULL, NULL, '博瑞吉', 10000.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (5, '蒋逸凡', NULL, 0, NULL, NULL, 'communicat_on_engineering', 85.63, 0.00, NULL, NULL, '新东方', 20000.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (7, '乔峥', NULL, 0, NULL, NULL, 'communicat_on_engineering', 82.83, 0.00, NULL, NULL, '百利天下', 20000.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (11, '朱雨晴', NULL, 0, NULL, NULL, 'communicat_on_engineering', 82.04, 0.00, NULL, NULL, '翰兴留学', 3000.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (12, '杨灿', NULL, 0, NULL, NULL, 'communicat_on_engineering', 79.67, 0.00, NULL, NULL, '翰兴留学', 3000.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (13, '罗雨禾', NULL, 0, NULL, NULL, 'communicat_on_engineering', 79.74, 0.00, NULL, NULL, '翰兴留学', 3000.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (14, '王芊予', NULL, 0, NULL, NULL, 'communicat_on_engineering', 79.46, 0.00, NULL, NULL, '翰兴留学', 3000.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (15, '祁昶宇', NULL, 0, NULL, NULL, 'communicat_on_engineering', 89.02, 0.00, NULL, NULL, '芥末留学', 10000.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (16, '陈宇轩', NULL, 0, NULL, NULL, 'communicat_on_engineering', 83.20, 0.00, NULL, NULL, '达程', 12000.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (20, '潘心依', NULL, 0, NULL, NULL, 'communicat_on_engineering', 88.33, 0.00, NULL, NULL, '翰兴留学', 3000.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (6, '张耀午', NULL, 0, NULL, NULL, 'communicat_on_engineering', 86.17, 0.00, NULL, NULL, '否', 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (8, '韦文娟', NULL, 0, NULL, NULL, 'communicat_on_engineering', 83.28, 0.00, NULL, NULL, '否', 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (9, '余佩轩', NULL, 0, NULL, NULL, 'communicat_on_engineering', 78.02, 0.00, NULL, NULL, '否', 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (10, '许文馨', NULL, 0, NULL, NULL, 'communicat_on_engineering', 81.52, 0.00, NULL, NULL, '否', 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (17, '董瀚文', NULL, 0, NULL, NULL, 'communicat_on_engineering', 78.12, 0.00, NULL, NULL, '否', 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (18, '杨金平', NULL, 0, NULL, NULL, 'communicat_on_engineering', 79.39, 0.00, NULL, NULL, '否', 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (19, '蒋湛', NULL, 0, NULL, NULL, 'communicat_on_engineering', 80.03, 0.00, NULL, NULL, '否', 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (21, '金子牛', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (22, '陈明源', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (23, '季赵一', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (24, '金雨洋', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (25, '陈炎宇', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (26, '李牧言', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (27, '陆心怡', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (28, '俞孙兴', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (29, '任芃桢', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (30, '陈子洋', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (31, '张文志', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (32, '刘苏翔', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (33, '孙培尧', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (34, '张航宁', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (35, '赵瑞轩', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (36, '庄雨', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (37, '殷文琪', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (38, '夏子轶', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (39, '陆子立', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (40, '李思惟', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (41, '佘子晗', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (42, '杨奕菲', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (43, '徐嘉蔚', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (44, '顾喆纯', NULL, 0, NULL, NULL, 'communicat_on_engineering', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (45, '孙雨田', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (46, '包纪龙', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (47, '张宇荻', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (48, '于子乔', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (49, '魏徐雨凡', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (50, '张彦清', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (51, '卜璐', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (52, '陈嘉晟', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (53, '周义', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (54, '杭妤迪', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (55, '夏晶琰', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (56, '欧阳玉珏', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (57, '钱继尧', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (58, '权司如', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (59, '陈璐', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (60, '顾蕴', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (61, '周文妍', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (62, '张璟璇', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (63, '于淼', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (64, '沈晗曦', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (65, '沈星汝', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (66, '王倩', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (67, '李锦蓉', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (68, '彭楚雯', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (69, '陈千里', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (70, '陈昊蓝', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (71, '李瑶', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (72, '孙金龙', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (73, '顾计斌', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (74, '张哲', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (75, '郭子睿', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (76, '张雍乾', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (77, '张宇霄', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (78, '姜逸豪', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (79, '张杰', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (80, '卜一欣', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (81, '徐加瑜', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (82, '于铠瑞', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (83, '吕少杰', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (84, '李彦骐', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (85, '张骏晔', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (86, '奚刘畅', NULL, 0, NULL, NULL, 'project_management', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (87, '姬崇桂', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (88, '黄相民', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (89, '曹可凡', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (90, '许宾伦', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (91, '张依婷', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (92, '孙铖', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (93, '张钒', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (94, '王博铮', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (95, '叶懿珊', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (96, '张涵', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (97, '陈蒙川', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (98, '郭欣浩', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (99, '周栩宸', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (100, '戴万翀', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.581272');
INSERT INTO "public"."t_student" VALUES (101, '倪玮辰', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (102, '赵辰瑞', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (103, '杨雨帆', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (104, '王睿', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (105, '陈鹏宇', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (106, '张骁', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (107, '郑智齐', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (108, '王浦涵', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (109, '施凯宸', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (110, '陈志伟', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (111, '袁贝怡', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (112, '胡皓宇', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (113, '贺鼎程', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (114, '杨佳聪', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (115, '吴一凡', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (116, '匡家颖', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (117, '耿千钧', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (118, '陈翔', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (119, '黄池 ', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (120, '胡宸豪', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (121, '陆鸿律', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (122, '欧阳锡', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (123, '蒋笑明 ', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (124, '孙祥臻', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (125, '顾茁奕', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (126, '张艺腾', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');
INSERT INTO "public"."t_student" VALUES (127, '李豪晴', NULL, 0, NULL, NULL, 'computer_science', 0.00, 0.00, NULL, NULL, NULL, 0.00, NULL, '2023-10-26 12:07:04.590263');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sys_role";
CREATE TABLE "public"."t_sys_role" (
  "role_id" int2 NOT NULL,
  "name" varchar(30) COLLATE "pg_catalog"."default" NOT NULL,
  "update_time" timestamp(6) DEFAULT now()
)
;
COMMENT ON COLUMN "public"."t_sys_role"."role_id" IS '角色id';
COMMENT ON COLUMN "public"."t_sys_role"."name" IS '角色名称';
COMMENT ON COLUMN "public"."t_sys_role"."update_time" IS '修改时间';

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO "public"."t_sys_role" VALUES (1, '管理员', '2022-04-11 15:44:23.9804');
INSERT INTO "public"."t_sys_role" VALUES (2, '普通用户', '2022-04-11 16:17:26.109852');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sys_user";
CREATE TABLE "public"."t_sys_user" (
  "id" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "username" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(64) COLLATE "pg_catalog"."default",
  "status" int2,
  "create_time" timestamp(6),
  "update_time" timestamp(6) DEFAULT now(),
  "nickname" varchar(64) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."t_sys_user"."id" IS '用户id';
COMMENT ON COLUMN "public"."t_sys_user"."username" IS '用户名';
COMMENT ON COLUMN "public"."t_sys_user"."password" IS '密码';
COMMENT ON COLUMN "public"."t_sys_user"."status" IS '状态(0-未审核 1-已审核)';
COMMENT ON COLUMN "public"."t_sys_user"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_sys_user"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."t_sys_user"."nickname" IS '昵称';
COMMENT ON TABLE "public"."t_sys_user" IS '系统用户';

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO "public"."t_sys_user" VALUES ('673e11c6f2ed43828049ca0300b09b08', '1927686067@qq.com', '123456', 1, '2023-10-31 17:20:51.18', '2023-10-31 17:20:51.168741', 'xiaosu');
INSERT INTO "public"."t_sys_user" VALUES ('644c12df517249bf93ba53bd84a83095', '1655016737@qq.com', '111111', 1, '2023-11-01 03:32:32.811', '2023-11-01 03:32:32.784732', 'wqy');
INSERT INTO "public"."t_sys_user" VALUES ('455695888625782596', 'yawen.yin.xue@gmail.com', '123456', 1, '2023-10-23 17:21:35.209363', '2023-10-23 17:21:35.209363', 'Administrator');
INSERT INTO "public"."t_sys_user" VALUES ('649673b182bc4f489f400a5c9015bd04', 'test@test.con', '123456', 1, '2023-11-01 10:04:14.067', '2023-11-01 10:04:14.048854', NULL);

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sys_user_role";
CREATE TABLE "public"."t_sys_user_role" (
  "id" int4 NOT NULL DEFAULT nextval('t_sys_user_role_id_seq'::regclass),
  "user_id" varchar(64) COLLATE "pg_catalog"."default",
  "role_id" int2,
  "update_time" timestamp(6) DEFAULT now()
)
;
COMMENT ON COLUMN "public"."t_sys_user_role"."id" IS 'id';
COMMENT ON COLUMN "public"."t_sys_user_role"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."t_sys_user_role"."role_id" IS '角色id';
COMMENT ON COLUMN "public"."t_sys_user_role"."update_time" IS '修改时间';

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO "public"."t_sys_user_role" VALUES (1, '455695888625782596', 1, '2023-10-23 11:53:01');
INSERT INTO "public"."t_sys_user_role" VALUES (10, '673e11c6f2ed43828049ca0300b09b08', 2, '2023-10-31 17:20:51.168741');
INSERT INTO "public"."t_sys_user_role" VALUES (11, '644c12df517249bf93ba53bd84a83095', 2, '2023-11-01 03:32:32.784732');
INSERT INTO "public"."t_sys_user_role" VALUES (12, '649673b182bc4f489f400a5c9015bd04', 2, '2023-11-01 10:04:14.048854');

-- ----------------------------
-- Table structure for t_university
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_university";
CREATE TABLE "public"."t_university" (
  "id" int4 NOT NULL DEFAULT nextval('t_university_id_seq'::regclass),
  "name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "ranking" int2,
  "lng" varchar(20) COLLATE "pg_catalog"."default",
  "lat" varchar(20) COLLATE "pg_catalog"."default",
  "phone" varchar(32) COLLATE "pg_catalog"."default",
  "logo" varchar(64) COLLATE "pg_catalog"."default",
  "address" varchar(255) COLLATE "pg_catalog"."default",
  "desc" text COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_time" timestamp(6) DEFAULT now()
)
;
COMMENT ON COLUMN "public"."t_university"."id" IS '学校id';
COMMENT ON COLUMN "public"."t_university"."name" IS '学校名称';
COMMENT ON COLUMN "public"."t_university"."ranking" IS '排名';
COMMENT ON COLUMN "public"."t_university"."lng" IS '经度';
COMMENT ON COLUMN "public"."t_university"."lat" IS '纬度';
COMMENT ON COLUMN "public"."t_university"."phone" IS '电话';
COMMENT ON COLUMN "public"."t_university"."logo" IS 'logo';
COMMENT ON COLUMN "public"."t_university"."address" IS '地址';
COMMENT ON COLUMN "public"."t_university"."desc" IS '简介';
COMMENT ON COLUMN "public"."t_university"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_university"."update_time" IS '更新时间';
COMMENT ON TABLE "public"."t_university" IS '学校信息';

-- ----------------------------
-- Records of t_university
-- ----------------------------
INSERT INTO "public"."t_university" VALUES (1, 'University_of_Cambridge', 2, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (2, 'University_of_Oxford', 3, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (3, 'Imperial_College_London', 6, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (4, 'University_College_London', 9, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (5, 'The_University_of_Edinburgh', 22, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (6, 'The_University_of_Manchester', 32, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (7, 'Kings_College_London', 40, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (8, 'The London_School_of_Economics_and_Political_Science', 45, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (9, 'University_of_Bristol', 55, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (10, 'The_University_of_Warwick', 67, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (11, 'University_of_Leeds', 75, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (12, 'University_of_Glasgow', 76, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (13, 'Durham_University', 78, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (14, 'University_of_Southampton', 82, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (15, 'University_of_Birmingham', 84, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (16, 'University_of_St_Andrews', 96, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (17, 'University_of_Nottingham', 101, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (18, 'The_University_of_Sheffield', 104, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (19, 'Newcastle_University', 110, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (20, 'Lancaster_University', 122, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (21, 'Queen_Mary_University_of_London', 145, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (22, 'University_of_Bath', 148, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (23, 'University_of_Exeter', 153, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (24, 'Cardiff_University', 155, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (25, 'University_of_York', 167, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (26, 'University_of_Reading', 170, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');
INSERT INTO "public"."t_university" VALUES (27, 'The_University_of_Liverpool', 178, NULL, NULL, NULL, NULL, NULL, NULL, '2023-10-25 21:33:42.682385', '2023-10-25 21:33:42.682385');


-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."t_shop_type_id_seq"
OWNED BY "public"."t_shop_type"."id";
SELECT setval('"public"."t_shop_type_id_seq"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."t_student_id_seq"
OWNED BY "public"."t_student"."id";
SELECT setval('"public"."t_student_id_seq"', 138, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."t_sys_user_role_id_seq"
OWNED BY "public"."t_sys_user_role"."id";
SELECT setval('"public"."t_sys_user_role_id_seq"', 13, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."t_university_id_seq"
OWNED BY "public"."t_university"."id";
SELECT setval('"public"."t_university_id_seq"', 2, false);

-- ----------------------------
-- Primary Key structure for table t_comment
-- ----------------------------
ALTER TABLE "public"."t_comment" ADD CONSTRAINT "t_comment_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_shop_score
-- ----------------------------
ALTER TABLE "public"."t_shop_score" ADD CONSTRAINT "t_shop_score_pkey" PRIMARY KEY ("shop_id", "user_id");

-- ----------------------------
-- Primary Key structure for table t_shop_type
-- ----------------------------
ALTER TABLE "public"."t_shop_type" ADD CONSTRAINT "t_shop_type_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_student
-- ----------------------------
ALTER TABLE "public"."t_student" ADD CONSTRAINT "t_student_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_sys_user
-- ----------------------------
ALTER TABLE "public"."t_sys_user" ADD CONSTRAINT "t_sys_user_pkey" PRIMARY KEY ("username");

-- ----------------------------
-- Primary Key structure for table t_sys_user_role
-- ----------------------------
ALTER TABLE "public"."t_sys_user_role" ADD CONSTRAINT "t_sys_user_role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_university
-- ----------------------------
ALTER TABLE "public"."t_university" ADD CONSTRAINT "t_university_pkey" PRIMARY KEY ("id");
