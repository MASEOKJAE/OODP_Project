# 📚 H-Library System

🔹 **Handong Global University | Object-Oriented Design Patterns**

🔹 **June 2023**

**Team B**

👤 **Dongheon Lee** | 21600490 | **Project Manager**

👤 **Sooyeong Kim** | 21800109 | **Architecture Designer**

👤 **Seokjae Ma** | 21800239 | **System Developer**

👤 **Kihong Park** | 21800264 | **Document Manager**

---

## 🚀 Project Overview

Libraries serve as **essential hubs of knowledge** for students, faculty, and staff. However, managing vast collections of books manually poses **challenges**, including:

- 📖 **Difficulty in tracking book loans and returns**
- 🔍 **Time-consuming book searches due to limited space**
- ❌ **Manual handling of overdue books and requests**
- 🏛 **Complex book organization with frequent new arrivals**

The **H-Library System** is designed to **automate and optimize** the process of **book borrowing, returns, reservations, and administration** through an **efficient and user-friendly digital library system**.

---

## 🔑 Key Features

### 🏛 Core Library Functions

| Feature | Description | Implementation |
| --- | --- | --- |
| **🔍 Search Books** | Users can search for books by **title, author, or publisher** without logging in. | ✅ Implemented (Refactored using Template Method Pattern) |
| **📖 Book Requests** | Users can request a book that **does not exist in the library**. | ✅ Implemented (Singleton Pattern) |
| **📚 Loan Books** | Users can **borrow books within their loan limit** after login. | ✅ Implemented |
| **📝 Book Reservations** | Users can **reserve books currently on loan**. | ✅ Implemented (Repository Pattern) |
| **📤 Return Books** | Users can return books **without logging in**. The system automatically updates their status. | ✅ Implemented |
| **🔗 External Library Access** | If a book is unavailable, users can **borrow from an external library**. | ✅ Implemented (Adapter Pattern) |
| **🔑 User Login** | Different access for **students, professors, and librarians**. | ✅ Implemented (Singleton Pattern) |
| **📩 Receive Requests** | Librarians can **review and approve book requests**. | ✅ Implemented (Observer Pattern) |
| **📚 Manage Books** | Librarians can **add, edit, and remove books** from the system. | ✅ Implemented |

---

## 📌 System Architecture

### 🏗 Applied **Design Patterns**

| **Pattern** | **Application** | **Implementation Area** |
| --- | --- | --- |
| **Strategy → Template Method** | Improved **book search function** for reusability and maintainability. | `BookSearcher.java` |
| **Singleton** | Ensures a **single instance of user sessions**. | `User.java`, `LoginDialog.java` |
| **Observer** | Notifies **librarians of new book requests** in real-time. | `RequestSearcher.java`, `RequestAdmin.java` |
| **State** | Implements **Dark Mode** for the admin page. | `AdminUI.java` |
| **MVC (Model-View-Controller)** | Enhances **code organization and maintainability**. | `LibrarySystem.java` |
| **Repository** | Efficient **book reservation handling**. | `BookRepository.java` |
| **Adapter** | Enables **external library book access** within the system. | `ExternalLibraryAdapter.java` |

---

## 🛠 Technologies Used

🔹 **Programming Languages**: Java

🔹 **Frameworks & Libraries**: Java Swing (GUI), Java Collections API

🔹 **Database**: CSV-based storage

🔹 **Development Tools**: IntelliJ IDEA, GitHub, Draw.io (Diagrams)

---

## 📑 System Design

## **1️⃣ Use Case Diagrams**

<img width=70% alt="Use Case Diagram" src="https://github.com/user-attachments/assets/011c8a9a-ee9f-4693-924c-795ee413d873" />

Illustrating **user interactions** with the system, including scenarios for:

- 📚 **Requesting a Book**: Users can enter book details such as name, author, and publisher on a dedicated request page.
- 📩 **Receiving Book Requests**: Librarians receive and process book requests, updating their status within the system.
- 🔍 **Searching for a Book**: Users can search for books by various criteria and view detailed information about availability and location.
- 📖 **Loaning and Returning Books**: Includes sequences for loan approvals, book returns, and handling unavailable books.

---

## **2️⃣ Use Case Scenarios**

The following table outlines detailed **use case scenarios**, including key participants, flow of events, and system quality requirements.

### **📌 Request a Book**

| **Aspect** | **Details** |
| --- | --- |
| **Participating Actor** | 📌 User |
| **Flow of Events** | 1. The user enters the book request page. 2. The system displays fields for book name, author, publisher, and published date. 3. The user fills in the details and submits the request. |
| **Entry Condition** | The book requested is not available in the library. |
| **Exit Condition** | The system successfully records and receives the book request. |
| **Quality Requirements** | - 🖥️ **Interface**: The system must provide a responsive and user-friendly request page. - 🎯 **Accuracy**: User input should be validated before submission. - 🔔 **Immediate Notification**: The system should instantly notify the librarian of a new book request. |

---

### **📌 Receive a Book Request**

| **Aspect** | **Details** |
| --- | --- |
| **Participating Actor** | 📌 Librarian |
| **Flow of Events** | 1. The librarian logs into the system and navigates to the book request section. 2. The system displays a list of requested books. 3. The librarian reviews and updates the book request status. |
| **Entry Condition** | - The librarian is logged into the system. - A book request has been submitted by a user. |
| **Exit Condition** | The librarian successfully acknowledges and processes the request. |
| **Quality Requirements** | - ⏳ **Prompt Response**: The system must notify the librarian of new requests without delay. - 💬 **User Communication**: The librarian should be able to provide feedback to users regarding their requests. |

---

### **📌 Searching for a Book**

