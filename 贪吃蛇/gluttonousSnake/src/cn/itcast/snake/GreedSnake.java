package cn.itcast.snake;

public class GreedSnake {
	public static void main(String[] args) {
		SnakeModel model = new SnakeModel(20, 30);
		SnakeControl control = new SnakeControl(model);
		SnakeView view = new SnakeView(model, control);
		// ���һ���۲��ߣ���view��Ϊmodel�Ĺ۲���
		model.addObserver(view);
		new Thread(model).start();
	}
}