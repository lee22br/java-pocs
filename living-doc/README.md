## 📄 POC with Living Documentation (Living-Doc)

Using the [living-documentation](https://github.com/jboz/living-documentation/) tool to generate documentation like diagrams and glossaries directly from the source code.

---

### Results

#### 🖼️ Class Diagram

This diagram illustrates the structure of the **Order**, **Person**, and **Client** classes, showing their attributes, methods, and the relationships between them.

![alt text](https://raw.githubusercontent.com/lee22br/java-pocs/refs/heads/main/living-doc/diagram.png)


---

#### 📋 Object Glossary

The glossary details the attributes and data types of the classes represented in the diagram.

| Object Name | Attribute name | Type | Description | Constraints | Default Value |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Client** | address | String | | | |
| **Order** | id | int | | | |
| | client | Client | | | |
| | totalValue | double | | | |
| **Person** | name | String | | | |
| | email | String | | | |




