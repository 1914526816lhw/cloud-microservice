package com.atguigu.task;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author lihw
 * @className ReadNumTask
 * @date 2022-08-17 16:06
 * @description
 */

@Service
public class ReadNumTask {

    private boolean flag = true;

    private Integer total = 0;

    private List<String> articleUrls;


    /*定时访问：n 秒一次*/
    @Scheduled(cron = "0/20 * * * * ?")
    public void ScheduledAccess() {
        if (flag) {
            flag = false;
            total++;
            if (articleUrls == null) {
                articleUrls = getAllArticle();
            }
            for (int i = 0; i < articleUrls.size(); i++) {
                String url = articleUrls.get(i);
                HttpRequest request = HttpUtil.createGet(url);
                request.execute();
            }
            System.out.println("第" + (total) + "次调用成功");
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
            if (articleArray.size() == 0) {
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
}
