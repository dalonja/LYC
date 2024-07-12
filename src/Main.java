import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder inputBuilder = new StringBuilder();

        System.out.println("Enter lines of code (type 'exit' to finish):");
        while (true) {
            String line = scanner.nextLine();
            if (line.trim().equals("exit")) {
                break;
            }
            inputBuilder.append(line).append("\n");
        }
        String input = inputBuilder.toString();
        Lexer lexer = new Lexer(input);
        String tokensAsString = "";
        Token token;
        while ((token = lexer.getNextToken()).type != TokenType.EOF) {
            tokensAsString += token.value + " ";
        }
        System.out.println("Tokens as string: " + tokensAsString.trim());
    }
}

