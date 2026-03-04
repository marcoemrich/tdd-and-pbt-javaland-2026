---
description: Implement minimal code to make a failing property pass for ALL inputs (Property-First Green Phase)
---

# Property Green Command

**MANDATORY**: Use the Task tool with `subagent_type: "property-green"` to run the Property Green phase.

Do NOT perform this phase manually. The agent implements minimal code that must work for ALL generated inputs.

**After Property Green completes**: Launch a separate Task with `subagent_type: "refactor"` for the Refactor phase. The existing TDD refactor agent is reused.

Provide the agent with the necessary context:
- Properties file path
- Failing property name and what it asserts
- Current counterexample/error message
- Implementation file path
