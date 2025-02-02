# 📚 H-Library System

🔹 **Handong Global University | Object-Oriented Design Patterns**

🔹 **June 2023**

**Team B**

👤 **Dongheon Lee** | 21600490 | **Data Architect**

👤 **Sooyeong Kim** | 21800109 | **Technical Writer & Documentation Specialist**

👤 **Seokjae Ma** | 21800239 | **Lead Software Engineer**

👤 **Kihong Park** | 21800264 | **Software Engineer**

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

### 1️⃣ **Use Case Diagrams**

<img width=70% alt="image" src="https://github.com/user-attachments/assets/011c8a9a-ee9f-4693-924c-795ee413d873" />

Illustrating **user interactions** with the system. This includes scenarios for:

- **Requesting a Book**: Users can enter book details such as name, author, and publisher on a dedicated request page.
- **Receiving Book Requests**: Librarians receive and process book requests, updating their status within the system.
- **Searching for a Book**: Users can search for books by various criteria and view detailed information about availability and location.
- **Loaning and Returning Books**: Includes sequences for loan approvals, book returns, and handling unavailable books.

### 2️⃣ **Class Diagram**

<img width=70% alt="image" src="https://github.com/user-attachments/assets/e98d64c3-57bc-45e0-b0b9-478bf68f9cb0" />

<img width=70% alt="image" src="https://github.com/user-attachments/assets/9ea3c0d1-fe73-404a-9a31-9b8898ebe879" />

Representing the **system's structure** with major classes and their relationships. This diagram is divided into two parts due to its extensive nature.

### 3️⃣ **Sequence Diagram**

<img width=70% alt="image" src="https://github.com/user-attachments/assets/8b17d59b-483c-491b-8c65-f3e62da10e90" />

Depicting **book loan and return processes**, focusing on interactions between system components during these actions.

### 4️⃣ **State Diagram**

<img width=70% alt="image" src="https://github.com/user-attachments/assets/539c3cfd-834b-4732-bc18-d31dfd08c13a" />

Visualizing **book reservation status changes** from available to reserved, loaned, and returned.

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
git clone https://github.com/your-repository/H-Library-System.git
cd H-Library-System
```

### 2️⃣ Compile & Run

```bash
javac -d bin src/*.java
java -cp bin Main
```

### 3️⃣ **Admin Login Credentials**

- **Username:** `admin`
- **Password:** `password123`

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
