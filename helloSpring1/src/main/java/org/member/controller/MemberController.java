package org.member.controller;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.member.memberVO.MemberVO;
import org.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/member") // url��û�� /member�� �����ϴ� ���� ���⼭ ó���Ѵ�
public class MemberController {

		@Inject
		private MemberService service;	
		
		@RequestMapping(value="/register",method=RequestMethod.GET) 
		  public void getReg(MemberVO vo) throws Exception{
		  System.out.println("/board/create �Դϴ�. GET���");
		  
		  }
		 
		@RequestMapping(value = "/register", method = RequestMethod.POST)
		public String postReg(MemberVO vo) throws Exception {
		
			
				
			
			try {
			service.register(vo);
		}catch (Exception e){
			
			System.out.println("�ߺ��� ���̵��Դϴ�.");
			return "redirect:/member/login";
		}
			
			
			return "redirect:/member/login";
			
		}
		
		@RequestMapping(value = "/login", method = RequestMethod.POST)
		public String loginpost(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
			
			HttpSession session = req.getSession();
			MemberVO login = service.login(vo); 
			System.out.println(login);
			
			if(login == null) {
				session.setAttribute("member", null);
				rttr.addFlashAttribute("msg", false);
			}
			
			else {
				session.setAttribute("member", login);
			}
			
			return "redirect:/member/login";
			
		}
		
		@RequestMapping(value = "/login", method = RequestMethod.GET)
		public void loginGET(MemberVO vo) throws Exception {
			
			
			
		}
		
		@RequestMapping(value = "/modify", method = RequestMethod.GET)
		public void modifyGET() throws Exception {
			
				
		}
		
		@RequestMapping(value = "/modify", method = RequestMethod.POST)
		public String modifypost(MemberVO vo, HttpSession session) throws Exception {
			System.out.println("��������");
			
			service.modify(vo);
			
			
			return "redirect:/member/login";
			
		}
			
		@RequestMapping(value = "/logout", method = RequestMethod.GET)
		public String logout(HttpSession session) throws Exception {
			session.invalidate();
			
			return "redirect:/member/login";
				
		}	
		
		
}
