package com.smartinterview.portal.config;

import com.smartinterview.portal.entity.Question;
import com.smartinterview.portal.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final QuestionRepository questionRepository;

    @Override
    public void run(String... args) {
        if (questionRepository.count() > 0) return;
        questionRepository.saveAll(List.of(
                q("What is OOPs?", "OOPs is a paradigm based on objects, encapsulation, inheritance and polymorphism.", "OOPs", "Easy", "Java Developer", "Infosys", true),
                q("Difference between ArrayList and LinkedList", "ArrayList is dynamic array; LinkedList is doubly-linked list with faster insertions.", "Collections", "Medium", "Java Developer", "Wipro", true),
                q("Difference between HashMap and Hashtable", "HashMap is non-synchronized and allows one null key; Hashtable is synchronized and no null keys.", "Collections", "Medium", "Java Developer", "TCS", true),
                q("What is multithreading?", "Multithreading allows concurrent execution of threads to improve CPU utilization.", "Multithreading", "Medium", "Java Developer", "Accenture", false),
                q("What is Spring Boot?", "Spring Boot simplifies Spring app setup through auto-configuration and starters.", "Spring Boot", "Easy", "Java Developer", "Capgemini", true),
                q("What is JSX?", "JSX is XML-like syntax that compiles into React.createElement calls.", "JSX", "Easy", "React Developer", "Cognizant", true),
                q("Difference between State and Props", "Props are read-only inputs; state is mutable local component data.", "State", "Easy", "React Developer", "TCS", true),
                q("What are React Hooks?", "Hooks are functions like useState/useEffect to use state and lifecycle in functional components.", "Hooks", "Medium", "React Developer", "Deloitte", true),
                q("What is useEffect?", "useEffect manages side effects such as API calls and subscriptions.", "Hooks", "Medium", "React Developer", "Infosys", false),
                q("What is Redux?", "Redux is a predictable state container for centralized app state.", "Redux", "Hard", "React Developer", "HCL", false),
                q("What is normalization?", "Normalization organizes data to reduce redundancy and improve integrity.", "SQL", "Medium", "Data Analyst", "EY", true),
                q("Supervised vs unsupervised learning", "Supervised uses labeled data; unsupervised finds hidden patterns in unlabeled data.", "ML Basics", "Medium", "Data Analyst", "KPMG", true),
                q("What is SQL JOIN?", "JOIN combines rows from multiple tables based on related columns.", "SQL", "Easy", "Data Analyst", "TCS", true),
                q("What is Power BI?", "Power BI is a Microsoft BI tool for dashboards and interactive reports.", "BI Tools", "Easy", "Data Analyst", "Genpact", false),
                q("Explain CI/CD in DevOps", "CI/CD automates integration, testing, and deployment for faster, reliable releases.", "CI/CD", "Medium", "DevOps Engineer", "IBM", true),
                q("What is Docker?", "Docker packages applications and dependencies as portable containers.", "Containers", "Easy", "DevOps Engineer", "Amazon", true),
                q("What is a Python decorator?", "Decorator wraps a function to extend behavior without changing original code.", "Functions", "Medium", "Python Developer", "Google", false),
                q("What is list comprehension?", "Concise syntax to create lists from iterables with optional filters.", "Core Python", "Easy", "Python Developer", "Zoho", true),
                q("What is JDBC?", "JDBC is Java API to connect and execute SQL with relational databases.", "JDBC", "Medium", "Full Stack Developer", "Oracle", false),
                q("Difference between monolith and microservices", "Monolith is single deployable unit; microservices are independent services.", "Architecture", "Hard", "Full Stack Developer", "Paytm", true)
        ));
    }

    private Question q(String title, String answer, String topic, String difficulty, String role, String company, boolean frequent) {
        return Question.builder().title(title).answer(answer).topic(topic).difficulty(difficulty)
                .role(role).company(company).frequentlyAsked(frequent).build();
    }
}
