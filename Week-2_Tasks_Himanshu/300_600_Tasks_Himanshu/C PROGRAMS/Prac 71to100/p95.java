import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Fact {
    private String name;
    private Map<String, Object> attributes;

    public Fact(String name) {
        this.name = name;
        this.attributes = new HashMap<>();
    }

    public void setAttribute(String attributeName, Object value) {
        attributes.put(attributeName, value);
    }

    public Object getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }

    public String getName() {
        return name;
    }
}

class Condition {
    private String attributeName;
    private Object expectedValue;

    public Condition(String attributeName, Object expectedValue) {
        this.attributeName = attributeName;
        this.expectedValue = expectedValue;
    }

    public boolean evaluate(Fact fact) {
        Object actualValue = fact.getAttribute(attributeName);
        return expectedValue.equals(actualValue);
    }
}

class Rule {
    private List<Condition> conditions;
    private String action;

    public Rule(List<Condition> conditions, String action) {
        this.conditions = conditions;
        this.action = action;
    }

    public boolean evaluate(Fact fact) {
        for (Condition condition : conditions) {
            if (!condition.evaluate(fact)) {
                return false;
            }
        }
        return true;
    }

    public String getAction() {
        return action;
    }
}

public class p95 {
    private List<Rule> rules;

    public p95() {
        this.rules = new ArrayList<>();
    }

    public void addRule(List<Condition> conditions, String action) {
        Rule rule = new Rule(conditions, action);
        rules.add(rule);
    }

    public void execute(Fact fact) {
        for (Rule rule : rules) {
            if (rule.evaluate(fact)) {
                System.out.println("Rule fired: " + rule.getAction());
            }
        }
    }

    public static void main(String[] args) {
        p95 rete = new p95();

        List<Condition> conditions1 = List.of(new Condition("temperature", "high"), new Condition("humidity", "low"));
        rete.addRule(conditions1, "Turn on air conditioner");

        List<Condition> conditions2 = List.of(new Condition("temperature", "low"), new Condition("humidity", "high"));
        rete.addRule(conditions2, "Turn on heater");

        Fact fact1 = new Fact("Weather");
        fact1.setAttribute("temperature", "high");
        fact1.setAttribute("humidity", "low");

        Fact fact2 = new Fact("Weather");
        fact2.setAttribute("temperature", "low");
        fact2.setAttribute("humidity", "high");

        rete.execute(fact1);
        rete.execute(fact2);
    }
}
