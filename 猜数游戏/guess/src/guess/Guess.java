package guess;

import java.util.Random;
import java.util.Scanner;
/**
 * ��д����ʵ�ֿ���̨�Ĳ�������Ϸ�� ��Ϸ����ʱ����һ��0��1000֮������������Ҫ���û��ӿ���̨�������֣�
 * ����������ֱȲ���������С�����������̫С�ˣ��ٴ�һ�㣡���� ����������ֱȲ��������ִ����������̫���ˣ���Сһ�㣡����
 * ����������ֺͲ�����������ȣ������������ϲ��¶��ˣ���Ȼ���˳����� ���û�����10�λ�δ�¶ԣ������������̫���ˣ��´������ɣ���Ȼ���˳�����
 */
public class Guess {
	public void guess() {
		Random r = new Random();
		int i = r.nextInt(1000); // ����0~1000���������
		System.out.println("������һ��0��1000֮���������");
		int count = 10;
		while (--count >= 0) {
			Scanner p = new Scanner(System.in);// ����¼��
			String s = p.nextLine();
			// �쳣���ƣ���ֹ�û�����Ĳ�������
			try {
				int num = Integer.parseInt(s);
				if (num == i) {
					System.out.println("��ϲ��¶��ˣ�");
					break;
				} else if (num < i) {
					System.out.println("̫С�ˣ��ٴ�һ�㣡");
				} else {
					System.out.println("̫���ˣ���Сһ�㣡");
				}
			} catch (Exception e) {
				System.out.println("�����������������֣�");
			}
		}
		if (count <= 0) {
			System.out.println("��̫���ˣ��´������ɣ�");
		}
	}
}
