package org.hello.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestCont {
	
	@ResponseBody
	@RequestMapping(value = "/chat", method = RequestMethod.GET, produces = { "application/json; charset=utf-8" })
	public String tt() {
		return "리턴";
        
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/chatb", method = RequestMethod.POST, produces = { "application/json; charset=utf-8" })
	public HashMap<String, Object> tes(@RequestBody HashMap<String,Object> params) {
		System.out.println(params);
		
		HashMap<String, Object> resultJson = new HashMap<>();
		List<HashMap<String,Object>> outputs = new ArrayList<>();
		List<HashMap<String,Object>> BCC = new ArrayList<>();
        HashMap<String,Object> template = new HashMap<>();
        HashMap<String, Object> basicCard = new HashMap<>();
        HashMap<String, Object> basicCard_Detail = new HashMap<>();
        HashMap<String, Object> thumbnail_Detail = new HashMap<>();
 
        /* 발화 처리 부분 * */
        //HashMap<String,Object> userRequest =  (HashMap<String,Object>)params.get("userRequest");
        //String utter = userRequest.get("utterance").toString().replace("\n","");

        String rtnStr = "";
       
        /* 발화 처리 끝*/
        
        
        thumbnail_Detail.put("imageUrl","http://k.kakaocdn.net/dn/83BvP/bl20duRC1Q1/lj3JUcmrzC53YIjNDkqbWK/i_6piz1p.jpg");
        
        /*베이직카드 제목 내용 이미지 만드는곳 */
        basicCard_Detail.put("title", rtnStr);
        basicCard_Detail.put("description", "뭐가있지");
        basicCard_Detail.put("thumbnail",thumbnail_Detail);
    
       
        
       
        /*버튼 세팅*/
        List<HashMap<String,Object>> buttons = new ArrayList<>();
        HashMap<String, Object> button_detail = new HashMap<>();
        button_detail.put("action", "message");
        button_detail.put( "label", "열어보기");
        button_detail.put( "messageText", "짜잔! 우리가 찾던 보물입니다");
        buttons.add(button_detail);   
        basicCard_Detail.put("buttons", buttons);
        
        /*퀵리플라이 세팅*/
        List<HashMap<String,Object>> quickReplies = new ArrayList<>();
        HashMap<String,Object> quickRepl = new HashMap<>();
        quickRepl.put("action","message");
        quickRepl.put("label","손흥민");
        quickRepl.put("messageText","손흥민");
        quickReplies.add(quickRepl);
        
        
        /*베이직 카드 여러개 삽입하는 부분*/
        List<HashMap<String,Object>> items = new ArrayList<>();
        items.add(basicCard_Detail);
        items.add(basicCard_Detail);
        
        /*아웃풋에 넣을 카드리스트, 타입을 만드는곳*/
        HashMap<String,Object> caro = new HashMap();
        caro.put("type", "basicCard");
        caro.put("items", items);
        
        basicCard.put("carousel",caro);
        outputs.add(basicCard);

       
        template.put("outputs",outputs);
        template.put("quickReplies",quickReplies);
      //  template.put("buttons",buttons);

        resultJson.put("version","2.0");
        resultJson.put("template",template);
		System.out.println(resultJson);
		return resultJson;
        
		
	}

}
