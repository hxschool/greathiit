import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;

public class StudentCourseTest {

	public static void main(String[] args) throws InvalidFormatException, IOException, InstantiationException, IllegalAccessException {
		File file = new File("D:\\course\\0063.xls");
		ImportExcel ei = new ImportExcel(file, 1, 0);
		Row courseRow = ei.getRow(1);
		Cell courseCell = courseRow.getCell(0);
	     //——  学年度第学期        
		String courseId = courseCell.getStringCellValue();
	    
		System.out.println("------------"+courseId);
		
		Row termRow = ei.getRow(3);
		Cell termCell = termRow.getCell(0);
		String term = termCell.getStringCellValue().replaceAll("\\s*", "").replace("—", "").replace("学年度第学期", "");
		System.out.println("------------"+term);
		ei = new ImportExcel(file, 13, 0);
		List<StudentCourse> list = ei.getDataList(StudentCourse.class);
		for (StudentCourse studentCourse : list){
			//System.out.println(studentCourse.getStudentName());
		}
		String ss = "09";
		
		System.out.println(0b1001);
	}

}
