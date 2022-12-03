package guess;

import java.util.Random;
import java.util.Scanner;
/**
 * 编写程序，实现控制台的猜数字游戏。 游戏运行时产生一个0－1000之间的随机整数，要求用户从控制台输入数字，
 * 若输入的数字比产生的数字小，则输出：“太小了，再大一点！”； 若输入的数字比产生的数字大，则输出：“太大了，再小一点！”，
 * 若输入的数字和产生的数字相等，则输出：“恭喜你猜对了！”然后退出程序； 若用户猜了10次还未猜对，则输出：“你太笨了，下次再来吧！”然后退出程序。
 */
public class Guess {
	public void guess() {
		Random r = new Random();
		int i = r.nextInt(1000); // 产生0~1000的随机整数
		System.out.println("请输入一个0－1000之间的整数：");
		int count = 10;
		while (--count >= 0) {
			Scanner p = new Scanner(System.in);// 键盘录入
			String s = p.nextLine();
			// 异常控制，防止用户输入的不是数字
			try {
				int num = Integer.parseInt(s);
				if (num == i) {
					System.out.println("恭喜你猜对了！");
					break;
				} else if (num < i) {
					System.out.println("太小了，再大一点！");
				} else {
					System.out.println("太大了，再小一点！");
				}
			} catch (Exception e) {
				System.out.println("输入有误，请输入数字！");
			}
		}
		if (count <= 0) {
			System.out.println("你太笨了，下次再来吧！");
		}
	}
}
