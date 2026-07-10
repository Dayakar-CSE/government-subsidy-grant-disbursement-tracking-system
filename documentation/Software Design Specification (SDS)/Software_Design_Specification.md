# Software Design Specification (SDS)

## 1. Use Case Diagram

### Purpose

The Use Case Diagram represents the interaction between different actors and the Government Subsidy & Grant Disbursement Tracking System.

### Actors

- Beneficiary
- Field Officer
- District Officer
- Finance Officer
- Administrator

### Description

The Beneficiary registers, logs in, browses available schemes, submits applications, uploads required documents, and tracks application status.

The Field Officer verifies submitted applications and prepares verification reports.

The District Officer reviews verification reports and approves or rejects applications.

The Finance Officer authorizes and releases subsidy funds.

The Administrator manages users, government schemes, eligibility rules, and reporting.

### Diagram

See:

`diagrams/generated/01-UseCaseDiagram.png`

# 2. Business Activity Diagram

## Objective

The Business Activity Diagram represents the complete lifecycle of a government subsidy application, from beneficiary registration to application closure.

## Business Phases

1. Registration
2. Authentication
3. Scheme Selection
4. Application Submission
5. Document Upload
6. Eligibility Evaluation
7. Field Verification
8. District Approval
9. Finance Approval
10. Fund Disbursement
11. Compliance Monitoring
12. Reporting
13. Application Closure

## Decision Points

The workflow contains four primary decision points:

- Application Validation
- Eligibility Evaluation
- Verification Result
- Approval Decision

An application may be rejected at any of these stages and the beneficiary is notified accordingly.

## Diagram

Refer:

`diagrams/generated/02-ActivityDiagram.png`

## Supporting Design Documents

- Class Catalog (`Class_Catalog.md`)

# 3. Domain Class Diagram

## Part 1 – Security and Citizen Domain

### Purpose

This section models the authentication and citizen profile components of the Government Subsidy & Grant Disbursement Tracking System.

### Security Domain

The Security Domain consists of the `Role` and `User` entities responsible for authentication and authorization.

### Citizen Domain

The Citizen Domain consists of the `Beneficiary` and `Region` entities, representing beneficiary information and geographical hierarchy.

### Relationships

- One Role can be assigned to many Users.
- Each User account belongs to exactly one Beneficiary.
- One Region can contain multiple Beneficiaries.

### Diagram

Refer:

`diagrams/generated/03-DomainClassDiagram-Part1.png`

## Part 2 – Core Business Domain

### Purpose

This section models the core business entities responsible for managing government subsidy applications, supporting documents, verification, approval workflow, fund disbursement, compliance monitoring, and audit history.

### Aggregate Root

The `Application` entity acts as the Aggregate Root for the business workflow.

### Composition Relationships

The following entities have a composition relationship with `Application` because they cannot exist independently:

- Document
- Verification
- Approval
- Disbursement
- Milestone

### Associations

- Each Application belongs to one Scheme.
- AuditLog records user actions across business entities.

### Diagram

Refer:

`diagrams/generated/03-DomainClassDiagram-Part2.png`

## Part 3 – Integrated Domain Class Diagram

### Purpose

The integrated Domain Class Diagram combines the Security, Citizen, Scheme, and Application domains into a single architectural model.

### Architectural Highlights

- Authentication and Authorization are separated from business entities.
- Application is the Aggregate Root for all business workflows.
- Composition is used where child entities cannot exist independently.
- Enumerations are used to represent controlled business states.
- The model follows the principles of Domain-Driven Design (DDD), Single Responsibility Principle (SRP), High Cohesion, and Low Coupling.

### Diagram

Refer:

`diagrams/generated/03-DomainClassDiagram.png`

# 4. Entity Relationship Diagram

## Purpose

The Entity Relationship Diagram (ERD) represents the relational database design for the Government Subsidy & Grant Disbursement Tracking System.

The ERD is derived directly from the approved Domain Class Diagram and serves as the blueprint for the MySQL database schema.

## Design Principles

- Third Normal Form (3NF)
- Referential Integrity
- Primary Keys
- Foreign Keys
- Minimal Data Redundancy
- Scalable Relationship Design

## Tables

The system contains the following tables:

- Role
- User
- Beneficiary
- Region
- Scheme
- Application
- Document
- Verification
- Approval
- Disbursement
- Milestone
- AuditLog

## Diagram

Refer:

`diagrams/generated/04-ERDiagram.png`

## Database Design

The database schema is designed using DBML (Database Markup Language).

The database design follows:

- Third Normal Form (3NF)
- Referential Integrity
- Crow's Foot Notation
- Industry-standard naming conventions

Refer:

- `database/dbml/01-DatabaseDesign-Part1.dbml`

## Database Design – Part 2

The Core Business Domain consists of the following tables:

- Scheme
- Application
- Document
- Verification
- Approval
- Disbursement
- Milestone
- AuditLog

The `Application` table acts as the central business entity and references the `Beneficiary` and `Scheme` tables. All workflow-related tables are linked to `Application` through foreign key relationships, ensuring data consistency and supporting the complete subsidy processing lifecycle.

Refer:

- `database/dbml/02-DatabaseDesign-Part2.dbml`
- `database/diagrams/02-DatabaseDesign-Part2.png`

## Final Database Design

The complete relational database schema has been finalized using DBML and validated through a Crow's Foot ER Diagram.

The schema includes:

- Security Domain
- Citizen Domain
- Scheme Domain
- Application Domain

The database follows:

- Third Normal Form (3NF)
- Referential Integrity
- Industry-standard naming conventions
- Primary and Foreign Key constraints

Artifacts:

- `database/dbml/03-DatabaseDesign.dbml`
- `database/diagrams/03-DatabaseDesign.png`
- `database/sql/schema.sql`