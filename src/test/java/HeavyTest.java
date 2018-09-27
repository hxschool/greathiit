
import java.util.Scanner;

public class HeavyTest {
	public static void main(String[] args) {
		System.out.println("请输入课程的数目：");
		Scanner sc = new Scanner(System.in);// 输入课程的数目
		Student students[] = getStudents(sc.nextInt());
		showStudents(students);
	}
	public static Student[] getStudents(int num) {
		Student students[] = new Student[num];// 创建Student对象数组
		for (int i = 0; i < students.length; i++) {
			System.out.println("请输入课程说明：");
			Scanner sc = new Scanner(System.in);// 输入课程的名字
			String str = sc.nextLine();
			System.out.println("是否有等级[y：有/n：没有]");
			if (sc.nextLine().equalsIgnoreCase("y")) {// 输入结果
				System.out.println("请输入等级号[ABCDE]");
				students[i] = new Student(str, sc.nextLine());	// 创建Student对象
			} else {
				students[i] = new Student(str);				// 创建Student对象
			}
		}
		return students;
	}
	public static void showStudents(Student students[]) {			// 显示所有的课程
		double sum = 0.0, num = 0.0;
		for (int i = 0; i < students.length; i++) {
			students[i].show();				// 显示当前的课程
			if (students[i].getGPA() >= 0.0) {
				num++;					// 获取总个数
				sum += students[i].getGPA();	// 获取总数
			}
		}
		System.out.println("GPA值为:" + (sum / num));// 求GPA的值
	}
}
class Student {
	private String name;// 课程的名字
	private String score;// 课程的分数等级
	// 以下是构造方法的重载
	public Student(String name) {			// 带一个参数的构造方法
		this(name, "没有等级编号");
	}
	public Student(String name, String score) {// 带两个参数的构造方法
		this.name = name;
		this.score = score;
	}
	// 显示课程名和等级
	public void show() {
		System.out.println(name + "[等级：" + score + "]");
	}
	// 根据等级获取相应的成绩点数
	public double getGPA() {
		String scores[] = { "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-",
				"D+", "D", "D-" };
		double GPAs[] = { 4.00, 4.0, 3.75, 3.25, 3.0, 2.75, 2.25, 2.0, 1.75,
				1.25, 1.0, 0.75, 0 };
		for (int i = 0; i < scores.length; i++) {
			if (score.equals(scores[i])) {
				return GPAs[i];
			}
		}
		return -1.0;
	}
}