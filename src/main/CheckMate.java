package main;

import java.util.Scanner;
import tasks.Priority;
import tasks.Task;
import users.Registration;
import users.Login;

public class CheckMate {
    static Scanner input = new Scanner(System.in);

    public static Priority getPriorityLevel() {
        System.out.print(
                "\nPlease choose a priority: \n0.No Priority \n1.Low Priority \n2.Medium Priority \n3.High Priority\n");
        System.out.print("\nYour option? ");
        int choice = input.nextInt();
        input.nextLine();
        if (!(choice < 0 || choice > 3)) {
            return Priority.fromPriority(choice);
        } else {
            System.out.println("Invalid choice to choose priority. Try again !");
            return getPriorityLevel();
        }
    }

    public void taskOperations(Task taskManager) {
        try {
            System.out.println("\nChoose the task operation you wanna perform: " +
                    "\n1.View all Tasks \n2.View prioritized tasks \n3.Add Task \n4.Edit Task " +
                    "\n5.Remove Task \n6.Change priority of a task \n7.Mark a task completed \n8.Logout");
            System.out.print("\nYour choice ? ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    taskManager.printAll();
                    break;
                case 2:
                    taskManager.printSpecific(getPriorityLevel());
                    break;
                case 3:
                    Priority addPriority = getPriorityLevel();
                    System.out.print("Enter the task description: ");
                    String addTask = input.nextLine();
                    taskManager.addTask(addPriority, addTask);
                    break;
                case 4:
                    Priority editPriority = getPriorityLevel();
                    System.out.println("Choose the task you wanna edit: ");
                    taskManager.printSpecific(editPriority);
                    System.out.print("Your option ? ");
                    int editIndex = input.nextInt();
                    input.nextLine();
                    System.out.print("Enter new task description: ");
                    String editTask = input.nextLine();
                    taskManager.editTask(editPriority, editIndex, editTask);
                    break;
                case 5:
                    Priority removePriority = getPriorityLevel();
                    System.out.println("Choose the task you wanna remove/delete: ");
                    taskManager.printSpecific(removePriority);
                    System.out.print("Your option ? ");
                    int removeIndex = input.nextInt();
                    input.nextLine();
                    taskManager.removeTask(removePriority, removeIndex);
                    break;
                case 6:
                    System.out.println("Enter old priority of that task: ");
                    Priority oldPriority = getPriorityLevel();
                    System.out.print("Choose the task for which you wanna change the priority: ");
                    taskManager.printSpecific(oldPriority);
                    System.out.print("Your option ? ");
                    int changeIndex = input.nextInt();
                    System.out.println("Enter the new priority to move the task into: ");
                    Priority newPriority = getPriorityLevel();
                    taskManager.changePriority(oldPriority, newPriority, changeIndex);
                    break;
                case 7:
                    Priority completedPriority = getPriorityLevel();
                    System.out.println("Choose the task you wanna mark as completed: ");
                    taskManager.printSpecific(completedPriority);
                    System.out.print("Your option ? ");
                    int completedIndex = input.nextInt();
                    taskManager.markCompleted(completedPriority, completedIndex);
                    break;
                case 8:
                    System.out.println("See you again pal! \nLogging out of the account..");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid index. Try again !");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage() + " Try again!");
            taskOperations(taskManager);
        }
    }

    public static void main(String args[]) {
        System.out.println("Welcome to Check-Mate. Your ultimate task manager !");
        boolean conditon = true;
        while (conditon) {
            System.out.print("\nEnter your choice:\n1.New Registration\n2.User Login\nYour choice? ");
            int option = input.nextInt();
            switch (option) {
                case 1:
                    System.out.print("\nCreate a username containing only lower-case letters: ");
                    String userID = input.next();
                    System.out.print("\nCreate a strong alphanumeric password of atleast 8 in length: ");
                    String pass = input.next();
                    Registration register = new Registration(userID, pass);
                    conditon = false;
                    break;
                case 2:
                    System.out.print("\nWelcome back! Enter your username: ");
                    String userName = input.next();
                    System.out.print("Enter the passsword: ");
                    String password = input.next();
                    Login user = new Login(userName, password);
                    CheckMate mate = new CheckMate();
                    Task taskManager = new Task(userName);
                    while (taskManager != null) {
                        mate.taskOperations(taskManager);
                    }
                    break;
                default:
                    System.out.println("Invalid choice to validate credentials. Try running the program again !");
            }
        }
    }
}
