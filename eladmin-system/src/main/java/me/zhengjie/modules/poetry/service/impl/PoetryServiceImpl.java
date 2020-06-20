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
package me.zhengjie.modules.poetry.service.impl;

import me.zhengjie.modules.poetry.domain.Poetry;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.poetry.repository.PoetryRepository;
import me.zhengjie.modules.poetry.service.PoetryService;
import me.zhengjie.modules.poetry.service.dto.PoetryDto;
import me.zhengjie.modules.poetry.service.dto.PoetryQueryCriteria;
import me.zhengjie.modules.poetry.service.mapstruct.PoetryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website https://el-admin.vip
* @description 服务实现
* @author yun
* @date 2020-06-20
**/
@Service
@RequiredArgsConstructor
public class PoetryServiceImpl implements PoetryService {

    private final PoetryRepository poetryRepository;
    private final PoetryMapper poetryMapper;

    @Override
    public Map<String,Object> queryAll(PoetryQueryCriteria criteria, Pageable pageable){
        Page<Poetry> page = poetryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(poetryMapper::toDto));
    }

    @Override
    public List<PoetryDto> queryAll(PoetryQueryCriteria criteria){
        return poetryMapper.toDto(poetryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public PoetryDto findById(Long id) {
        Poetry poetry = poetryRepository.findById(id).orElseGet(Poetry::new);
        ValidationUtil.isNull(poetry.getId(),"Poetry","id",id);
        return poetryMapper.toDto(poetry);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PoetryDto create(Poetry resources) {
        return poetryMapper.toDto(poetryRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Poetry resources) {
        Poetry poetry = poetryRepository.findById(resources.getId()).orElseGet(Poetry::new);
        ValidationUtil.isNull( poetry.getId(),"Poetry","id",resources.getId());
        poetry.copy(resources);
        poetryRepository.save(poetry);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            poetryRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<PoetryDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PoetryDto poetry : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("内容", poetry.getContent());
            map.put("摘要", poetry.getContentAbstract());
            map.put("类型", poetry.getType());
            map.put("状态:0初始化待审核,1审核中,2审核失败,3审核成功", poetry.getState());
            map.put("作者", poetry.getAuthor());
            map.put("出自", poetry.getContentFrom());
            map.put("创建人", poetry.getOpenId());
            map.put("原图", poetry.getOriginalPhoto());
            map.put("标准图", poetry.getStandardPhoto());
            map.put("长图", poetry.getLongPhoto());
            map.put("宽图", poetry.getWidePhoto());
            map.put("省略图", poetry.getEllipsisPhoto());
            map.put("扩展字段", poetry.getExt());
            map.put("删除标记，0代表有效，1代表无效", poetry.getDelFlag());
            map.put("创建时间", poetry.getCreateTime());
            map.put("更新时间", poetry.getUpdateTime());
            map.put("保存时间", poetry.getSaveTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}