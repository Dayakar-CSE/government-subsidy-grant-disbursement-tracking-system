# Class Catalog

## Purpose

The Class Catalog defines the core business entities of the Government Subsidy & Grant Disbursement Tracking System. It serves as the foundation for the Class Diagram, Entity Relationship Diagram (ERD), JPA entity design, and Spring Boot implementation.

---

## Domain Entity Catalog

| S.No | Entity | Primary Responsibility | Aggregate Root | Relationships |
|------|--------|------------------------|----------------|---------------|
| 1 | Role | Defines authorization roles and permissions | No | One Role → Many Users (1:N) |
| 2 | User | Authentication and account management | No | Many Users → One Role (N:1), One User ↔ One Beneficiary (1:1) |
| 3 | Beneficiary | Maintains citizen profile information | Yes | One Beneficiary ↔ One User (1:1), Many Beneficiaries → One Region (N:1), One Beneficiary → Many Applications (1:N) |
| 4 | Region | Stores geographical hierarchy (State, District, Mandal, Village) | No | One Region → Many Beneficiaries (1:N) |
| 5 | Scheme | Defines government subsidy or grant schemes | No | One Scheme → Many Applications (1:N) |
| 6 | Application | Represents a beneficiary's application for a government scheme | **Yes** | Many Applications → One Beneficiary (N:1), Many Applications → One Scheme (N:1), One Application → Many Documents (1:N), One Application → Many Verifications (1:N), One Application → Many Approvals (1:N), One Application → Many Disbursements (1:N), One Application → Many Milestones (1:N) |
| 7 | Document | Stores supporting documents uploaded by beneficiaries | No | Many Documents → One Application (N:1) |
| 8 | Verification | Stores field verification records and inspection details | No | Many Verifications → One Application (N:1) |
| 9 | Approval | Stores approval history across different approval levels | No | Many Approvals → One Application (N:1) |
| 10 | Disbursement | Stores fund release and payment transaction details | No | Many Disbursements → One Application (N:1) |
| 11 | Milestone | Tracks compliance checkpoints and project progress | No | Many Milestones → One Application (N:1) |
| 12 | AuditLog | Records system activities for auditing and traceability | No | References multiple business entities through Entity Name and Entity ID |

---

## Design Principles Applied

The domain model has been designed based on the following software engineering principles:

- Single Responsibility Principle (SRP)
- High Cohesion
- Low Coupling
- Domain-Driven Design (DDD)
- Database Normalization
- Future Scalability
- Auditability
- Maintainability

---

## Aggregate Roots

The system contains two primary aggregate roots:

### Beneficiary Aggregate

- Beneficiary
- User
- Region

### Application Aggregate

- Application
- Document
- Verification
- Approval
- Disbursement
- Milestone

---

## Notes

- `Application` is the central business entity of the system.
- `Verification`, `Approval`, `Disbursement`, and `Milestone` are modeled as separate entities to preserve workflow history and support future scalability.
- Authentication and authorization concerns are separated from business entities using `User` and `Role`.
- Eligibility evaluation is intentionally modeled as business logic (`EligibilityService`) rather than a database entity in the current version of the system.