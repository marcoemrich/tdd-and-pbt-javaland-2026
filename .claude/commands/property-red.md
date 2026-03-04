---
description: Activate one property, predict failure, verify it fails (Property-First Red Phase)
---

# Property Red Command

**MANDATORY**: Use the Task tool with `subagent_type: "property-red"` to run the Property Red phase.

Do NOT perform this phase manually. The agent activates one property, makes predictions, and verifies failure.

Provide the agent with the necessary context:
- Properties file path
- Which `@Disabled("todo")` property to activate (name or line number)
- Current number of passing properties
- Implementation file path
