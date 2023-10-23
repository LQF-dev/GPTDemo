package com.example.storygpt.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.storygpt.config.TransProperties;
import com.example.storygpt.dto.Result;
import com.example.storygpt.utils.AuthV3Util;
import com.example.storygpt.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Luo qinfeng
 * @Date: 2023/9/3 09:52
 * @Description:
 */
@RestController
@CrossOrigin
public class GenerateWordExplain {

    private final static String BOLD= "**";
    private final static String NEWLINE= "\n\n";
    @Autowired
    TransProperties transProperties;

    @GetMapping("/generateWordExplanation")
    public JSONObject generateWordExplanation(@RequestParam String word){
        JSONObject returnObject = new JSONObject();

        // 添加鉴权相关参数
        try {
            // 添加请求参数
            Map<String, String[]> params = createRequestParams(word,transProperties.getFrom(),transProperties.getTo());
            AuthV3Util.addAuthParams(transProperties.getAppKey(), transProperties.getAppSecret(), params);
            // 请求api服务
            byte[] result = HttpUtil.doPost("https://openapi.youdao.com/api", null, params, "application/json");
            // 打印返回结果
            if (result != null) {

                JSONObject returnJsonObject = JSONObject.parseObject(new String(result, StandardCharsets.UTF_8));

                JSONArray jsonArray = returnJsonObject.getJSONObject("basic").getJSONArray("explains");
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i< jsonArray.size(); i++) {
                    builder.append(BOLD+(String) jsonArray.get(i)+BOLD+NEWLINE);
                }

                returnObject.put("result",new Result(400,builder.toString()));
                return returnObject;
            }

        } catch (Exception e) {
             returnObject.put("result", new Result(400,"获取单词解析结果失败！请输入存在的单词！"));
             return returnObject;
        }

        returnObject.put("result", new Result(400,"获取单词API请求失败，检查网络！"));
        return returnObject;
    }


    private  Map<String, String[]> createRequestParams(String q,String from,String to) {
        /*
         * note: 将下列变量替换为需要请求的参数
         * 取值参考文档: https://ai.youdao.com/DOCSIRMA/html/%E8%87%AA%E7%84%B6%E8%AF%AD%E8%A8%80%E7%BF%BB%E8%AF%91/API%E6%96%87%E6%A1%A3/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1-API%E6%96%87%E6%A1%A3.html
         */

//        String vocabId = "您的用户词表ID";

        return new HashMap<String, String[]>() {{
            put("q", new String[]{q});
            put("from", new String[]{from});
            put("to", new String[]{to});
        }};
    }

}
