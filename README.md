# Students App

A simple Android application for managing a student repository with CRUD operations (Create, Read, Update, Delete).

## 📱 Overview

The Students App is an Android application built with Kotlin that allows users to manage a list of students. The app stores data in memory and provides a clean, Material Design 3 interface for managing student information.

## ✨ Features

### Core Functionality
- **View Students List** - Browse all students in a scrollable RecyclerView
- **Add New Student** - Create new student records with validation
- **View Student Details** - See complete information for any student
- **Edit Student** - Update student information (all fields editable, including ID)
- **Delete Student** - Remove students with confirmation dialog
- **Checkbox Status** - Toggle and track student checked status

### Data Management
- In-memory database using Singleton pattern
- Data persists during app session (cleared when app closes)
- Automatic data refresh after all operations
- Handles ID changes correctly

### Validation & Error Handling
- Required fields: Name and ID
- Optional fields: Phone and Address
- Duplicate ID detection
- Student not found handling
- Inline error messages
- Success feedback with toast messages

## 🏗️ Architecture

### Screens (Activities)
1. **StudentsListActivity** - Main screen with RecyclerView and FloatingActionButton
2. **NewStudentActivity** - Form for adding new students
3. **StudentDetailsActivity** - Display student information
4. **EditStudentActivity** - Edit or delete existing students

### Data Model
- **Student** - Data class with fields: name, id, phone, address, isChecked, imageResId
- **Model** - Singleton class managing in-memory student database
- **StudentsAdapter** - RecyclerView adapter with ViewHolder pattern

### Navigation
```
StudentsListActivity (Launcher)
├── NewStudentActivity → Add student → Return to List
├── StudentDetailsActivity → View details → Return to List
    └── EditStudentActivity → Edit/Delete → Return to Details
```

## 🛠️ Technical Stack

- **Language:** Kotlin
- **Min SDK:** 33
- **Target SDK:** 36
- **UI Framework:** Android Views with Material Design 3 components
- **Build System:** Gradle with Kotlin DSL
- **Architecture Pattern:** Model-View (MV) with Singleton

### Key Libraries
- AndroidX Core KTX
- AppCompat
- Material Design 3
- RecyclerView
- ConstraintLayout

## 📂 Project Structure

```
app/
├── src/main/
│   ├── java/com/ofirandanael/studentapp/
│   │   ├── MainActivity.kt
│   │   ├── StudentsListActivity.kt
│   │   ├── NewStudentActivity.kt
│   │   ├── StudentDetailsActivity.kt
│   │   ├── EditStudentActivity.kt
│   │   ├── Constants.kt
│   │   ├── adapter/
│   │   │   └── StudentsAdapter.kt
│   │   ├── model/
│   │   │   ├── Student.kt
│   │   │   └── Model.kt
│   │   └── utils/
│   │       └── StudentExtensions.kt
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_students_list.xml
│   │   │   ├── activity_new_student.xml
│   │   │   ├── activity_student_details.xml
│   │   │   ├── activity_edit_student.xml
│   │   │   └── item_student.xml
│   │   ├── drawable/
│   │   │   └── student.jpg
│   │   └── values/
│   │       └── strings.xml
│   └── AndroidManifest.xml
└── build.gradle.kts
```

## 🚀 Getting Started

### Prerequisites
- Android Studio (latest version recommended)
- JDK 11 or higher
- Android SDK 33 or higher

