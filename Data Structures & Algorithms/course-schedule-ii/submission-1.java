class Solution {
    public static class Course {
        public int id; 
        public Set<Course> prerequisites; 
        public int indegree;

        public Course(int id) {
            this.id = id;
            this.prerequisites = new HashSet<>();
            this.indegree = 0;
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Course> graph = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new Course(i));
        }

        for(int[] prerequisite: prerequisites) {
            Course a = graph.get(prerequisite[0]);
            Course b = graph.get(prerequisite[1]);

            b.prerequisites.add(a);
            a.indegree += 1;
        }

        ArrayDeque<Course> stack = new ArrayDeque<>();
        for (Course course: graph.values()) {
            if (course.indegree == 0) {
                stack.push(course);
            }
        }

        List<Integer> topologcalOrder = new ArrayList<>();
        while(!stack.isEmpty()) {
            Course current = stack.pop();
            topologcalOrder.add(current.id);

            for (Course preReq: current.prerequisites) {
                preReq.indegree -= 1;
                if (preReq.indegree == 0) {
                    stack.push(preReq);
                }
            }
        }

        if (topologcalOrder.size() != numCourses) {
            return new int[0];
        }

        return topologcalOrder.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}
