import java.util.InputMismatchException;
import java.util.Scanner;

public class QuizApplication {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        printBanner();
        mainMenu();
        sc.close();
    }

    private static void printBanner() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║        SIMPLE QUIZ APPLICATION           ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    private static void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n─────────── MENU ───────────");
            System.out.println("  1. Start Quiz");
            System.out.println("  2. View Rules");
            System.out.println("  3. Exit");
            System.out.println("────────────────────────────");

            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1: startQuiz();    break;
                case 2: showRules();    break;
                case 3:
                    System.out.println("\n Thanks for playing! Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println(" Invalid choice. Enter 1-3.");
            }
        }
    }

    private static void showRules() {
        System.out.println("\n QUIZ RULES:");
        System.out.println("──────────────────────────────────────────");
        System.out.println("  1. There are 10 multiple choice questions.");
        System.out.println("  2. Each question has 4 options (A, B, C, D).");
        System.out.println("  3. Enter 1 for A, 2 for B, 3 for C, 4 for D.");
        System.out.println("  4. Each correct answer = 1 point.");
        System.out.println("  5. No negative marking.");
        System.out.println("  6. Your score and grade shown at the end.");
        System.out.println("──────────────────────────────────────────");
    }

    private static void startQuiz() {
        System.out.println("\n──── Start Quiz ────");
        System.out.print("Enter your name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println(" Name cannot be empty.");
            return;
        }

        System.out.println("\nWelcome, " + name + "! ");
        System.out.println("Get ready... Quiz is starting!");
        System.out.println("(Enter 1=A, 2=B, 3=C, 4=D)");

        QuizManager quiz = new QuizManager();

        while (!quiz.isFinished()) {
            Question q = quiz.getNextQuestion();
            System.out.printf("%n[Question %d of %d]",
                    quiz.getCurrentNumber(), quiz.getTotalQuestions());
            q.displayQuestion(quiz.getCurrentNumber());

            int answer = 0;
            while (answer < 1 || answer > 4) {
                answer = getIntInput("Your answer (1-4): ");
                if (answer < 1 || answer > 4) {
                    System.out.println(" Enter only 1, 2, 3, or 4!");
                }
            }
            quiz.submitAnswer(answer);
        }

        quiz.showResult(name);

        System.out.print("\nPlay again? (yes/no): ");
        String again = sc.nextLine().trim();
        if (again.equalsIgnoreCase("yes")) {
            startQuiz();
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int val = sc.nextInt();
                sc.nextLine();
                return val;
            } catch (InputMismatchException e) {
                System.out.println(" Invalid input! Enter a number.");
                sc.nextLine();
            }
        }
    }
}