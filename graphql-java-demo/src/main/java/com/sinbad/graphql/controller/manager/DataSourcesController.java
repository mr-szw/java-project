package com.sinbad.graphql.controller.manager;

import com.google.common.collect.Lists;
import com.sinbad.graphql.po.GraphqlDataSourcesDto;
import com.sinbad.graphql.vo.response.RtListResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据源数据管理
 *
 * @author sinbad on 2023/1/10 - 11:22 PM.
 **/
@RestController
@RequestMapping("/manage/datasource")
public class DataSourcesController {

    /**
     * 查询数据源列表
     *
     * @return 查询的数据列表
     */
    public RtListResponse<GraphqlDataSourcesDto> getDatSourcesList() {
        return RtListResponse.success(Lists.newArrayList(new GraphqlDataSourcesDto()));
    }

}
