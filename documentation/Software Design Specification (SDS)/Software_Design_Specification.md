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