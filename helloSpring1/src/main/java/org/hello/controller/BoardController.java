package org.hello.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hello.domain.BoardVO;
import org.hello.domain.Criteria;
import org.hello.domain.ImgVO;
import org.hello.domain.PageMaker;
import org.hello.domain.PointVO;
import org.hello.domain.ReplyVO;
import org.hello.domain.Vo;
import org.hello.service.BoardService;
import org.hello.service.PointService;
import org.hello.service.ReplyService;
import org.member.memberVO.MemberVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kubg.utils.UploadFileUtils;

@Controller
@RequestMapping("/board") // url��û�� /board/�� �����ϴ� ���� ���⼭ ó���Ѵ�. ex) board/abc , board/123 board/create
public class BoardController {

	@Inject
	private BoardService service;
	
	@Inject
	private ReplyService replyService;
	
	@Inject
	private PointService pointService;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@RequestMapping(value="/",method=RequestMethod.GET) 
	  public String defaul(ModelAndView mav) throws Exception{
		mav.setViewName("/board/listAll");
	  	  
		return "redirect:/board/listAll";
	  
	  }
		
	@RequestMapping(value="/create",method=RequestMethod.GET) 
	  public void createGET(BoardVO board, MemberVO vo, Model model,HttpServletRequest req,HttpSession session) throws Exception{
		  System.out.println("/board/create �Դϴ�. GET���");

	  	  Object loginInfo = session.getAttribute("member");
	  	  
	  		if(loginInfo == null) 
	  		{
	  			model.addAttribute("msg", false);
	  		}
	  		else {
	  			model.addAttribute("member", loginInfo);
	  		}
		
	  
	  }
	 

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void createPOST(BoardVO board, MemberVO vo, HttpServletRequest req, RedirectAttributes rttr,Model model,HttpSession session) throws Exception {
		System.out.println("/board/create �Դϴ�. POST ���" );	
		service.create(board);
		
		rttr.addFlashAttribute("msg", "����");
		
		//return "redirect:/board/ImgUpload";
	}

	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Criteria cri, BoardVO board, Model model,HttpSession session) throws Exception {
		
		
		System.out.println("��ü��� ������ ��");
		cri.setPerPageNum(10);
		
		int test = service.listCnt();
		List<BoardVO> list = service.listAll(cri);
		PageMaker pageMaker = new PageMaker();
		Object loginInfo = session.getAttribute("member");
		if(loginInfo == null) {
			model.addAttribute("msg", false);
		}
		model.addAttribute("boardList", list);
		model.addAttribute("pageMaker", pageMaker);
	    pageMaker.setCri(cri);
	    pageMaker.setTotalCount(test);
	    
	    System.out.println(list+"��ü����Ʈ");

        
              
   
		
		
	}
	
	@RequestMapping(value = "/listAll", method = RequestMethod.POST)
	public void listAllpost(@RequestParam("perPageNum") Integer perPageNum, Criteria cri, BoardVO board, Model model,HttpSession session) throws Exception {
		System.out.println("��ü��� ������ ����Ʈ");
		System.out.println("gihun----"+perPageNum);
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		int param = perPageNum;
		paramMap.put("perPageNum", param);
		paramMap.put("pageStart", 0);
		
		int test = service.listCnt();
		List<BoardVO> list = service.listAll_Select(paramMap);
		PageMaker pageMaker = new PageMaker();
		
		model.addAttribute("boardList", list);
		model.addAttribute("pageMaker", pageMaker);
	    pageMaker.setCri(cri);
	    pageMaker.setTotalCount(test);
	    System.out.println(test);
	    

        
           
		
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public void readget(ReplyVO vo,@RequestParam("b_no") Integer b_no, Model model,HttpSession session) throws Exception {
		System.out.println("�󼼸��  ������ ��");
		System.out.println(b_no);
		List<ReplyVO> replyVO = replyService.readReply(b_no);
		System.out.println(replyVO);
		Map<String, Object> ckmap = replyService.ReplyCk(vo);
		System.out.println(ckmap);
		model.addAttribute(/* "BoardVO" */ service.read(b_no));
		
		model.addAttribute("reply", replyVO);		
		model.addAttribute("RNOCK", ckmap);
		
		service.updatecnt(b_no);
		
		Object loginInfo = session.getAttribute("member");
		if(loginInfo == null) {
			model.addAttribute("msg", false);
		}
		else 
		{
			model.addAttribute("member", loginInfo);
		}	
		
		model.addAttribute("ImgVo",service.ImgView());
	}

	
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public String readpost(@RequestParam("content") String content,@RequestParam("r_no") Integer r_no, ReplyVO vo,@RequestParam("b_no") Integer b_no, Model model,HttpSession session) throws Exception {
		System.out.println("�󼼸��  ������ ����Ʈ");
		System.out.println("����Ʈ �۹�ȣ"+b_no);
		System.out.println("����Ʈ ��۹�ȣ"+r_no);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("b_no",b_no);
		map.put("r_no",r_no);
		
		
		
		
		model.addAttribute(/* "BoardVO" */ service.read(b_no));
		replyService.createRp(vo);
		
			
		
		/*
		 * int ckmap = replyService.ReplyCk(b_no); System.out.println(ckmap);
		 */
		
		
		service.updatecnt(b_no);
		return "redirect:/board/detail?b_no="+b_no;
		
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void updateGET(@RequestParam("b_no") Integer b_no, Model model,HttpSession session) throws Exception {
		System.out.println("���������� ��");
		/* BoardVO vo = service.read(b_no); */
		model.addAttribute(service.read(b_no));
		Object loginInfo = session.getAttribute("member");
		if(loginInfo == null) {
			model.addAttribute("msg", false);
		}
		
		
		model.addAttribute("ImgVo",service.ImgView());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePOST(BoardVO vo, Model model) throws Exception {
		System.out.println("���������� ����Ʈ");
		//System.out.println(vo.getB_title());
		service.update(vo);	
		return "redirect:/board/listAll";

	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void delete(@RequestParam("b_no") Integer b_no,HttpSession session, Model model) throws Exception {
		System.out.println("���� ��");
		model.addAttribute(service.read(b_no));
		Object loginInfo = session.getAttribute("member");
		if(loginInfo == null) {
			model.addAttribute("msg", false);
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("b_no") Integer b_no) throws Exception {
		System.out.println("���� ����Ʈ");
		service.delete(b_no);
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="/angular", method = RequestMethod.GET)
	public void study() throws Exception{
		System.out.println("접근");
	}
	
	/*
	 * @RequestMapping(value = "/search", method = RequestMethod.GET) public void
	 * searchGet(@RequestParam("b_no") Integer b_no, Model model, HttpSession
	 * session) throws Exception { System.out.println("�� ��ġ");
	 * 
	 * service.search(b_no);
	 * 
	 * Object loginInfo = session.getAttribute("member"); if(loginInfo == null) {
	 * model.addAttribute("msg", false); }
	 * 
	 * }
	 */
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public void searchPost(@RequestParam("b_no") Integer b_no, Model model, HttpSession session) throws Exception {
		System.out.println("����Ʈ ��ġ");
		
		model.addAttribute(service.search(b_no));
		Object loginInfo = session.getAttribute("member");
		if(loginInfo == null) {
			model.addAttribute("msg", false);
		}
		
	}
	
	@RequestMapping(value = "/replyupdate", method = RequestMethod.GET)
	public void replyupdateget(ReplyVO vo, Model model, HttpSession session) throws Exception {
		System.out.println("�� ��ۼ���");
		//System.out.println("�Ѱܹ��� ����� �۹�ȣ"+b_no);
		//System.out.println("�Ѱܹ��� ����� ��ȣ"+r_no);
		model.addAttribute("rep", vo);
		
		Object loginInfo = session.getAttribute("member");
		if(loginInfo == null) {
			model.addAttribute("msg", false);
		}
		
	}
	
	
	
	@RequestMapping(value = "/replyupdate", method = RequestMethod.POST)
	public String replyupdatepost(@RequestParam("r_no") Integer r_no,@RequestParam("b_no") Integer b_no,@RequestParam("content") String content,@RequestParam("writer") String writer,ReplyVO vo, Model model, HttpSession session) throws Exception {
		System.out.println("����Ʈ ��ۼ���");
		System.out.println(b_no);
		System.out.println(r_no);
		System.out.println(writer);
		
		String content_2 = '"'+content+'"';
		vo.setContent(content_2);
		System.out.println(content_2);
		replyService.updateReply(vo);
		
		
		Object loginInfo = session.getAttribute("member");
		if(loginInfo == null) {
			model.addAttribute("msg", false);
		}
		
		return "redirect:/board/detail?b_no="+b_no;
		
	}
	
	@RequestMapping(value = "/javascripts", method = RequestMethod.GET)
	public void test_get(PointVO vo, Model model, HttpSession session) throws Exception {
		
		List test = pointService.point(vo);
		
		List<Map<String,Object>> list = test;
		
		System.out.println(list.get(list.size() -1));
		
		
		
		model.addAttribute("poi", list);
		model.addAttribute("maxpoi",list.get(list.size() -1));
	}
	
	@RequestMapping(value = "/javascripts", method = RequestMethod.POST)
	public void test_post(PointVO vo, Model model, HttpSession session) throws Exception {
		
		pointService.point_reg(vo);
		
	}
	
	@RequestMapping(value = "/point_reg_Popup", method = RequestMethod.GET)
	public void reg_popup_get(PointVO vo, Model model, HttpSession session) throws Exception {
		System.out.println("���˾�");
		
		
	}
	
	@RequestMapping(value = "/point_reg_Popup", method = RequestMethod.POST)
	public void reg_popup_post(PointVO vo, Model model, HttpSession session) throws Exception {
		System.out.println("����Ʈ�˾�");
		Map<String,Object> map = pointService.point_max_id(vo);
		String Id = (String) map.get("point_id");
		System.out.println(Id);
		
		String point_front = Id.substring(0,Id.length()-3);
		System.out.println(point_front);
		String point_number = Id.substring(Id.length()-3);
		System.out.println(point_number);
		int point_number_2 = Integer.parseInt(point_number);
		point_number_2 = point_number_2+1;
		String point_number_2_1 = Integer.toString(point_number_2);
		int len = point_number_2_1.length();
		System.out.println(len+"����");
		if(len == 1) {
			String point_ID = point_front +"00"+ point_number_2;
			vo.setPoint_id(point_ID);	
			pointService.point_reg(vo);
			
		}else if(len == 2){
			String point_ID = point_front +"0"+ point_number_2;
			vo.setPoint_id(point_ID);	
			pointService.point_reg(vo);
		}else {
			String point_ID = point_front + point_number_2;
			vo.setPoint_id(point_ID);	
			pointService.point_reg(vo);
		}
			
	}
	
		@RequestMapping(value = "/point_mod_Popup", method = RequestMethod.GET)
		public void mod_popup_get(PointVO vo, Model model, HttpSession session) throws Exception {
			System.out.println("���˾�");
			
			
		}
		
		@RequestMapping(value = "/point_mod_Popup", method = RequestMethod.POST)
		public void mod_popup_post(PointVO vo, Model model, HttpSession session) throws Exception {
			System.out.println("����Ʈ�˾�");
			System.out.println(vo.getPoint_id());
			String point_id = vo.getPoint_id();
			String point_id_front = point_id.substring(0,point_id.length()-1);
			String point_id_end = point_id.substring(point_id.length()-1);
			int len = point_id_end.length();
			if(len == 1) {
				point_id = point_id_front+"00"+point_id_end;
				vo.setPoint_id(point_id);
				pointService.point_mod(vo);
			}
			
			
			
		
		
		
		
		
		
	}
	
		@RequestMapping(value = "/roadview", method = RequestMethod.GET)
		public void roa_get(PointVO vo, Model model, HttpSession session) throws Exception {
			System.out.println("���˾�");
				
				
		}

		@RequestMapping(value = "/ImgUpload", method = RequestMethod.POST)
		public String upload_post(ImgVO vo,MultipartHttpServletRequest file) throws Exception {
			System.out.println(file.getFile("file").getOriginalFilename());
			System.out.println(file.getFile("file"));
			String imgUploadPath = uploadPath + File.separator + "imgUpload";
			System.out.println(imgUploadPath);
			String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
			String fileName = null;

			if(file.getFile("file") != null) {
			 fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getFile("file").getOriginalFilename(), file.getFile("file").getBytes(), ymdPath); 
			} else {
			 fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
			}
			
			vo.setGdsImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
			vo.setGdsThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
			service.register_img(vo);
			return "redirect:/board/listAll";
			
		}
		
		@RequestMapping(value = "/modifyImg", method = RequestMethod.POST)
		public String Img(ImgVO vo,MultipartHttpServletRequest file,HttpServletRequest req) throws Exception {
			System.out.println(file.getFile("file"));
			System.out.println(req.getParameter("gdsImg"));
			// 새로운 파일이 등록되었는지 확인
			 if(file.getFile("file").getOriginalFilename() != null && file.getFile("file").getOriginalFilename() != "") {
			  // 기존 파일을 삭제
			  new File(uploadPath + req.getParameter("gdsImg")).delete();
			  new File(uploadPath + req.getParameter("gdsThumbImg")).delete();
			  
			  // 새로 첨부한 파일을 등록
			  String imgUploadPath = uploadPath + File.separator + "imgUpload";
			  String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
			  String fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getFile("file").getOriginalFilename(), file.getFile("file").getBytes(), ymdPath);
			  
			  vo.setGdsImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
			  vo.setGdsThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
			  
			 } else {  // 새로운 파일이 등록되지 않았다면
			  // 기존 이미지를 그대로 사용
			  vo.setGdsImg(req.getParameter("gdsImg"));
			  vo.setGdsThumbImg(req.getParameter("gdsThumbImg"));
			  
			 }
			service.ImgModify(vo);
			return "redirect:/board/listAll";
			
		}
		
		@ResponseBody
		@RequestMapping(value = "/test123", method = RequestMethod.POST, headers = {"Accept=application/json"})
		public HashMap<String, Object> test_2(@RequestBody Map<String, Object> params) {
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
		
		@RequestMapping(value = "/formFile", method = RequestMethod.GET)
		public String img_form() throws Exception {
			return "formFile";
		}
		
		
	

		/**
		 * 파일처리 컨트롤러
		 * @param vo
		 * @return
		 */
		@RequestMapping(value="/saveImage")
		public String saveImage(Vo vo) {
			try {
				Map<String, Object> hmap = new HashMap<String, Object>();
				hmap.put("img", vo.getImgFile().getBytes());
				service.saveImage(hmap);	
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/formFile";
		}
		
		
		@RequestMapping(value="/view")
		public String view() {
			return "view";
		}
		
		
		@RequestMapping(value="/getByteImage")
		public ResponseEntity<byte[]> getByteImage() throws Exception {
			Map<String, Object> map = service.getByteImage();
		       byte[] imageContent = (byte[]) map.get("img");
		       System.out.println(imageContent);
		       final HttpHeaders headers = new HttpHeaders();
		       headers.setContentType(org.springframework.http.MediaType.IMAGE_JPEG);
		       
		       return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
		}

		@RequestMapping(value = "/response/update", method = RequestMethod.POST, produces = {
				"application/json; charset=utf-8" })
		public void responseUpdate(HttpServletRequest req) throws Exception {
			MultipartFile single_input_img_form = ((MultipartRequest) req).getFile("single_input_img_data");
			System.out.println(single_input_img_form.getName());
			System.out.println(single_input_img_form.getBytes());
			
			Map<String, Object> hmap = new HashMap<String, Object>();
			
			hmap.put("img", single_input_img_form.getBytes());
			service.saveImage(hmap);
			
			//Map<String, Object> hmap = new HashMap<String, Object>();
			//hmap.put("img", );
			//service.saveImage(hmap);
		}
		
		
		
}
