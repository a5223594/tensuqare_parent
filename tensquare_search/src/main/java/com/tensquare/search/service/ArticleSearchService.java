package com.tensquare.search.service;

import com.tensquare.common.util.IdWorker;
import com.tensquare.search.dao.ArticleSearchDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ArticleSearchService {

    @Autowired
    ArticleSearchDao searchDao;

    @Autowired
    IdWorker idWorker;

    public void add(Article article) {
        article.setId(idWorker.nextId()+"");
        searchDao.save(article);
    }

    public Page<Article> findByKeywords(String keywords, int page, int size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return searchDao.findByTitleOrContentLike(keywords, keywords, request);
    }
}
