package com.tensquare.article.controller;

import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Article> ArticleList = articleService.findAll();
        return new Result(true, StatusCode.OK, "success", ArticleList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article) {
        articleService.save(article);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value="/{articleId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Article article) {
        articleService.update(article);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{articleId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String articleId) {
        articleService.delete(articleId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String articleId) {
        Article article = articleService.findById(articleId);
        return new Result(true, StatusCode.OK, "success", article);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result find(@RequestBody Article article) {
        List<Article> articleList = articleService.search(article);
        return new Result(true, StatusCode.OK, "success", articleList);
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result searchByPage(@PathVariable int page, @PathVariable int size,Article article) {
        PageResult<Article> pageResult = articleService.searchByPage(article, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    @RequestMapping(value = "/examine/{id}", method = RequestMethod.PUT)
    public Result examine(@PathVariable String id) {
        articleService.examine(id);
        return new Result(true,StatusCode.OK,"审核成功");
    }

    @RequestMapping(value = "/thumbup/{id}", method = RequestMethod.PUT)
    public Result updateThumbup(@PathVariable String id) {
        articleService.updateThumbup(id);
        return new Result(true, StatusCode.OK, "点赞成功");
    }





}
