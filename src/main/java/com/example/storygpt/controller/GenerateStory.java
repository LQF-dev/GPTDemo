package com.example.storygpt.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.storygpt.dto.Result;
import io.github.asleepyfish.util.OpenAiUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Luo qinfeng
 * @Date: 20Cr9/2 16:09
 * @Description:
 */
@RestController
@CrossOrigin
public class GenerateStory {

    private final String BASEWORD="我在学习雅思词汇，请给我生成一个大约30个词的英文小故事，包含英文故事本身，中文翻译，词汇表。请先给我英文故事，然后是对应的中文翻译，并且故事中的英文词汇和对应的中文词汇需要加粗显示，最后请给我词汇表，该表以表格形式展示，表格包括英文，中文翻译，词性，例句。这是我需要的单词：";


    @GetMapping("/generateStoryByWords")
     public JSONObject generateStoryByWords(@RequestParam String words){

        JSONObject returnObject = new JSONObject();

        try {
            List<String> contentFromGPT = OpenAiUtils.createChatCompletion(BASEWORD+words);

            System.out.println("result" + new Result(400,contentFromGPT.get(0) ));
            returnObject.put("result", new Result(400,contentFromGPT.get(0)));
            return returnObject;

        }catch (Exception e){
            returnObject.put("result", new Result(200,"charGPT获取结果失败！"));
            return returnObject;
        }

    }

}
