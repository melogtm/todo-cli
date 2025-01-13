# A Todo List in CLI (ft. JSON)

A simple excuse (CLI based project) to manipulate JSON with Java.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them

* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Maven](https://maven.apache.org/install.html)

### Installing and Using

```bash
mvn compile
mvn package

mvn exec:java
mvn exec:java -Dexec.args='list done'
mvn exec:java -Dexec.args='add "buy groceries"'
mvn exec:java -Dexec.args='list'
```
#### Commands

**Add a task:**  
`mvn exec:java -Dexec.args="add 'Task description'"`

**Update a task:**  
`mvn exec:java -Dexec.args="update <task_id> 'New task description'"`

**Delete a task:**  
`mvn exec:java -Dexec.args="delete <task_id>"`

**Mark a task as in progress:**  
`mvn exec:java -Dexec.args="mark_in_progress <task_id>"`

**Mark a task as done:**  
`mvn exec:java -Dexec.args="mark_done <task_id>"`

**List tasks:**  
`mvn exec:java -Dexec.args="list"`

**List tasks with a filter (todo, done or in_progress):**  
`mvn exec:java -Dexec.args="list <filter>"`

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details


