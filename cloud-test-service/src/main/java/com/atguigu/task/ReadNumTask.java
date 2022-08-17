package com.atguigu.task;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lihw
 * @className ReadNumTask
 * @date 2022-08-17 16:06
 * @description
 */

@Slf4j
@Service
public class ReadNumTask {

    private boolean flag = true;

    private Integer total = 0;

    private List<String> articleUrls;

    private List<String> listUrl = new ArrayList<>();

    {
        //商品
        String url1 = "https://item.taobao.com/item.htm?spm=a1z10.3-c.w4002-24358234104.9.6df267c9Bpy0RB&id=678926529946";
        String url2 = "https://item.taobao.com/item.htm?spm=a1z10.3-c.w4002-24358234104.11.6dfa67c92ecjVs&id=679221562703";
        String url3 = "https://item.taobao.com/item.htm?spm=a1z10.3-c.w4002-24358234104.13.6dfa67c92ecjVs&id=679274646253";
        String url4 = "https://item.taobao.com/item.htm?spm=a1z10.3-c.w4002-24358234104.15.6dfa67c92ecjVs&id=679546451602";
        String url5 = "https://item.taobao.com/item.htm?spm=a1z10.3-c.w4002-24358234104.17.6dfa67c92ecjVs&id=679614307710";
        //进店
        String shopUrl = "https://shop275216655.taobao.com/?spm=2013.1.1000126.d21.cea45ef9bZ4low";

        listUrl.add(url1);
        listUrl.add(url2);
        listUrl.add(url3);
        listUrl.add(url4);
        listUrl.add(url5);
        listUrl.add(shopUrl);
    }


    /*定时访问：n 秒一次*/
    @Async
    @Scheduled(cron = "0/18 * * * * ?")
    public void ScheduledAccess() {
        if (flag) {
            flag = false;
            total++;
            if (articleUrls == null) {
                articleUrls = getAllArticle();
            }
            for (int i = 0; i < articleUrls.size(); i++) {
                String url = articleUrls.get(i);
                try {
                    HttpRequest request = HttpUtil.createGet(url);
                    request.execute();
                    //避免请求太快，睡眠 1 秒
                    Thread.sleep(TimeUnit.SECONDS.toSeconds(1));
                    log.info("请求地址【{}】成功", url);
                } catch (Exception e) {
                    log.error("请求地址【{}】失败,报错信息为：{}", url, e.getMessage());
                }
            }
            System.out.println("第" + (total) + "次定时任务成功");
            flag = true;
        } else {
            System.out.println("上一次任务还没执行完成");
        }
    }


    /*获取所有的文章*/
    private List<String> getAllArticle() {
        List<String> articleUrlList = new ArrayList<>();
        //初始化页数
        int initPage = 1;
        //每页数量
        int page = 100;
        do {
            //拼接文章地址
            String blogArticleUrl = "https://blog.csdn.net/community/home-api/v1/get-business-list?page=" + initPage + "&size=" + page + "&businessType=blog&orderby=&noMore=false&year=&month=&username=qq_36763419";
            HttpRequest request = HttpUtil.createGet(blogArticleUrl);
            HttpResponse response = request.execute();
            JSONObject resJson = JSON.parseObject(response.body());
            JSONArray articleArray = resJson.getJSONObject("data").getJSONArray("list");
            if (articleArray.isEmpty()) {
                break;
            } else {
                for (int i = 0; i < articleArray.size(); i++) {
                    JSONObject article = articleArray.getJSONObject(i);
                    String articleUrl = article.getString("url");
                    articleUrlList.add(articleUrl);
                }
                initPage++;
            }
        } while (true);
        return articleUrlList;
    }


    /*刷访问量淘宝*/
    @Scheduled(cron = "0/3 * * * * ?")
    @Async
    public void taobaoAccess() {

        if (flag) {
            flag = false;
            total++;
            for (int i = 0; i < listUrl.size(); i++) {
                String url = listUrl.get(i);
                try {
                    HttpRequest request = HttpUtil.createGet(url);
                    request.execute();
                    log.info("请求【淘宝】地址【{}】成功", url);
                } catch (Exception e) {
                    log.error("请求【淘宝】地址【{}】失败,报错信息为：{}", url, e.getMessage());
                }
            }
            System.out.println("【淘宝】第" + (total) + "次定时任务成功");
            flag = true;
        } else {
            System.out.println("上一次【淘宝刷访问量】任务还没执行完成");
        }
    }
}
