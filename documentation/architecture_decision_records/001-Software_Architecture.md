# Software Architecture

## Context 
The goal is to separate the business logic and keep it free of any technical dependencies.

## Decision
Either use Clean Architecture or Onion Architecture.
The final decision can be done later. There is a preference for Clean Architecture, because of the Use Cases.

## Consequences
### Positive impacts
* The source of the business logic is kept free from frameworks
* The frameworks for dependency injection, transactions, webservices,... have no influence to the business code and can be replaced easier
* The business logic can be tested standalone

### Potential Drawbacks
* No dependency/context injection. Maybe it's harder to create objects
* If a decision is made for Event Driven Architecture, it could be harder to apply events
