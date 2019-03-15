package com.tensquare.article.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tensquare.article.mapper.ArticleMapper;
import com.tensquare.article.pojo.Article;
import com.tensquare.article.pojo.ArticleExample;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.util.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
@Transactional
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    IdWorker idWorker;

    @Autowired
    RedisTemplate redisTemplate;

    public List<Article> findAll() {
        ArticleExample example = new ArticleExample();
        return articleMapper.selectByExample(example);
    }

    public void save(Article article) {
        article.setId(idWorker.nextId()+"");

        articleMapper.insert(article);
    }

    public void update(Article article) {
        redisTemplate.delete("article" + article.getId());
        articleMapper.updateByPrimaryKeySelective(article);
    }

    public void delete(String articleId) {
        redisTemplate.delete("article" + articleId);
        articleMapper.deleteByPrimaryKey(articleId);
    }

    public Article findById(String articleId) {
        Article article = (Article) redisTemplate.opsForValue().get("article" + articleId);
        if(article == null){
            article = articleMapper.selectByPrimaryKey(articleId);
            redisTemplate.opsForValue().set("article"+articleId,article,60, TimeUnit.SECONDS);
        }
        return article;
    }

    public List<Article> search(Article article) {
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(article.getId())) {
               criteria.andIdEqualTo(article.getId());
            }
          	if (StringUtils.isNotBlank(article.getColumnid())) {
               criteria.andIdEqualTo(article.getColumnid());
            }
          	if (StringUtils.isNotBlank(article.getUserid())) {
               criteria.andIdEqualTo(article.getUserid());
            }
          	if (StringUtils.isNotBlank(article.getTitle())) {
               criteria.andIdEqualTo(article.getTitle());
            }
          	if (StringUtils.isNotBlank(article.getContent())) {
               criteria.andIdEqualTo(article.getContent());
            }
          	if (StringUtils.isNotBlank(article.getImage())) {
               criteria.andIdEqualTo(article.getImage());
            }
          	if (StringUtils.isNotBlank(article.getIspublic())) {
               criteria.andIdEqualTo(article.getIspublic());
            }
          	if (StringUtils.isNotBlank(article.getIstop())) {
               criteria.andIdEqualTo(article.getIstop());
            }
          	if (StringUtils.isNotBlank(article.getState())) {
               criteria.andIdEqualTo(article.getState());
            }
          	if (StringUtils.isNotBlank(article.getChannelid())) {
               criteria.andIdEqualTo(article.getChannelid());
            }
          	if (StringUtils.isNotBlank(article.getUrl())) {
               criteria.andIdEqualTo(article.getUrl());
            }
          	if (StringUtils.isNotBlank(article.getType())) {
               criteria.andIdEqualTo(article.getType());
            }

        return articleMapper.selectByExample(example);
    }

    public PageResult<Article> searchByPage(Article article, int page, int size) {
        PageHelper.startPage(page-1,size);
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();
                  	if (StringUtils.isNotBlank(article.getId())) {
               criteria.andIdEqualTo(article.getId());
            }
          	if (StringUtils.isNotBlank(article.getColumnid())) {
               criteria.andIdEqualTo(article.getColumnid());
            }
          	if (StringUtils.isNotBlank(article.getUserid())) {
               criteria.andIdEqualTo(article.getUserid());
            }
          	if (StringUtils.isNotBlank(article.getTitle())) {
               criteria.andIdEqualTo(article.getTitle());
            }
          	if (StringUtils.isNotBlank(article.getContent())) {
               criteria.andIdEqualTo(article.getContent());
            }
          	if (StringUtils.isNotBlank(article.getImage())) {
               criteria.andIdEqualTo(article.getImage());
            }
          	if (StringUtils.isNotBlank(article.getIspublic())) {
               criteria.andIdEqualTo(article.getIspublic());
            }
          	if (StringUtils.isNotBlank(article.getIstop())) {
               criteria.andIdEqualTo(article.getIstop());
            }
          	if (StringUtils.isNotBlank(article.getState())) {
               criteria.andIdEqualTo(article.getState());
            }
          	if (StringUtils.isNotBlank(article.getChannelid())) {
               criteria.andIdEqualTo(article.getChannelid());
            }
          	if (StringUtils.isNotBlank(article.getUrl())) {
               criteria.andIdEqualTo(article.getUrl());
            }
          	if (StringUtils.isNotBlank(article.getType())) {
               criteria.andIdEqualTo(article.getType());
            }

        List<Article> articleList = articleMapper.selectByExample(example);
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }

    public void examine(String id) {
        Article article = new Article();
        article.setId(id);
        article.setState("1");
        articleMapper.updateByPrimaryKeySelective(article);
    }

    public void updateThumbup(String id) {
        articleMapper.updateThumbup(id);
    }


}
