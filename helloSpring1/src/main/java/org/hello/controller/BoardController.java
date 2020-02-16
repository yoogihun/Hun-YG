package org.hello.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hello.domain.BoardVO;
import org.hello.domain.Criteria;
import org.hello.domain.PageMaker;
import org.hello.domain.PointVO;
import org.hello.domain.ReplyVO;
import org.hello.service.BoardService;
import org.hello.service.PointService;
import org.hello.service.ReplyService;
import org.member.memberVO.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board") // url요청이 /board/로 시작하는 것은 여기서 처리한다. ex) board/abc , board/123 board/create
public class BoardController {
	
	
	
	
	@Inject
	private BoardService service;
	
	@Inject
	private ReplyService replyService;
	
	@Inject
	private PointService pointService;
	
	
	@RequestMapping(value="/create",method=RequestMethod.GET) 
	  public void createGET(BoardVO board, MemberVO vo, Model model,HttpServletRequest req,HttpSession session) throws Exception{
		  System.out.println("/board/create 입니다. GET방식");
	  	  
		  
		  
		  
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
	public String createPOST(BoardVO board, MemberVO vo, HttpServletRequest req, RedirectAttributes rttr,Model model,HttpSession session) throws Exception {
		System.out.println("/board/create 입니다. POST 방식" );	
		service.create(board);
		
		rttr.addFlashAttribute("msg", "성공");
		
		return "redirect:/board/listAll?page=1";
	}

	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Criteria cri, BoardVO board, Model model,HttpSession session) throws Exception {
		
		
		System.out.println("전체목록 페이지 겟");
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
	    
	    System.out.println(list+"전체리스트");

        
              
   
		
		
	}
	
	@RequestMapping(value = "/listAll", method = RequestMethod.POST)
	public void listAllpost(@RequestParam("perPageNum") Integer perPageNum, Criteria cri, BoardVO board, Model model,HttpSession session) throws Exception {
		System.out.println("전체목록 페이지 포스트");
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
		System.out.println("상세목록  페이지 겟");
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
		
	}

	
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public String readpost(@RequestParam("content") String content,@RequestParam("r_no") Integer r_no, ReplyVO vo,@RequestParam("b_no") Integer b_no, Model model,HttpSession session) throws Exception {
		System.out.println("상세목록  페이지 포스트");
		System.out.println("포스트 글번호"+b_no);
		System.out.println("포스트 댓글번호"+r_no);
		
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
		System.out.println("수정페이지 겟");
		/* BoardVO vo = service.read(b_no); */
		model.addAttribute(service.read(b_no));
		Object loginInfo = session.getAttribute("member");
		if(loginInfo == null) {
			model.addAttribute("msg", false);
		}
		
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePOST(BoardVO vo, Model model) throws Exception {
		System.out.println("수정페이지 포스트");
		//System.out.println(vo.getB_title());
		service.update(vo);	
		return "redirect:/board/listAll";

	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void delete(@RequestParam("b_no") Integer b_no,HttpSession session, Model model) throws Exception {
		System.out.println("삭제 겟");
		model.addAttribute(service.read(b_no));
		Object loginInfo = session.getAttribute("member");
		if(loginInfo == null) {
			model.addAttribute("msg", false);
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("b_no") Integer b_no) throws Exception {
		System.out.println("삭제 포스트");
		service.delete(b_no);
		return "redirect:/board/listAll";
	}
	
	/*
	 * @RequestMapping(value = "/search", method = RequestMethod.GET) public void
	 * searchGet(@RequestParam("b_no") Integer b_no, Model model, HttpSession
	 * session) throws Exception { System.out.println("겟 서치");
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
		System.out.println("포스트 서치");
		
		model.addAttribute(service.search(b_no));
		Object loginInfo = session.getAttribute("member");
		if(loginInfo == null) {
			model.addAttribute("msg", false);
		}
		
	}
	
	@RequestMapping(value = "/replyupdate", method = RequestMethod.GET)
	public void replyupdateget(ReplyVO vo, Model model, HttpSession session) throws Exception {
		System.out.println("겟 댓글수정");
		//System.out.println("넘겨받은 댓글의 글번호"+b_no);
		//System.out.println("넘겨받은 댓글의 번호"+r_no);
		model.addAttribute("rep", vo);
		
		Object loginInfo = session.getAttribute("member");
		if(loginInfo == null) {
			model.addAttribute("msg", false);
		}
		
	}
	
	
	
	@RequestMapping(value = "/replyupdate", method = RequestMethod.POST)
	public String replyupdatepost(@RequestParam("r_no") Integer r_no,@RequestParam("b_no") Integer b_no,@RequestParam("content") String content,@RequestParam("writer") String writer,ReplyVO vo, Model model, HttpSession session) throws Exception {
		System.out.println("포스트 댓글수정");
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
		System.out.println("겟팝업");
		
		
	}
	
	@RequestMapping(value = "/point_reg_Popup", method = RequestMethod.POST)
	public void reg_popup_post(PointVO vo, Model model, HttpSession session) throws Exception {
		System.out.println("포스트팝업");
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
		System.out.println(len+"길이");
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
			System.out.println("겟팝업");
			
			
		}
		
		@RequestMapping(value = "/point_mod_Popup", method = RequestMethod.POST)
		public void mod_popup_post(PointVO vo, Model model, HttpSession session) throws Exception {
			System.out.println("포스트팝업");
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
			System.out.println("겟팝업");
				
				
		}
}
