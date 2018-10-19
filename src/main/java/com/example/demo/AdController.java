package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jodd.util.StringUtil;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by Admin on 2018/10/19.
 */
@RestController
public class AdController {
    RestTemplate restTemplate = new RestTemplate();

    /**
     * 获取广告信息
     * @param reqMap
     * @return
     */
    @RequestMapping(value="/ad",method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=UTF-8")
    public String getAdInfo(@RequestBody Map<String,Object> reqMap){
        String mac = reqMap.get("mac").toString()== "" ? "\"\"" : "\""+reqMap.get("mac").toString()+"\"";//mac地址
        String ua = reqMap.get("ua").toString()== "" ? "\"\"" : "\""+reqMap.get("ua").toString()+"\"";//ua信息
        String pnumber = reqMap.get("pnumber").toString() == "" ? "\"\"" : reqMap.get("pnumber").toString();
        String id = reqMap.get("id").toString();//广告id,如果等于0，表示是贴片，如果等于1，表示是暂停
        if (StringUtil.isNotEmpty(id)) {
            if(id.equals("0")) {
                id="0DAA898E58F35C1263D77E71B87E699D";
            }else {
                id="14F7FD39E4C0E33D681ECD75F7B9B127";
            }
        } else {
            CheckObject c = new CheckObject();
            c.setCode("101");
            c.setMessage("广告ID不能为空!");
            String json = JSON.toJSON(c).toString();
            return json;
        }
//        HashMap<String, String> map = new HashMap<>();
//        map.put("authid","miguauthid");
//        map.put("token","migutoken");
//        map.put("device", "{\"carrier\": 0,\"connectiontype\": 0,\"devicetype\": 1,\"did\": \"other\", \"didha\": 0, \"dpid\": \"other\",\"dpidha\": 0,\"mac\":\"\",\"pnumber\":\"15000128153\",\"ua\":\"\",\"networktraffictype\":3}");
//        map.put("imp", "[{\"actiontype\":1,\"id\":\"14F7FD39E4C0E33D681ECD75F7B9B127\"}]");
//        map.put("app", "{\"networktraffictype\":3}");


//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("authid","miguauthid");
//        jsonObj.put("token","migutoken");
//        jsonObj.put("device", "{\"carrier\": 0,\"connectiontype\": 0,\"devicetype\": 1,\"did\": \"other\", \"didha\": 0, \"dpid\": \"other\",\"dpidha\": 0,\"mac\":\"\",\"pnumber\":\"15000128153\",\"ua\":\"\",\"networktraffictype\":3}");
//        jsonObj.put("imp", "[{\"actiontype\":1,\"id\":\"14F7FD39E4C0E33D681ECD75F7B9B127\"}]");
//        jsonObj.put("app", "{\"networktraffictype\":3}");

        String reqJsonStr = "{\"authid\":\"miguauthid\", \"token\":\"migutoken\",\"device\":{\"carrier\": 0,\"connectiontype\": 0,\"devicetype\": 1,\"did\": \"other\", \"didha\": 0, \"dpid\": \"other\",\"dpidha\": 0,\"mac\":"+mac+",\"pnumber\":"+pnumber+",\"ua\":"+ua+",\"networktraffictype\":3}, \"imp\":[{\"actiontype\":1,\"id\":"+id+"}],\"app\":{\"networktraffictype\":3}}";
        HttpHeaders headers = new HttpHeaders(); headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(reqJsonStr,headers);
        ResponseEntity<Map> resp = restTemplate.exchange("http://ggx.cmvideo.cn/request/api10", HttpMethod.POST, entity, Map.class);
        JSONObject responseBody  = new JSONObject(resp.getBody());
        return responseBody.toString();

    }
}