### Installation

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd StudentApp
   ```

2. **Open in Android Studio:**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the StudentApp folder
   - Click "OK"

3. **Build the project:**
   - Wait for Gradle sync to complete
   - Build > Make Project (or Ctrl+F9)

4. **Run the app:**
   - Connect an Android device or start an emulator
   - Click "Run" (or Shift+F10)
   - Select your device

## 📖 Usage Guide

### Adding a New Student
1. Click the FloatingActionButton (+) on the Students List screen
2. Fill in the required fields:
   - **Name** (required)
   - **ID** (required, must be unique)
   - **Phone** (optional)
   - **Address** (optional)
3. Check the "Checked" box if needed
4. Click **SAVE** to add the student
5. Click **CANCEL** to discard changes

### Viewing Student Details
1. From the Students List, click on any student row
2. View all student information
3. Click **EDIT** to modify the student
4. Use Android back button to return to the list

### Editing a Student
1. From Student Details, click **EDIT**
2. Modify any fields (including ID)
3. Click **SAVE** to update
4. Click **DELETE** to remove the student (requires confirmation)
5. Click **CANCEL** to discard changes

### Toggle Checkbox
- Click the checkbox on any student row in the list
- Status updates immediately
- Persists across navigation

## ✅ Features Validation

### Validation Rules
- **Name:** Required, cannot be empty
- **ID:** Required, cannot be empty, must be unique
- **Phone:** Optional, numeric keyboard for easy input
- **Address:** Optional, multiline support

### Error Messages
- "Name is required" - When name field is empty
- "ID is required" - When ID field is empty
- "Student with this ID already exists" - When duplicate ID detected
- "Student not found" - When student cannot be loaded
- "Error: No student ID provided" - When Intent data is missing

### Success Messages
- "Student added successfully" - After creating new student
- "Student updated successfully" - After editing student
- "Student deleted successfully" - After deleting student

## 🎨 UI/UX Features

- **Material Design 3** components throughout
- **TextInputLayout** with inline error messages
- **CardView** for list items with elevation
- **ScrollView** for keyboard handling
- **Proper input types** (textPersonName, phone, textPostalAddress)
- **Confirmation dialog** before destructive actions
- **Toast feedback** for user actions
- **Accessibility** content descriptions

## 🧪 Testing

### Manual Test Cases

#### Test 1: Add Valid Student
- Enter all fields with valid data
- Expected: Student appears in list immediately

#### Test 2: Validation - Empty Name
- Leave name empty, fill ID
- Expected: Error "Name is required"

#### Test 3: Validation - Duplicate ID
- Enter ID that already exists
- Expected: Error "Student with this ID already exists"

#### Test 4: Edit Student
- Change student information
- Expected: Updates saved correctly

#### Test 5: Delete Student
- Click DELETE, confirm
- Expected: Student removed from list

#### Test 6: Cancel Operations
- Start add/edit, click CANCEL
- Expected: No changes saved

### Edge Cases Handled
✅ Student not found  
✅ No student ID in Intent  
✅ Empty optional fields  
✅ Duplicate ID detection  
✅ ID change validation  
✅ RecyclerView item recycling

## 📝 Data Model

### Student Data Class
```kotlin
data class Student(
    val name: String,
    val id: String,
    val phone: String,
    val address: String,
    val isChecked: Boolean = false,
    val imageResId: Int
)
```

### Model (Singleton)
```kotlin
Model.shared.getAllStudents(): List<Student>
Model.shared.getStudentById(id: String): Student?
Model.shared.addStudent(student: Student)
Model.shared.updateStudent(originalId: String, updatedStudent: Student): Boolean
Model.shared.deleteStudent(id: String): Boolean
Model.shared.studentExists(id: String): Boolean
Model.shared.toggleStudentChecked(id: String): Boolean
```

## 🔧 Configuration

### Constants
```kotlin
object Constants {
    const val EXTRA_STUDENT_ID = "com.ofirandanael.studentapp.EXTRA_STUDENT_ID"
    const val EXTRA_STUDENT_INDEX = "com.ofirandanael.studentapp.EXTRA_STUDENT_INDEX"
}
```

### Initial Data
- App pre-seeds with 20 dummy students on first launch
- All students use the same static image (student.jpg)


## 🐛 Known Limitations

- Data stored in memory only (cleared when app closes)
- All students share the same static image
- No image selection from gallery/camera
- No data persistence (no database or SharedPreferences)
- No search or filter functionality
- No sorting options


## 📄 License

This project is created for educational purposes as part of an Android development course.

## 👥 Authors

- Ofir and Anael

## 📅 Project Information

- **Created:** January 2026
- **Last Updated:** January 2026
- **Version:** 1.0
- **Status:** Complete

## 🙏 Acknowledgments

- Material Design 3 guidelines
- Android Developers documentation
- Kotlin documentation

---

**Note:** This is an educational project demonstrating basic Android development concepts including Activities, RecyclerView, data management, and Material Design implementation.

