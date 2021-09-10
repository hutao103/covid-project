package com.htht.pro;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	static final char[] ch = {'a','b','c','d','e','f','g','h','i','j','k','l',
			'm','n','o','p','q','r','s','t','u','v','w','x','y','z',
			'0','1','2','3','4','5','6','7','8','9'};
	
	public static String getId(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(new Date());
		String id = getRandom(10)+time+getRandom(10);
		return id;
	}
	
	public static String getRandom(int num){
		String s = "";
		for(int i=0;i< num;i++){
			int c = (int) Math.floor(Math.random()*36);
			s += String.valueOf(ch[c]);
		}
		
		return s;
	}
	
	public static String getRandomVirus(){
		final char[] ch2 = {'0','1','0','1','0','1','0','1','0','1','0','1','0','1','0','1','0','1','0','1','0','1','0','1'};
		int c = (int) Math.floor(Math.random()*24);
		String s = String.valueOf(ch2[c]);
		return s;
	}
	
}
