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
package me.zhengjie.modules.poetry.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author yun
* @date 2020-06-20
**/
@Entity
@Data
@Table(name="poetry")
public class Poetry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private Long id;

    @Column(name = "content",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "内容")
    private String content;

    @Column(name = "content_abstract")
    @ApiModelProperty(value = "摘要")
    private String contentAbstract;

    @Column(name = "type",nullable = false)
    @NotNull
    @ApiModelProperty(value = "类型")
    private Integer type;

    @Column(name = "state")
    @ApiModelProperty(value = "状态:0初始化待审核,1审核中,2审核失败,3审核成功")
    private Integer state;

    @Column(name = "author",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "作者")
    private String author;

    @Column(name = "content_from",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "出自")
    private String contentFrom;

    @Column(name = "open_id")
    @ApiModelProperty(value = "创建人")
    private String openId;

    @Column(name = "original_photo")
    @ApiModelProperty(value = "原图")
    private String originalPhoto;

    @Column(name = "standard_photo")
    @ApiModelProperty(value = "标准图")
    private String standardPhoto;

    @Column(name = "long_photo")
    @ApiModelProperty(value = "长图")
    private String longPhoto;

    @Column(name = "wide_photo")
    @ApiModelProperty(value = "宽图")
    private String widePhoto;

    @Column(name = "ellipsis_photo")
    @ApiModelProperty(value = "省略图")
    private String ellipsisPhoto;

    @Column(name = "ext")
    @ApiModelProperty(value = "扩展字段")
    private String ext;

    @Column(name = "del_flag")
    @ApiModelProperty(value = "删除标记，0代表有效，1代表无效")
    private Integer delFlag;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    @Column(name = "save_time")
    @ApiModelProperty(value = "保存时间")
    private Timestamp saveTime;

    public void copy(Poetry source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}