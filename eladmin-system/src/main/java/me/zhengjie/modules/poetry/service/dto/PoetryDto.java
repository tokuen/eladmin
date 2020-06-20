/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.modules.poetry.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author yun
* @date 2020-06-20
**/
@Data
public class PoetryDto implements Serializable {

    /** 主键 */
    private Long id;

    /** 内容 */
    private String content;

    /** 摘要 */
    private String contentAbstract;

    /** 类型 */
    private Integer type;

    /** 状态:0初始化待审核,1审核中,2审核失败,3审核成功 */
    private Integer state;

    /** 作者 */
    private String author;

    /** 出自 */
    private String contentFrom;

    /** 创建人 */
    private String openId;

    /** 原图 */
    private String originalPhoto;

    /** 标准图 */
    private String standardPhoto;

    /** 长图 */
    private String longPhoto;

    /** 宽图 */
    private String widePhoto;

    /** 省略图 */
    private String ellipsisPhoto;

    /** 扩展字段 */
    private String ext;

    /** 删除标记，0代表有效，1代表无效 */
    private Integer delFlag;

    /** 创建时间 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;

    /** 保存时间 */
    private Timestamp saveTime;
}