package compiler;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import VO.compiler_VO;

public class java {
	public compiler_VO integer(String code,int in) throws Exception {
		compiler_VO vo = new compiler_VO();
//		파일이름 생성
		Random ra = new Random();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
		Date time = new Date();
		String file_name = "t" + format1.format(time) + ra.nextInt(100) + 1;
//		파일이름 생성
		
//		index java파일 생성
		File file_1 = new File("F:/school_project/project/compiler/" + file_name + ".java");
		FileWriter fw = new FileWriter(file_1, false); 
		String index_code = "public class " + file_name + "{\n"
				+ "public static void main(String[] args) { \n"
				+ file_name+" t = new "+file_name+"();\n"
				+ "t.test(Integer.parseInt(args[0]));\n"
				+ "} "
				+ code
				+ "\n }";
		fw.write(index_code);
		fw.close();
//		index java파일 생성
		
//		class파일 생성
		Process process_1 = Runtime.getRuntime()
				.exec("javac F:/school_project/project/compiler/" + file_name + ".java");
		while (process_1.isAlive()) {
		}
//		class파일 생성
		
//		컴파일 후 결과값 메모장에 저장
		long beforeTime = System.currentTimeMillis();
		Process process_2 = Runtime.getRuntime()
				.exec("cmd /c f: && cd /school_project/project/compiler/ && java -Xmx128M -Xms16M " + file_name + " "+in+" > F:/school_project/project/compiler/" + file_name + ".txt");
		while (process_2.isAlive()) {
		}
		long afterTime = System.currentTimeMillis();
		
//		컴파일 후 결과값 메모장에 저장
		
//		결과값이 들어있는 메모장을 스캔
		File file_3 = new File("F:/school_project/project/compiler/" + file_name + ".txt");
		FileReader filereader = new FileReader(file_3);
		int ch = 0;
		String chnew = "";
		while ((ch = filereader.read()) != -1) {
			chnew += (char) ch;
		}
		filereader.close();
//		결과값이 들어있는 메모장을 스캔

//		java, class, text 파일 삭제
		File file_2 = new File("F:/school_project/project/compiler/" + file_name + ".class");
		file_1.delete();
		file_2.delete();
		file_3.delete();
//		java, class, text 파일 삭제
		
//		결과값 출력
		String ms = (afterTime - beforeTime)/1000+"."+(afterTime - beforeTime)%1000;
		vo.setChnew(chnew);
		vo.setMs(ms);
//		결과값 출력
		return vo;
	}
	
	public compiler_VO string(String code,String in) throws Exception {
		compiler_VO vo = new compiler_VO();
//		파일이름 생성
		Random ra = new Random();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
		Date time = new Date();
		String file_name = "t" + format1.format(time) + ra.nextInt(100) + 1;
//		파일이름 생성
		
//		index java파일 생성
		File file_1 = new File("F:/school_project/project/compiler/" + file_name + ".java");
		FileWriter fw = new FileWriter(file_1, false); 
		String index_code = "public class " + file_name + "{\n"
				+ "public static void main(String[] args) { \n"
				+ file_name+" t = new "+file_name+"();\n"
				+ "t.test(args[0]);\n"
				+ "} "
				+ code
				+ "\n }";
		fw.write(index_code);
		fw.close();
//		index java파일 생성
		
//		class파일 생성
		Process process_1 = Runtime.getRuntime()
				.exec("javac F:/school_project/project/compiler/" + file_name + ".java");
		while (process_1.isAlive()) {
		}
//		class파일 생성
		
//		컴파일 후 결과값 메모장에 저장
		long beforeTime = System.currentTimeMillis();
		Process process_2 = Runtime.getRuntime()
				.exec("cmd /c f: && cd /school_project/project/compiler/ && java -Xmx128M -Xms16M " + file_name + " "+in+" > F:/school_project/project/compiler/" + file_name + ".txt");
		while (process_2.isAlive()) {
		}
		long afterTime = System.currentTimeMillis();
		
//		컴파일 후 결과값 메모장에 저장
		
//		결과값이 들어있는 메모장을 스캔
		File file_3 = new File("F:/school_project/project/compiler/" + file_name + ".txt");
		FileReader filereader = new FileReader(file_3);
		int ch = 0;
		String chnew = "";
		while ((ch = filereader.read()) != -1) {
			chnew += (char) ch;
		}
		filereader.close();
//		결과값이 들어있는 메모장을 스캔

//		java, class, text 파일 삭제
		File file_2 = new File("F:/school_project/project/compiler/" + file_name + ".class");
		file_1.delete();
		file_2.delete();
		file_3.delete();
//		java, class, text 파일 삭제
		
//		결과값 출력
		String ms = (afterTime - beforeTime)/1000+"."+(afterTime - beforeTime)%1000;
		vo.setChnew(chnew);
		vo.setMs(ms);
//		결과값 출력
		return vo;
	}
}
