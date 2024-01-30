import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Question {
    private String questionText;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String questionText, List<String> options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = new ArrayList<>(options);  // Create a mutable list
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

class Quiz {
    private List<Question> questions;

    public Quiz(List<Question> questions) {
        this.questions = new ArrayList<>(questions);  // Create a mutable list
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Question getRandomQuestion() {
        Collections.shuffle(questions);
        return questions.get(0);
    }
}

class Student {
    private String name;
    private int score;

    public Student(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }
}

public class Prac71_OnlineQuizApplication {
    public static void main(String[] args) {
        // Create questions
        Question question1 = new Question("What is the largest mammal?",
                List.of("Elephant", "Blue Whale", "Giraffe", "Hippopotamus"), 1);
        Question question2 = new Question("Which planet is known as the 'Red Planet'?",
                List.of("Mars", "Venus", "Jupiter", "Saturn"), 0);
        Question question3 = new Question("What is the capital of Japan?",
                List.of("Beijing", "Seoul", "Tokyo", "Bangkok"), 2);
        Question question4 = new Question(
                "Which programming language is known for its 'write once, run anywhere' principle?",
                List.of("Java", "Python", "C++", "JavaScript"), 0);
        Question question5 = new Question("Who wrote 'Romeo and Juliet'?",
                List.of("Charles Dickens", "William Shakespeare", "Jane Austen", "Mark Twain"), 1);
        Question question6 = new Question("What is the powerhouse of the cell?",
                List.of("Mitochondria", "Nucleus", "Endoplasmic Reticulum", "Golgi Apparatus"), 0);
        Question question7 = new Question("What is the currency of Germany?",
                List.of("Euro", "Pound Sterling", "Yen", "Dollar"), 0);
        Question question8 = new Question("Which ocean is the largest?",
                List.of("Atlantic Ocean", "Indian Ocean", "Southern Ocean", "Pacific Ocean"), 3);
        Question question9 = new Question("Who developed the theory of relativity?",
                List.of("Isaac Newton", "Albert Einstein", "Galileo Galilei", "Nikola Tesla"), 1);
        Question question10 = new Question("What is the capital of Australia?",
                List.of("Sydney", "Melbourne", "Canberra", "Brisbane"), 2);

        // Create a quiz with the questions
        Quiz quiz = new Quiz(List.of(question1, question2, question3, question4, question5, question6, question7,
                question8, question9, question10));

        // Create a student
        Student student = new Student("John");

        // Take the quiz
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            Question currentQuestion = quiz.getRandomQuestion();
            System.out.println("Question " + (i + 1) + ": " + currentQuestion.getQuestionText());

            List<String> options = currentQuestion.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }

            // Get student's answer
            int selectedOption = getStudentAnswer();

            // Check if the answer is correct
            if (selectedOption - 1 == currentQuestion.getCorrectOptionIndex()) {
                System.out.println("Correct!");
                student.increaseScore();
            } else {
                System.out.println("Incorrect. The correct answer is: " +
                        options.get(currentQuestion.getCorrectOptionIndex()));
            }

            System.out.println();
        }

        // Display student's score
        System.out.println("Quiz completed! " + student.getName() + "'s score: " + student.getScore());
    }

    private static int getStudentAnswer() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Your answer: ");
                return scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}
