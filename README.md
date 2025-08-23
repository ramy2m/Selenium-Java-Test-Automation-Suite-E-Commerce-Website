# Automated UI Testing for an E-Commerce Website

## Project Overview
This project is part of the Digital Egypt Pioneers Initiative (DEPI) graduation project.  
We developed an **automated UI testing framework** for a demo e-commerce website using **Java and Selenium WebDriver**.  
The focus is on writing **clean, maintainable, and reusable** automated test scripts that verify the core user journeys of an online shopping platform.

---

## Objectives
- Automate key user flows on an e-commerce website.
- Apply testing best practices: assertions, waits, and test data management.
- Refactor tests using the **Page Object Model (POM)** design pattern.
- Deliver a well-structured and maintainable testing framework.

---

## Tech Stack
- **Programming Language:** Java  
- **Automation Tool:** Selenium WebDriver  
- **Build Tool:** Maven/Gradle  
- **Testing Framework:** TestNG / JUnit  
- **Target Website:** [Swag Labs Demo](https://www.saucedemo.com/)  

---

## Project Structure
ecommerce-ui-automation/
│── src/test/java/
│ ├── tests/ # Test cases
│ ├── pages/ # Page Object classes
│ └── utils/ # Utility classes (waits, data, etc.)
│
│── pom.xml # Maven dependencies
│── README.md # Project documentation

---

## Test Coverage
1. **Login Flow** – Valid and invalid login tests.  
2. **Product Search & Add to Cart** – Searching and adding an item.  
3. **Checkout Process** – Verifying cart and completing checkout.  
4. **Assertions** – Page titles, cart counters, confirmation messages.  

---

## How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/username/ecommerce-ui-automation.git
2. Install dependencies:
 mvn clean install
3. Run the test suite:
mvn test
   Sample Report

After execution, TestNG generates an HTML report showing the pass/fail results of all test cases.

 Author

 Ramy Mohamed Hussein – Test Automation Engineer
 Ahmed Abdallah Rizk – Test Automation Engineer
 Mohamed El Shaarawy – Test Automation Engineer

 Future Enhancements

CI/CD integration with GitHub Actions.

Data-driven testing (Excel/CSV).

Cross-browser testing (Chrome, Firefox, Edge).

