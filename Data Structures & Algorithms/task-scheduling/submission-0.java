class Solution {
    public int leastInterval(char[] tasks, int n) {
        record Task(char letter, int remaining) {}
        record CoolingTask(char letter, int remaining, int readyAt) {}

        Map<Character, Integer> frequency = new HashMap<>();
        for(char task: tasks) {
            frequency.merge(task, 1, Integer::sum);
        }

        PriorityQueue<Task> availableTasks = new PriorityQueue<>(
            Comparator.comparingInt(Task::remaining).reversed()
        );
        for(var entry: frequency.entrySet()) {
            availableTasks.add(new Task(entry.getKey(), entry.getValue()));
        }
        Queue<CoolingTask> cooling = new ArrayDeque();
        int time = 0;

        while(!availableTasks.isEmpty() || !cooling.isEmpty()) {
            //if cooling has ended, move it back to available
            while(!cooling.isEmpty() && cooling.peek().readyAt() <= time) {
                CoolingTask coolingTask = cooling.remove();
                availableTasks
                .add(new Task(coolingTask.letter(), coolingTask.remaining()));
            }

            if (!availableTasks.isEmpty()) {
                Task task = availableTasks.remove();
                int remaining = task.remaining() - 1;

                if (remaining > 0) {
                    cooling.add(new CoolingTask(
                        task.letter(),
                        remaining,
                        time + n + 1
                    ));
                }
            }

            time++;
        }

        return time;
    }
}
