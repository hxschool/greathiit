import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.ZipUtils;

public class ZipTest {

	public static void main(String[] args) throws IOException {
		String studentNumber = "201401010405";
		String folder = FileUtils.getTempDirectoryPath();
		File srcDir = new File("C:\\Users\\Team\\git\\jeesite\\src\\test\\java\\frp");
		File destDir = new File(folder,studentNumber);
		if(!destDir.exists()) {
			destDir.mkdirs();
		}
		
		FileUtils.copyDirectory(srcDir, destDir);
		BufferedReader br = new BufferedReader(new FileReader(new File(destDir,"frpc.ini")));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(destDir,studentNumber.concat("_conf.ini"))));
		String s = null;
		while((s=br.readLine())!=null) {
			s = s.replace("zhaojunfei", studentNumber);
			bw.write(s);
			bw.write("\r\n");
		}
		br.close();
		bw.flush();
		bw.close();
		BufferedWriter bat = new BufferedWriter(new FileWriter(new File(destDir,"start.bat")));
		bat.write("frpc -c ".concat(studentNumber.concat("_conf.ini")));
		bat.flush();
		bat.close();
		ZipUtils.zipDirectory(destDir, new File("d:/frp",studentNumber.concat(".zip")));
		
		FileUtils.deleteDirectory(destDir);
        
	}
	
}
