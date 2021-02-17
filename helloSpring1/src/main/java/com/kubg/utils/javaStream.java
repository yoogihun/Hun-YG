package com.kubg.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class javaStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> list = new ArrayList();
		list.add("라");
		list.add("다");
		list.add("나");
		list.add("가");
		
		String array[] = {"유기훈","이지현","양꼬치","냠냠","히히히"};

		Stream<String> stream2 = Arrays.stream(array);
		stream2.forEach(e -> System.out.println(e));

		stream2.close();

		stream2=Arrays.stream(array);

		stream2.forEach(name ->{ //이 괄호는 accept 메소드의 구현부

			System.out.println("이름 : "+name);

			System.out.println("집에 갈수있는가? : "+name+"못감");
			

			System.out.println("성 : "+name.substring(0, 1));
			
			
		});
		Stream<String> stream = list.stream();

		stream.sorted((s1,s2)->s2.length()-s1.length()).forEach(s -> System.out.println(s)); 
		list.stream().sorted((s1, s2) -> s2.length() - s1.length()).forEach(item -> System.out.println(item));
		
		List<Integer> nums = Arrays.asList(10,20,41,54,32,85,57,54,34,84,10,20,20,10);
		
	
		
		nums.stream().filter(num-> num%4==0).forEach(num->{
			System.out.println("4의 배수만 들어옴 : "+num);
			if(num%10 ==0) {
				System.out.println("남은것: "+num);
			}
		});

	}

}
