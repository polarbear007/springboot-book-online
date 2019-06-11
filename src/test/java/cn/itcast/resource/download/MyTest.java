package cn.itcast.resource.download;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class MyTest {
	@Test
	public void testReg() {
		String str = "1479124685@163.com";
		System.out.println(str.matches("^\\w+@\\w+(\\.\\w+)+$"));
	}
	
	@Test
	public void testReg2() {
		String str = "12345678901";
		System.out.println(str.matches("^\\d{11}$"));
	}
	
	// 8-20位，不能全是数字，不能全是字母或下划线
	@Test
	public void testReg3() {
		String str = "adfljkl123132";
		System.out.println(str.matches("(?!\\d+$)(?![a-zA-Z_]+$)\\w{8,20}$"));
	}
	
	@Test
	public void test1() {
		String str = "abc123";
		System.out.println(str.matches("\\w+"));
	}
	
	@Test
	public void test2() {
		String str = "ad562sflksdjfl1235324kadsj123fla132jfsdfl";
		System.out.println(str.replaceAll("\\d", "*"));
	}
	
	@Test
	public void test3() {
		String str = "ad562sflksdjfl1235324kadsj123fla132jfsdfl";
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(str);
		while(m.find()) {
			System.out.println(m.group());
		}
	}
	
	@Test
	public void test4() {
		String str = "123+456=789";
		Pattern p = Pattern.compile("(\\d+)|([\\+\\-\\*\\=/])");
		Matcher m = p.matcher(str);
		while(m.find()) {
			if(m.group(1) != null) {
				System.out.println("获取到数字：" + m.group(1));
			}else if(m.group(2) != null) {
				System.out.println("获取到符号：" + m.group(2));
			}
		}
	}
	
	@Test
	public void test5() {
		String str = "123+456=789";
		Pattern p = Pattern.compile("(?<num>\\d+)|(?<operator>[\\+\\-\\*\\=/])");
		Matcher m = p.matcher(str);
		while(m.find()) {
			if(m.group("num") != null) {
				System.out.println("获取到数字：" + m.group("num"));
			}else if(m.group("operator") != null) {
				System.out.println("获取到符号：" + m.group("operator"));
			}
		}
	}
	
	@Test
	public void test6() {
		String str = "abC";
		Pattern P = Pattern.compile("ABC", Pattern.CASE_INSENSITIVE);
		Matcher m = P.matcher(str);
		System.out.println(m.matches());
	}
	
	@Test
	public void test7() throws Exception {
		File file = new File("abc.txt");
		Scanner sc = new Scanner(file);
		while(sc.findInLine("(\\d+)|([\\+\\-\\*\\=/])") != null) {
			if(sc.match().group(1) != null) {
				System.out.println("数字：" + sc.match().group(1));
			}else if(sc.match().group(2) != null) {
				System.out.println("符号：" + sc.match().group(2));
			}
		}
		sc.close();
	}
	
	@Test
	public void test8() throws Exception {
		File file = new File("def.txt");
		Scanner sc = new Scanner(file);
		// 以数字作为分隔符，取出所有的非数字字符
		sc.useDelimiter("\\d+");
		while(sc.hasNext()) {
			System.out.println(sc.next());
		}
		sc.close();
	}
	
	@Test
	public void test9() throws Exception {
		File file = new File("abc.txt");
		Scanner sc = new Scanner(file);
		String str = null;
		while((str = sc.findInLine("\\d+")) != null) {
			System.out.println(str);
		}
		sc.close();
	}
	
	@Test
	public void test10() throws Exception {
		File file = new File("def.txt");
		Scanner sc = new Scanner(file);
		String line = null;
		while(sc.hasNextLine()) {
			while((line = sc.findInLine("\\d+")) != null) {
				System.out.println(line);
			}
			// 这个方法不仅可以读取一行的数据，还可以让指针移动到下一行
			sc.nextLine();
		}
		sc.close();
	}
}
