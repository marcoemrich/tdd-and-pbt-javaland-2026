---
description: Implement one @Disabled property-based test and analyze jqwik results
---

# Implement Properties Command

**MANDATORY**: Use the Task tool with `subagent_type: "implement-properties"` to implement properties.

Do NOT perform this phase manually. The agent implements one property at a time and analyzes jqwik's results including counterexamples.

Provide the agent with the necessary context:
- Properties file path (e.g., `*Properties.java`)
- Which `@Disabled("todo")` property to implement (name or line number)
- Implementation file path
- Current number of passing properties