| **Aspect** | **Details** |
| --- | --- |
| **Participating Actor** | 📌 User |
| **Flow of Events** | 1. The system presents a menu with options: search books, request books, rent books, reserve books, return books. 2. The user selects the **search books** option. 3. The system displays input fields for search criteria (book name, author, publisher, published date). 4. The user enters search criteria. 5. The system retrieves and displays matching books with details (title, author, publisher, year, loan availability). 6. The user notes the book number for future reference. |
| **Entry Condition** | The user is logged into the system and wishes to check book availability. |
| **Exit Condition** | The user successfully retrieves book information. |
| **Quality Requirements** | - 📜 **Informative**: The system should clearly present book details. - 🔍 **Efficient Search**: Users should be able to filter by various parameters (title, author, publisher, etc.). |

---

### **📌 Loan a Book**

| **Aspect** | **Details** |
| --- | --- |
| **Participating Actor** | 📌 User |
| **Flow of Events** | 1. The user selects **search books**. 2. The system prompts for search input. 3. The user enters the **book number** for the book they wish to borrow. 4. The system validates the request, updates the book's availability status, assigns the loan to the user, and displays a success message. |
| **Entry Condition** | The user is logged in and has found an available book. |
| **Exit Condition** | The user successfully borrows the book, and the system updates the loan record. |
| **Quality Requirements** | - ✅ **Real-time Updates**: The system must reflect loan status instantly. - 🔄 **Accurate Tracking**: User accounts should maintain a record of borrowed books. |

---

### **📌 Fail to Loan a Book**

| **Aspect** | **Details** |
| --- | --- |
| **Participating Actor** | 📌 User |
| **Flow of Events** | 1. The user selects **search books**. 2. The system prompts for search input. 3. The user enters the **book number**. 4. If the book is already loaned, the system displays an error message: **"The book is already loaned."** |
| **Entry Condition** | The user is logged in and attempts to borrow a book. |
| **Exit Condition** | The user is informed that the book is unavailable. |
| **Quality Requirements** | - ⚠️ **Clear Feedback**: The system should display a **precise message** when a book is unavailable. - 🔍 **Up-to-Date Information**: The system must reflect real-time loan status. |

---

### 3️⃣ **Class Diagram**

<img width=70% alt="image" src="https://github.com/user-attachments/assets/e98d64c3-57bc-45e0-b0b9-478bf68f9cb0" />  
<img width=70% alt="image" src="https://github.com/user-attachments/assets/9ea3c0d1-fe73-404a-9a31-9b8898ebe879" />

📌 **Overview**

The class diagram defines the **structure of the system**, showing key classes and their relationships.

- Core entities such as **User, Librarian, and Book** are included, with **inheritance, association, and dependency relationships** clearly illustrated.
- The first diagram focuses on **users and book-related classes**, while the second diagram covers **loan and request processing classes**.

---

### 4️⃣ **Sequence Diagram**

<img width=70% alt="image" src="https://github.com/user-attachments/assets/8b17d59b-483c-491b-8c65-f3e62da10e90" />

📌 **Overview**

The sequence diagram illustrates the **book loan and return process**, depicting the **flow of messages between system components**.

- When a user requests a book loan, the system verifies the book's availability and determines loan eligibility.
- During the return process, the system updates the loan status and notifies any users with pending reservations.
- The primary interaction follows the sequence: **User → Library System → Book DB → Librarian**.

---

### 5️⃣ **State Diagram**

<img width=70% alt="image" src="https://github.com/user-attachments/assets/539c3cfd-834b-4732-bc18-d31dfd08c13a" />

📌 **Overview**

The state diagram represents **the transitions of a book's status** within the library system.

- A book moves through the states: **Available → Reserved → Loaned → Returned**.
- Specific states trigger **automatic notifications** or system messages indicating book availability.
- If a book is returned and a reservation exists, the system **automatically assigns the book to the next user in line**.

---

## 🔄 Implementation Details

### 🔍 **Book Search (Template Method Pattern)**

🔹 **Before Refactoring:** Code redundancy for different search types.

🔹 **After Refactoring:** Unified **abstract `BookSearcher` class**, reducing duplicate code.

### 📖 **Book Loan (Singleton Pattern)**

🔹 **Before Refactoring:** Multiple instances of the `User` class.

🔹 **After Refactoring:** Implemented **singleton instance**, ensuring a single session per user.

### 🛠 **Admin Page (State Pattern)**

🔹 **Before Refactoring:** No **Dark Mode** support.

🔹 **After Refactoring:** Implemented **state switching** between **Light Mode** and **Dark Mode**.

---

## 🖥️ Installation & Setup

### 1️⃣ Clone Repository

```bash
git clone https://github.com/MASEOKJAE/OODP_Project.git
cd OODP_Project
```

### 2️⃣ Compile & Run

```bash
javac -d bin src/*.java
java -cp bin Main
```

### 3️⃣ **Admin Login Credentials**

- **Username:** `1234`
- **Password:** `1234`

---

## 📷 Screenshots

### 🔑 **Login Page**

### 📖 **Book Search Page**

### 📝 **Book Reservation Page**

### 🔗 **External Library Access**

---

## 🤝 Contributing

We welcome contributions! 🛠️

1. **Fork** this repository
2. **Create a feature branch** (`git checkout -b feature-branch`)
3. **Commit your changes** (`git commit -m "Add new feature"`)
4. **Push to the branch** (`git push origin feature-branch`)
5. **Submit a Pull Request**

---

## 📞 Contact

📩 **Email:** maasj7514@gmail.com

📂 **GitHub Repository:** [H-Library System](https://github.com/MASEOKJAE/OODP_Project.git)
