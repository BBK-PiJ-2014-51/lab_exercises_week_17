package exercise4;

import java.util.ArrayList;
import java.util.Scanner;


public class ResponsiveUi  {
	private static final int NUM_TASKS = 10;
	private Task[] tasks = new Task[NUM_TASKS];
	private boolean[] reported = new boolean[NUM_TASKS];
	
	public ResponsiveUi(){
		for (int i = 0; i < NUM_TASKS; i++){
			reported[i] = false;
			tasks[i] = null;
		}
	}
	public static void main(String[] args) {
		ResponsiveUi ui = new ResponsiveUi();
		for (int i = 0; i < NUM_TASKS; i++) {
			ui.checkComplete();
			Task task = new Task(getWaitTime(i));
			Thread t = new Thread(task);
			ui.addTask(task, i);
			t.start();			
		}
	}

	private void checkComplete() {
		ArrayList<Integer> justCompleted = new ArrayList<Integer>();
		for (int i = 0; i < NUM_TASKS; i++){
			if (!reported[i]){
				if (tasks[i] != null){
					if(!tasks[i].isRunning()){
						justCompleted.add(i);
						reported[i] = true;
					}
				}
			}
		}
		if (!justCompleted.isEmpty()){
			String output = "Just completed: ";
			for (Integer i : justCompleted){
				output = output + i + ", ";
			}
			System.out.println(output.substring(0, output.length() - 2));
		}
	}

	public void addTask(Task task, int i) {
		tasks[i] = task;
	}

	private static int getWaitTime(int i){
		System.out.println("Enter the duration (in ms) of task " + i + ": ");
		Scanner scanner = new Scanner(System.in);
		int result = -1;
			if(scanner.hasNext()) {
				try{
					result = Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException ex){
					ex.printStackTrace();
				}
			}
		return result;
	}
}