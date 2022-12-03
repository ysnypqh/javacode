package cn.itcast.snake;

import javax.swing.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;
class SnakeModel extends Observable implements Runnable {
    boolean[][] matrix;                         // ָʾλ������û�����ʳ��
    LinkedList nodeArray = new LinkedList();    // ����
    Node food;
    int maxX;									//����
    int maxY;									//����
    int direction = 2;                          // �����еķ���
    boolean running = false;                    // ����״̬

    int timeInterval = 200;                     // ʱ����������
    double speedChangeRate = 0.75;              // ÿ�ε��ٶȱ仯��
    boolean paused = false;                     // ��ͣ��־

    int score = 0;                              // �÷�
    int countMove = 0;                          // �Ե�ʳ��ǰ�ƶ��Ĵ���

    // UP and DOWN should be even
    // RIGHT and LEFT should be odd
    public static final int UP = 2;
    public static final int DOWN = 4;
    public static final int LEFT = 1;
    public static final int RIGHT = 3;

    public SnakeModel( int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        reset();
    }

    public void reset(){
        direction = SnakeModel.UP;              // �����еķ���
        timeInterval = 200;                     // ʱ����������
        paused = false;                         // ��ͣ��־
        score = 0;                              // �÷�
        countMove = 0;                          // �Ե�ʳ��ǰ�ƶ��Ĵ���

        // initial matirx, ȫ����0
        matrix = new boolean[maxX][];
        for (int i = 0; i < maxX; ++i) {
            matrix[i] = new boolean[maxY];
            Arrays.fill(matrix[i], false);
        }

        // initial the snake
        // ��ʼ�����壬�������λ�ó���20��������Ϊ10������Ϊ����λ�õ�һ��
        int initArrayLength = maxX > 20 ? 10 : maxX / 2;
        nodeArray.clear();
        for (int i = 0; i < initArrayLength; ++i) {
            int x = maxX / 2 + i;//maxX����ʼ��Ϊ20
            int y = maxY / 2;    //maxY����ʼ��Ϊ30
            //nodeArray[x,y]: [10,15]-[11,15]-[12��15]~~[20,15]
            //Ĭ�ϵ����з������ϣ�������Ϸһ��ʼnodeArray�ͱ�Ϊ��
            //       [10��14]-[10��15]-[11��15]-[12��15]~~[19��15]
            nodeArray.addLast(new Node(x, y));
            matrix[x][y] = true;
        }

        // ����ʳ��
        food = createFood();
        matrix[food.x][food.y] = true;
    }

    public void changeDirection(int newDirection) {
        // �ı�ķ�������ԭ������ͬ�����
        if (direction % 2 != newDirection % 2) {
            direction = newDirection;
        }
    }
   
    public boolean moveOn() {
        Node n = (Node) nodeArray.getFirst();
        int x = n.x;
        int y = n.y;

        // ���ݷ�����������ֵ
        switch (direction) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }

        // ���������������Ч��Χ�ڣ�����д���
        if ((0 <= x && x < maxX) && (0 <= y && y < maxY)) {
        
            if (matrix[x][y]) {        // ���������ĵ����ж������������ʳ�
                if (x == food.x && y == food.y) {       // �Ե�ʳ��ɹ�
                    nodeArray.addFirst(food);           // ����ͷ����

                    // �����������ƶ��ı䷽��Ĵ������ٶ�����Ԫ���й�
                    int scoreGet = (10000 - 200 * countMove) / timeInterval;
                    score += scoreGet > 0 ? scoreGet : 10;
                    countMove = 0;

                    food = createFood();                // �����µ�ʳ��
                    matrix[food.x][food.y] = true;      // ����ʳ������λ��
                    return true;
                } else                                  // �Ե���������ʧ��
                    return false;
               
            } else {                 // ���������ĵ���û�ж��������壩���ƶ�����
                nodeArray.addFirst(new Node(x, y));
                matrix[x][y] = true;
                n = (Node) nodeArray.removeLast();
                matrix[n.x][n.y] = false;
                countMove++;
                return true;
            }
        }
        return false;                                   // �������ߣ�ʧ��
    }

    public void run() {
        running = true;
        while (running) {
            try {
                Thread.sleep(timeInterval);
            } catch (Exception e) {
                break;
            }

            if (!paused) {
                if (moveOn()) {
                    setChanged();           // Model֪ͨView�����Ѿ�����
                    notifyObservers();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "you failed",
                            "Game Over",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        }
        running = false;
    }

    private Node createFood() {
        int x = 0;
        int y = 0;
        // �����ȡһ����Ч�����ڵ��������ʳ�ﲻ�ص���λ��
        do {
            Random r = new Random();
            x = r.nextInt(maxX);
            y = r.nextInt(maxY);
        } while (matrix[x][y]);

        return new Node(x, y);
    }

    public void speedUp() {
        timeInterval *= speedChangeRate;
    }

    public void speedDown() {
        timeInterval /= speedChangeRate;
    }

    public void changePauseState() {
        paused = !paused;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < nodeArray.size(); ++i) {
            Node n = (Node) nodeArray.get(i);
            result += "[" + n.x + "," + n.y + "]";
        }
        return result;
    }
}

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}