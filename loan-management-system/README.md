# Loan Management System

## STS Setup
1. Open STS.
2. File > Import > Existing Maven Projects.
3. Select this project folder.
4. Update `src/main/resources/application.properties` MySQL username/password.
5. Create database manually or let URL create it: `loan_management_db`.
6. Right click project > Maven > Update Project.
7. Run `LoanManagementSystemApplication.java`.
8. Swagger URL: `http://localhost:8081/swagger-ui.html`

## Default Flow
1. Register user: POST `/api/auth/register`
2. Login: POST `/api/auth/login`
3. Use JWT token in Authorization header: `Bearer token`
4. Apply loan: POST `/api/loan/apply`
5. Upload document: POST `/api/document/upload`
6. Admin approve: POST `/api/admin/loan/approve/{id}`
7. Admin disburse: POST `/api/admin/loan/disburse/{applicationId}`
8. View EMI: GET `/api/emi/{loanAccountId}`
9. Pay EMI: POST `/api/repayment/pay`
