# Database Design

## Purpose

This directory contains the database design artifacts for the Government Subsidy & Grant Disbursement Tracking System.

## Structure

- `dbml/` — DBML source files used to generate the ER Diagram.
- `sql/` — SQL schema generated from the finalized DBML.
- `README.md` — Database documentation.

## Database Design Strategy

The database schema has been designed using:

- Third Normal Form (3NF)
- Referential Integrity
- Primary and Foreign Key Constraints
- Crow's Foot Relationship Modeling
- Industry-standard naming conventions

The DBML serves as the single source of truth for the database design.

## Database Design - Part 2

The Core Business Domain models the complete lifecycle of a government subsidy application.

It includes:

- Scheme Management
- Application Management
- Document Management
- Verification Workflow
- Approval Workflow
- Fund Disbursement
- Compliance Monitoring
- Audit Logging

This design follows Third Normal Form (3NF) and maintains referential integrity using primary and foreign key constraints.

## Final Database Design

The final database design integrates all project modules into a unified relational schema.

Artifacts:

- `03-DatabaseDesign.dbml` – Master DBML source
- `03-DatabaseDesign.png` – Final Crow's Foot ER Diagram
- `schema.sql` – Generated MySQL schema

This master design will be used for Spring Boot JPA entity creation and database implementation.