package tasks;

public enum Priority {
    HIGH(3, "C:/Users/ASUS/Downloads/checkMate/resources/high"),
    MEDIUM(2, "C:/Users/ASUS/Downloads/checkMate/resources/medium"),
    LOW(1, "C:/Users/ASUS/Downloads/checkMate/resources/low"),
    NO(0, "C:/Users/ASUS/Downloads/checkMate/resources/no");

    private final int priority;
    private final String filePath;

    Priority(int priority, String filePath) {
        this.priority = priority;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getPriority() {
        return priority;
    }

    public static Priority fromPriority(int priority) {
        for (Priority p : Priority.values()) {
            if (p.getPriority() == priority) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid priority: " + priority);
    }
}
