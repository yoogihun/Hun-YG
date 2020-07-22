package org.hello.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hello.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RestCont {
	
	@Autowired BoardService service;
	
	
	@ResponseBody
	@RequestMapping(value = "/chat", method = RequestMethod.GET, produces = { "application/json; charset=utf-8" })
	public String tt() {
		return "리턴";
        
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/chatTest", method = RequestMethod.POST, produces = { "application/json; charset=utf-8" })
	public HashMap<String,Object> testt(@RequestBody HashMap<String,Object> params) throws Exception {
		String a = service.jsonTest();
		//String a = "	{		\"강남구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 강남구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"강남구\",				\"@department\": \"N\",				\"@keyword\": \"중개업소!거래신고위반 행정처분\"			}		},		\"강동구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 강동구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"강동구\",				\"@department\": \"N\",				\"@keyword\": \"부동산중개업!부동산중개업소!부동산민원\"			}		},		\"강서구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 강서구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"강서구\",				\"@department\": \"N\",				\"@keyword\": \"부동산중개사무소\"			}		},		\"관악구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 관악구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"관악구\",				\"@department\": \"N\",				\"@keyword\": \"중개업(s*)지도(s*)단속!중개업소(s*)지도(s*)단속\"			}		},		\"광진구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 광진구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"광진구\",				\"@department\": \"N\",				\"@keyword\": \"부동산중개업\"			}		},		\"구로구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 구로구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"구로구\",				\"@department\": \"N\",				\"@keyword\": \"부동산중개업\"			}		},		\"금천구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 금천구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"금천구\",				\"@department\": \"N\",				\"@keyword\": \"부동산중개업\"			}		},		\"노원구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 노원구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"노원구\",				\"@department\": \"N\",				\"@keyword\": \"부동산중개업\"			}		},		\"동작구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 동작구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"동작구\",				\"@department\": \"N\",				\"@keyword\": \"부동산민원!부동산중개업\"			}		},		\"서대문구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 서대문구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"서대문구\",				\"@department\": \"N\",				\"@keyword\": \"부동산중개업소(s*)관리\"			}		},		\"서초구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 서초구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"서초구\",				\"@department\": \"N\",				\"@keyword\": \"중개업\"			}		},		\"성동구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 성동구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"성동구\",				\"@department\": \"N\",				\"@keyword\": \"부동산중개사무소\"			}		},		\"송파구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 송파구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"송파구\",				\"@department\": \"N\",				\"@keyword\": \"부동산중개업!중개업 행정처분\"			}		},		\"용산구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 용산구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"용산구\",				\"@department\": \"N\",				\"@keyword\": \"중개사무소!부동산중개업\"			}		},		\"중구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 중구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"중구\",				\"@department\": \"N\",				\"@keyword\": \"부동산민원!부동산중개사무소\"			}		},		\"강북구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 강북구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"강북구\",				\"@department\": \"부동산관리팀\",				\"@keyword\": \"N\"			}		},		\"종로구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 종로구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"종로구\",				\"@department\": \"부동산관리팀\",				\"@keyword\": \"N\"			}		},		\"중랑구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 중랑구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"중랑구\",				\"@department\": \"부동산관리팀\",				\"@keyword\": \"N\"			}		},		\"도봉구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 도봉구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"도봉구\",				\"@department\": \"부동산정보과 부동산정책팀\",				\"@keyword\": \"N\"			}		},		\"동대문구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 동대문구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"동대문구\",				\"@department\": \"부동산정보과 부동산행정팀\",				\"@keyword\": \"N\"			}		},		\"양천구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 양천구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"양천구\",				\"@department\": \"부동산정보과 부동산행정팀\",				\"@keyword\": \"N\"			}		},		\"성북구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 성북구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"성북구\",				\"@department\": \"지적행정\",				\"@keyword\": \"N\"			}		},		\"은평구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 은평구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"은평구\",				\"@department\": \"부동산관리팀!부동산팀\",				\"@keyword\": \"N\"			}		},		\"마포구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 마포구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"마포구\",				\"@department\": \"부동산정보과 부동산관리팀\",				\"@keyword\": \"N\"			}		},		\"영등포구\": {			\"answer\": \"문의하신 부동산중개사무소 민원 관련 영등포구 담당부서와 연락처입니다.\",			\"parm\": {				\"@location\": \"영등포구\",				\"@department\": \"부동산정보과 부동산팀\",				\"@keyword\": \"N\"			}		}	}";
		//System.out.println(map);
		//System.out.println(map.get("jsonString"));
		System.out.println(a);
		Map<String,Object> dataMap = new HashMap<String,Object>();
		//String을 Map으로 변환
		dataMap = convertJSONstringToMap(a);
		System.out.println(dataMap);
		//System.out.println(dataMap.get("강남구"));
		
		
		return null;
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
	
	public static Map<String,Object> convertJSONstringToMap(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<String, Object>();
       
        map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
       
        return map;
    }

}
