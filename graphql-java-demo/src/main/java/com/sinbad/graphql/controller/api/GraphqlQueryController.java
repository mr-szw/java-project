package com.sinbad.graphql.controller.api;

import com.sinbad.graphql.service.GraphqlQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sinbad on 2020/1/10.
 **/
@RestController
@RequestMapping(("/graphql/query"))
public class GraphqlQueryController {

    @Autowired
    private GraphqlQueryService graphqlQueryService;

    @GetMapping("/get")
    public Object query() {
        return graphqlQueryService.query();
    }
}
