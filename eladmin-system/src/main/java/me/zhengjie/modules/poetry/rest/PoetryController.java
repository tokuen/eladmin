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
package me.zhengjie.modules.poetry.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.poetry.domain.Poetry;
import me.zhengjie.modules.poetry.service.PoetryService;
import me.zhengjie.modules.poetry.service.dto.PoetryQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @author yun
* @date 2020-06-20
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "诗歌后台管理")
@RequestMapping("/api/poetry")
public class PoetryController {

    private final PoetryService poetryService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('poetry:list')")
    public void download(HttpServletResponse response, PoetryQueryCriteria criteria) throws IOException {
        poetryService.download(poetryService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询诗歌后台")
    @ApiOperation("查询诗歌后台")
    @PreAuthorize("@el.check('poetry:list')")
    public ResponseEntity<Object> query(PoetryQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(poetryService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增诗歌后台")
    @ApiOperation("新增诗歌后台")
    @PreAuthorize("@el.check('poetry:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Poetry resources){
        return new ResponseEntity<>(poetryService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改诗歌后台")
    @ApiOperation("修改诗歌后台")
    @PreAuthorize("@el.check('poetry:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Poetry resources){
        poetryService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除诗歌后台")
    @ApiOperation("删除诗歌后台")
    @PreAuthorize("@el.check('poetry:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        poetryService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}