package tasks;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Comparator;
import tasks.Priority;

public class Task {
    Comparator<Priority> priorityComparator = new Comparator<>() {
        @Override
        public int compare(Priority p1, Priority p2) {
            return Integer.compare(p2.getPriority(), p1.getPriority());
        }
    };
    String userFile;
    TreeMap<Priority, ArrayList<String>> taskMap;

    public Task(String userName) {
        this.userFile = "_" + userName + ".txt";
        taskMap = new TreeMap<>(priorityComparator);
        for (Priority priority : Priority.values()) {
            taskMap.put(priority, new ArrayList<>());
            loadTasksFromFile(priority);
        }
    }

    public void addTask(Priority priorityLevel, String task) {
        taskMap.get(priorityLevel).add(task);
        saveTasksToFile(priorityLevel);
        System.out.println("The task has been added successfully");
        System.out.println();
    }

    public void removeTask(Priority priorityLevel, int index) {
        if (index >= 0 && index < taskMap.get(priorityLevel).size()) {
            taskMap.get(priorityLevel).remove(index);
            saveTasksToFile(priorityLevel);
            System.out.println("The task has been removed successfully");
        } else {
            throw new IllegalArgumentException("Invalid index to remove task");
        }
        System.out.println();
    }

    public void changePriority(Priority oldPriority, Priority newPriority, int index) {
        if (index >= 0 && index < taskMap.get(oldPriority).size()) {
            String changeTask = taskMap.get(oldPriority).remove(index);
            saveTasksToFile(oldPriority);
            addTask(newPriority, changeTask);
            System.out.println("Task priority changed from " + oldPriority.name() + " priority to " + newPriority.name()
                    + " priority");
        } else {
            throw new IllegalArgumentException("Invalid index to change task priority");
        }
        System.out.println();
    }

    public void markCompleted(Priority priorityLevel, int index) {
        if (index >= 0 && index < taskMap.get(priorityLevel).size()) {
            taskMap.get(priorityLevel).remove(index);
            saveTasksToFile(priorityLevel);
            System.out.println("The task has been marked as completed. Keep up the good work!");
        } else {
            throw new IllegalArgumentException("Invalid index to mark the task as completed");
        }
        System.out.println();
    }

    public void editTask(Priority priorityLevel, int index, String newTask) {
        if (index >= 0 && index < taskMap.get(priorityLevel).size()) {
            taskMap.get(priorityLevel).set(index, newTask);
            saveTasksToFile(priorityLevel);
            System.out.println("The task has been edited successfully");
        } else {
            throw new IllegalArgumentException("Invalid index to edit the task");
        }
        System.out.println();
    }

    public void printSpecific(Priority priorityLevel) {
        ArrayList<String> tasks = taskMap.get(priorityLevel);
        int serial = 0;
        if (!tasks.isEmpty()) {
            System.out.println();
            System.out.println(priorityLevel.name() + " Priority Tasks: ");
            for (String task : tasks) {
                System.out.printf("%d. %s%n", serial, task);
                serial++;
            }
        } else {
            System.out.println(priorityLevel.name() + " Priority Tasks: ");
            System.out.println("none in the list");
        }
    }

    public void printAll() {
        for (Priority priorityLevel : Priority.values()) {
            printSpecific(priorityLevel);
            System.out.println();
        }
    }

    private void saveTasksToFile(Priority priority) {
        ArrayList<String> tasks = taskMap.get(priority);
        try (FileWriter fileWriter = new FileWriter(priority.getFilePath() + userFile);
                BufferedWriter bw = new BufferedWriter(fileWriter)) {
            for (String task : tasks) {
                bw.write(task);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private void loadTasksFromFile(Priority priority) {
        ArrayList<String> tasks = taskMap.get(priority);
        try (FileReader fileReader = new FileReader(priority.getFilePath() + userFile);
                BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }
}
