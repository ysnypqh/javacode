package wuziqi;

import java.util.Scanner;

public class WuZiQi {
	// �������̵Ĵ�С
	static int SIZE = 15;
	// �ö�ά�����ʾ����
	static String[][] BROAD = new String[SIZE][SIZE];

	// ��ʼ������
	public static void init() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				BROAD[i][j] = "ʮ";
			}
		}
	}

	// �滭����
	public static void paintBroad() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				// ���һ�У���Ҫ����
				System.out.print(BROAD[i][j]);
			}
			// ���������һ��
			System.out.println();
		}
	}

	// �ж���������ĺϷ���
	public static boolean isLegal(int i, int j) {
		// ���벻�ܳ������̷�Χ
		if (i > 15 || i < 0 || j > 15 || j < 0) {
			return false;
		}
		// ����λ�ñ����ǻ�δ����
		if (BROAD[i - 1][j - 1] != "ʮ") {
			return false;
		}
		return true;
	}

	// ���������ģ���������
	public static int[] computer() {
		int[] coordinate = new int[2];
		coordinate[0] = (int) (Math.random() * 15 + 1);
		coordinate[1] = (int) (Math.random() * 15 + 1);
		while (!isLegal(coordinate[0], coordinate[1])) {
			coordinate[0] = (int) (Math.random() * 15 + 1);
			coordinate[1] = (int) (Math.random() * 15 + 1);
		}
		return coordinate;
	}

	/**
	 * �ж���Ӯ ����ֵ�� 1:����Ӯ�� 0:����Ӯ�� -1��δ��ʤ��
	 */
	public static int isWin() {
		// �����ж�
		// �����ж�
		// ��б���ж�
		// б���ж�
		for (int i = 0; i < SIZE - 4; i++) {
			for (int j = 0; j < SIZE - 4; j++) {
				if (BROAD[i][j] == "��" && BROAD[i + 1][j + 1] == "��"
						&& BROAD[i + 2][j + 2] == "��"
						&& BROAD[i + 3][j + 3] == "��"
						&& BROAD[i + 4][j + 4] == "��")
					return 1;
				else if (BROAD[i][j] == "��" && BROAD[i + 1][j + 1] == "��"
						&& BROAD[i + 2][j + 2] == "��"
						&& BROAD[i + 3][j + 3] == "��"
						&& BROAD[i + 4][j + 4] == "��")
					return 0;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		init();
		paintBroad();
		do {
			System.out.println("���������ӵ�����:");
			Scanner sn = new Scanner(System.in);
			int i = sn.nextInt();
			int j = sn.nextInt();
			while (!isLegal(i, j)) {
				System.out.println("����Ƿ����������������ӵ�����:");
				sn = new Scanner(System.in);
				i = sn.nextInt();
				j = sn.nextInt();
			}
			BROAD[i - 1][j - 1] = "��";
			paintBroad();
			int[] coordinate = computer();
			int c1 = coordinate[0];
			int c2 = coordinate[1];
			BROAD[c1 - 1][c2 - 1] = "��";
			System.out.println("��������: " + c1 + " " + c2);
			paintBroad();
			if (isWin() == 1) {
				System.out.println("��ϲ��Ӯ��");
				break;
			}
			if (isWin() == 0) {
				System.out.println("���ź���������");
				break;
			}
		} while (isWin() == -1);
	}
}