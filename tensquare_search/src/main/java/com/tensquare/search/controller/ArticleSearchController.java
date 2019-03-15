package com.tensquare.search.controller;

import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService searchService;

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article) {
        searchService.add(article);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value = "/search/{keywords}/{page}/{size}",method = RequestMethod.GET)
    public Result findByKeywords(@PathVariable String keywords, @PathVariable int page, @PathVariable int size) {
        keywords = "*"+keywords+"*";
        Page<Article> pages = searchService.findByKeywords(keywords, page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Article>(pages.getTotalElements(),pages.getContent()));
    }
}
