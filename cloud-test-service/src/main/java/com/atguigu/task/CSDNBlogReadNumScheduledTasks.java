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
public class CSDNBlogReadNumScheduledTasks {

    private boolean flag = true;

    private Integer total = 0;

    private List<String> articleUrls;

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
        int page = 1;
        //每页数量
        int pageSize = 100;
        //账号，只需要修改账号即可
        String username = "qq_36763419";

        //业务类型
        String businessType = "blog";
        //地址
        String url = "https://blog.csdn.net/community/home-api/v1/get-business-list";
        do {
            //拼接文章地址
            String blogArticleUrl = url + "?page=" + page + "&size=" + pageSize + "&businessType=" + businessType + "&username=" + username;
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
                page++;
            }
        } while (true);
        return articleUrlList;
    }
}
