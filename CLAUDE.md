# mqe-message-hub

Web interface for the Message Quality Evaluator — Spring Boot backend + Angular frontend wrapping
`mqe-validator`/`mqe-hl7-util` behind a UI/API. See `../CLAUDE.md` for how this fits with the other MQE repos.

- GitHub: immregistries/mqe-message-hub
- Maven: `groupId org.immregistries`, `artifactId mqe-message-hub`, packaging `war`, parent
  `spring-boot-starter-parent 1.3.3.RELEASE`
- Java: 1.7; frontend built via `frontend-maven-plugin` (Node v10.15.0, Angular under `angular/`)
- Depends on: `mqe-validator`, `mqe-hl7-utility` (both pinned to `${mqe.version}`)
- Status: **dated, no longer actively built**; last commit 2021-07-19
