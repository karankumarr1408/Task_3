public class Question {
    private String question;
    private String[] options;      
    private int correctOption;     

    public Question(String question, String[] options, int correctOption) {
        this.question      = question;
        this.options       = options;
        this.correctOption = correctOption;
    }

    public void displayQuestion(int qNumber) {
        System.out.println("\n─────────────────────────────────────────");
        System.out.println("Q" + qNumber + ". " + question);
        System.out.println("─────────────────────────────────────────");
        System.out.println("   A. " + options[0]);
        System.out.println("   B. " + options[1]);
        System.out.println("   C. " + options[2]);
        System.out.println("   D. " + options[3]);
        System.out.println("─────────────────────────────────────────");
    }

    public boolean isCorrect(int userAnswer) {
        return userAnswer == correctOption;
    }

    public String getCorrectAnswerLetter() {
        String[] letters = {"A", "B", "C", "D"};
        return letters[correctOption - 1];
    }

    public String getCorrectAnswerText() {
        return options[correctOption - 1];
    }

    public String getQuestion() { return question; }
}