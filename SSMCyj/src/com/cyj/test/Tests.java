package com.cyj.test;

import javax.enterprise.inject.New;

public class Tests {
	
	public static void main(String[] args) {
		System.out.println(new Tests().test());
		/* 在这里调用下面的text方法
		 * 根据结果可以看到先执行了func1方法输出func1
		 * 然后执行finally可是并没有发生异常说明该方法
		 * 总是执行
		 * 并且finally的代码是在return语句后执行的
		 */
	}
	
	int test(){
		try {
			return func1();
		} finally {
			return func2();
		}
	}
	
	int func1(){
		System.out.println("func1");
		return 1;
	}
	
	int func2(){
		System.out.println("func2");
		return 2;
	}
	
}
