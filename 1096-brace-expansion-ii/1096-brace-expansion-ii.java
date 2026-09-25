class Solution {
    private String s;
    private int idx;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        idx = 0;
        Set<String> result = parseExpr();
        List<String> list = new ArrayList<>(result);
        Collections.sort(list);
        return list;
    }

    private Set<String> parseExpr() {
        List<Set<String>> terms = new ArrayList<>();
        terms.add(parseTerm());
        while (idx < s.length() && s.charAt(idx) == ',') {
            idx++;
            terms.add(parseTerm());
        }
        Set<String> union = new HashSet<>();
        for (Set<String> t : terms) {
            union.addAll(t);
        }
        return union;
    }

    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");
        while (idx < s.length() && s.charAt(idx) != ',' && s.charAt(idx) != '}') {
            Set<String> factor = parseFactor();
            Set<String> next = new HashSet<>();
            for (String a : result) {
                for (String b : factor) {
                    next.add(a + b);
                }
            }
            result = next;
        }
        return result;
    }

    private Set<String> parseFactor() {
        if (s.charAt(idx) == '{') {
            idx++;
            Set<String> res = parseExpr();
            idx++;
            return res;
        } else {
            Set<String> res = new HashSet<>();
            res.add(String.valueOf(s.charAt(idx)));
            idx++;
            return res;
        }
    }
}