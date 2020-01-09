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
import org.hello.domain.ReplyVO;
import org.hello.service.BoardService;
import org.hello.service.ReplyService;
import org.member.memberVO.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board") // url��û�� /board/�� �����ϴ� ���� ���⼭ ó���Ѵ�. ex) board/abc , board/123 board/create
public class BoardController {
	
	
	
	
	@Inject
	private BoardService service;
	
	@Inject
	private ReplyService replyService;
	
	
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
	public String createPOST(BoardVO board, MemberVO vo, HttpServletRequest req, RedirectAttributes rttr,Model model,HttpSession session) throws Exception {
		System.out.println("/board/create �Դϴ�. POST ���" );	
		service.create(board);
		
		rttr.addFlashAttribute("msg", "����");
		
		return "redirect:/board/listAll?page=1";
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
	public void test(ReplyVO vo, Model model, HttpSession session) throws Exception {
		
		
		
		
	}
	
	
	
	
}
