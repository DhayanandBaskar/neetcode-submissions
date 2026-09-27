class Solution {

    public record Visit(String username, String website, int timestamp) {}

    public List<String> mostVisitedPattern(
        String[] username, 
        int[] timestamp, 
        String[] website
    ) {
        ArrayList<Visit> visits = new ArrayList<>();
        for (int i = 0; i < username.length; i++) {
            visits.add(new Visit(username[i], website[i], timestamp[i]));
        }

        visits.sort(Comparator.comparingInt(Visit::timestamp));

        HashMap<String, List<String>> sitesByUser = new HashMap<>();
        for (Visit visit: visits) {
            sitesByUser
                .computeIfAbsent(visit.username(), ignore -> new ArrayList<>())
                .add(visit.website());

        }

        Map<String, Integer> patterns = new TreeMap<>();
        for(List<String> userVisits: sitesByUser.values()) {
            for (String pattern: generatePatterns(userVisits)) {
                patterns.merge(pattern, 1, Integer::sum);
            }
        }

        String bestPattern = null;
        int bestScore = 0;
        for(String pattern: patterns.keySet()) {
            int score = patterns.get(pattern);
            if (score > bestScore) {
                bestScore = score;
                bestPattern = pattern;
            }
        }

        return List.of(bestPattern.split("#"));
    }

    private Set<String> generatePatterns(List<String> sites) {
        Set<String> patterns = new HashSet<>();
        for(int i = 0; i < sites.size(); i++) {
           for(int j = i+1; j < sites.size(); j++) {
                for(int k = j+1; k < sites.size(); k++) {
                    patterns.add(sites.get(i)+"#"+sites.get(j)+"#"+sites.get(k));
                }
            } 
        }

        return patterns;
    }
}