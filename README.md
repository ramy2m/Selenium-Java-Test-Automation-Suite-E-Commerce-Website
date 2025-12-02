
# **Software Testing Project Documentation**

## **Project Title:**

**Multi-Part Software Testing (Manual, Automation, API)**

## **Team Members:**

* Mohamed
* Ahmed
* Ramy

## **Project Links:**

* **GitHub Repository:** [https://github.com/ramy2m/Selenium-Java-Test-Automation-Suite-E-Commerce-Website](https://github.com/ramy2m/Selenium-Java-Test-Automation-Suite-E-Commerce-Website)
* **Demo Websites:**

  * Demoblaze
  * OrangeHRM
  * DummyJSON

---

# **1. Project Planning**

## **Project Idea**

This project focuses on end-to-end **Software Testing** across multiple layers:

* **Manual Testing**
* **Automation Testing**
* **API Testing**

The aim is to validate **functionality**, **usability**, **data integrity**, and **core workflows** of selected demo web applications.

## **Objectives**

* Gain practical, hands-on experience in Manual, Automation, and API testing.
* Validate multiple functional modules across different systems.
* Document test cases, executions, bugs, and observations.
* Build a structured automation framework using Java, Selenium, and TestNG.

## **Scope**

### **Manual Testing**

Covers key modules such as:

* User Login / Signup
* Product browsing and categories
* Product details
* Cart operations
* Checkout and payment validation
* Basic performance and usability observations

### **Automation Testing**

Selenium/TestNG scripts for:

* Valid Login
* Invalid Login
* Add to Cart flow
* Checkout scenarios

### **API Testing**

Using Postman for endpoints such as:

* `POST /login`
* `POST /signup`
* `GET /products`
* Basic CRUD requests (where supported)

---

# **2. Stakeholder Analysis**

| Stakeholder                             | Interest / Role                                    | Delivered by Project                        |
| --------------------------------------- | -------------------------------------------------- | ------------------------------------------- |
| **End Users**                           | Using a functional website with correct operations | Tested and validated features               |
| **QA Engineers (Mohamed, Ahmed, Ramy)** | Execute manual, automation, and API tests          | Clear test cases, scenarios, and results    |
| **Admin / System Owner**                | Ensure stability and correctness of core features  | Validation of modules and secure operations |

---

# **3. Database Design (Mock Data)**

### **Tables**

* **Users** – Login credentials, profile info
* **Products** – Product ID, name, price, category
* **Orders** – Order items, payment info, timestamps
* **Cart** – Items added to cart for each user

### **Relationships**

* **One User → Many Orders**
* **One Order → Many Products**
* **One User → One Cart**

---

# **4. UI/UX Design**

### **Manual Testing Screens**

* Login / Signup
* Product browsing & categories
* Product details
* Cart page & checkout flow
* Payment validation

### **Automation Testing Screens**

* Dashboard & admin panels
* Employee list (Add / Update / Delete)
* System user management

### **User Flow Examples**

* **Customer:**
  `Login → Browse Products → Add to Cart → Checkout → Payment → Order Confirmation`

* **Admin:**
  `Login → Add System User → Validate Access → Logout → Login as New User`

---

# **5. Implementation**

## **Manual Testing**

* Test cases documented in Excel/Word
* Includes steps, expected results, and execution status

## **Automation Testing**

Built using:

* **Java**
* **Selenium WebDriver**
* **TestNG**
* **Maven**

Includes scripts for:

* Successful login
* Invalid login
* Add to cart flow
* Checkout test cases

**Repository:** *(same as top link)*

## **API Testing**

Using **Postman** collections for:

* Login
* Signup
* Product listing
* Response validation (status codes, payload, schema)

