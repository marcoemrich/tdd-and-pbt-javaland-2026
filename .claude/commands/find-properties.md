---
description: Find properties to harden an existing TDD implementation with property-based tests
---

# Find Properties Command

**MANDATORY**: Use the Task tool with `subagent_type: "find-properties"` to find properties.

Do NOT perform this phase manually. The agent systematically analyzes the implementation and discovers meaningful properties.

Provide the agent with the necessary context:
- Implementation file path
- Test file path (existing TDD tests)
- Target properties file path (e.g., `*Properties.java`)
