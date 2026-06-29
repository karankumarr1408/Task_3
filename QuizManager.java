import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizManager {
    private List<Question> questions;
    private Map<Integer, Integer> userAnswers;
    private int score;
    private int currentQuestion;

    public QuizManager() {
        questions = new ArrayList<>();
        userAnswers = new HashMap<>();
        score = 0;
        currentQuestion = 0;
        loadQuestions();
    }

    private void loadQuestions() {

        questions.add(new Question(
            "Which keyword is used to create a class in Java?",
            new String[]{"function", "class", "define", "struct"},
            2
        ));

        questions.add(new Question(
            "What is the size of an int in Java?",
            new String[]{"8 bytes", "2 bytes", "4 bytes", "16 bytes"},
            3
        ));

        questions.add(new Question(
            "Which method is the entry point of a Java program?",
            new String[]{"start()", "main()", "run()", "init()"},
            2
        ));

        questions.add(new Question(
            "Which keyword is used to inherit a class in Java?",
            new String[]{"implements", "inherits", "extends", "super"},
            3
        ));
    }

    public Question getNextQuestion() {
        if (currentQuestion < questions.size()) {
            return questions.get(currentQuestion);
        }
        return null;
    }

    public boolean submitAnswer(int answer) {
        Question q = questions.get(currentQuestion);
        boolean correct = q.isCorrect(answer);

        userAnswers.put(currentQuestion, answer);

        if (correct) {
            score++;
            System.out.println("✓ Correct!");
        } else {
            System.out.println("✗ Wrong! Correct answer: "
                    + q.getCorrectAnswerLetter() + ". " + q.getCorrectAnswerText());
        }

        currentQuestion++;
        return correct;
    }

    public void showResult(String playerName) {
        int total = questions.size();
        int wrong = total - score;
        double pct = ((double) score / total) * 100;

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║              QUIZ COMPLETED!             ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.println("  Player   : " + playerName);
        System.out.println("  Score    : " + score + " / " + total);
        System.out.println("  Correct  : " + score);
        System.out.println("  Wrong    : " + wrong);
        System.out.printf("  Percent  : %.1f%%%n", pct);
        System.out.println("  Grade    : " + getGrade(pct));
        System.out.println("──────────────────────────────────────────");
        System.out.println("  " + getRemark(pct));
        System.out.println("╚══════════════════════════════════════════╝");

        showReview();
    }

    private void showReview() {
        System.out.println("\nAnswer Review:");
        System.out.println("──────────────────────────────────────────");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            int userAns = userAnswers.get(i);
            boolean correct = q.isCorrect(userAns);
            String[] letters = {"A", "B", "C", "D"};

            System.out.printf(
                "Q%-2d | Your Answer: %s | Correct: %s | %s%n",
                (i + 1),
                letters[userAns - 1],
                q.getCorrectAnswerLetter(),
                correct ? "Correct" : "Wrong"
            );
        }

        System.out.println("──────────────────────────────────────────");
    }

    private String getGrade(double pct) {
        if (pct >= 90) return "A+";
        else if (pct >= 80) return "A";
        else if (pct >= 70) return "B";
        else if (pct >= 60) return "C";
        else if (pct >= 50) return "D";
        else return "F";
    }

    private String getRemark(double pct) {
        if (pct >= 90) return "Outstanding! You are a Java Expert!";
        else if (pct >= 70) return "Great job! Keep it up!";
        else if (pct >= 50) return "Good try! Practice more!";
        else return "Don't give up! Try again!";
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public int getCurrentNumber() {
        return currentQuestion + 1;
    }

    public boolean isFinished() {
        return currentQuestion >= questions.size();
    }
}