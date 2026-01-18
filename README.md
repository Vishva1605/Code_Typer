<div align="center">

# 🚀 CodeTyper  
### Automatic Java Code Typing for ACE Editors

<img src="https://img.shields.io/badge/Java-15%2B-orange" />
<img src="https://img.shields.io/badge/Platform-Windows-blue" />
<img src="https://img.shields.io/badge/Editor-ACE-success" />
<img src="https://img.shields.io/badge/Automation-Keyboard%20Simulation-purple" />

</div>

---

## ✨ Overview

**CodeTyper** is a Java-based automation tool that automatically **types Java programs into any ACE Editor**.

Instead of pasting code, the application simulates **real human typing**, ensuring:
- Proper indentation  
- Clean formatting  
- Well-structured Java code  

This is especially useful for **online coding platforms and ACE-based editors** where paste actions are restricted.

---

## 🎯 Key Features

- ⌨️ Automatically types Java programs character by character  
- 🧠 Preserves indentation and formatting  
- 🌍 Works with any ACE Editor  
- 🔑 Global keyboard shortcut to trigger typing  
- 🖥️ Runs directly from the terminal  
- ⚡ Smooth and reliable typing simulation  

---

## 🛠️ Built With

- Java (15+)
- JNativeHook (global keyboard listener)
- Java AWT Robot (keyboard simulation)

---

## 📂 Project Structure

CodeTyper_Java_Typer_App/
│
├── CodeTyper.java
├── README.md
└── lib/
    └── jnativehook-2.2.2.jar

---

## ⚙️ Requirements

### Java
- Java 15 or higher  
- Recommended: Java 17 or Java 21 (LTS)

> This project uses modern Java features such as **text blocks** and **enhanced switch expressions**.

### Library
- `jnativehook-2.2.2.jar`

---

## 🚀 Getting Started

### 1️⃣ Check Java Version

```bash
java -version
javac -version
