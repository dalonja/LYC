import java.util.ArrayList;
import java.util.List;

// Token types
enum TokenType {
    KEYWORD, IDENTIFIER, NUMBER, CHAR, LPAREN, RPAREN, LBRACE, RBRACE, LBRACKET, RBRACKET,
    COMMA, SEMI, COLON, ASSIGN, PLUS, MINUS, TIMES, DIVIDE, MOD, EQ, NEQ, LT, GT, LE, GE,
    AND, OR, NOT, INC, DEC, TYPEID,
    EOF
}

// Token class
class Token {
    TokenType type;
    String value;

    Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }
}



// Lexer class
public class Lexer {
    private String input;
    private int pos;
    private Token currentToken;

    Lexer(String input) {
        this.input = input;
        this.pos = 0;
        this.currentToken = null;
    }

    Token getNextToken() {
        while (pos < input.length()) {
            char c = input.charAt(pos);
            if (Character.isWhitespace(c)) {
                pos++;
                continue;
            }
            if (c == '(') {
                currentToken = new Token(TokenType.LPAREN, "(");
                pos++;
                return currentToken;
            }
            if (c == ')') {
                currentToken = new Token(TokenType.RPAREN, ")");
                pos++;
                return currentToken;
            }
            if (c == '{') {
                currentToken = new Token(TokenType.LBRACE, "{");
                pos++;
                return currentToken;
            }
            if (c == '}') {
                currentToken = new Token(TokenType.RBRACE, "}");
                pos++;
                return currentToken;
            }
            if (c == '[') {
                currentToken = new Token(TokenType.LBRACKET, "[");
                pos++;
                return currentToken;
            }
            if (c == ']') {
                currentToken = new Token(TokenType.RBRACKET, "]");
                pos++;
                return currentToken;
            }
            if (c == ',') {
                currentToken = new Token(TokenType.COMMA, ",");
                pos++;
                return currentToken;
            }
            if (c == ';') {
                currentToken = new Token(TokenType.SEMI, ";");
                pos++;
                return currentToken;
            }
            if (c == ':') {
                currentToken = new Token(TokenType.COLON, ":");
                pos++;
                return currentToken;
            }
            if (c == '=') {
                if (pos + 1 < input.length() && input.charAt(pos + 1) == '=') {
                    currentToken = new Token(TokenType.EQ, "==");
                    pos += 2;
                    return currentToken;
                } else {
                    currentToken = new Token(TokenType.ASSIGN, "=");
                    pos++;
                    return currentToken;
                }
            }
            if (c == '+') {
                if (pos + 1 < input.length() && input.charAt(pos + 1) == '+') {
                    currentToken = new Token(TokenType.INC, "++");
                    pos += 2;
                    return currentToken;
                } else {
                    currentToken = new Token(TokenType.PLUS, "+");
                    pos++;
                    return currentToken;
                }
            }
            if (c == '-') {
                if (pos + 1 < input.length() && input.charAt(pos + 1) == '-') {
                    currentToken = new Token(TokenType.DEC, "--");
                    pos += 2;
                    return currentToken;
                } else {
                    currentToken = new Token(TokenType.MINUS, "-");
                    pos++;
                    return currentToken;
                }
            }
            if (c == '*') {
                currentToken = new Token(TokenType.TIMES, "*");
                pos++;
                return currentToken;
            }
            if (c == '/') {
                currentToken = new Token(TokenType.DIVIDE, "/");
                pos++;
                return currentToken;
            }
            if (c == '%') {
                currentToken = new Token(TokenType.MOD, "%");
                pos++;
                return currentToken;
            }
            if (c == '<') {
                if (pos + 1 < input.length() && input.charAt(pos + 1) == '=') {
                    currentToken = new Token(TokenType.LE, "<=");
                    pos += 2;
                    return currentToken;
                } else {
                    currentToken = new Token(TokenType.LT, "<");
                    pos++;
                    return currentToken;
                }

            }
            if (c == '>') {
                if (pos + 1 < input.length() && input.charAt(pos + 1) == '=') {
                    currentToken = new Token(TokenType.GE, ">=");
                    pos += 2;
                    return currentToken;
                } else {
                    currentToken = new Token(TokenType.GT, ">");
                    pos++;
                    return currentToken;
                }
            }
            if (c == '!') {
                if (pos + 1 < input.length() && input.charAt(pos + 1) == '=') {
                    currentToken = new Token(TokenType.NEQ, "!=");
                    pos += 2;
                    return currentToken;
                } else {
                    currentToken = new Token(TokenType.NOT, "!");
                    pos++;
                    return currentToken;
                }
            }
            if (c == '&') {
                if (pos + 1 < input.length() && input.charAt(pos + 1) == '&') {
                    currentToken = new Token(TokenType.AND, "&&");
                    pos += 2;
                    return currentToken;
                } else {
                    currentToken = new Token(TokenType.AND, "&");
                    pos++;
                    return currentToken;
                }
            }
            if (c == '|') {
                if (pos + 1 < input.length() && input.charAt(pos + 1) == '|') {
                    currentToken = new Token(TokenType.OR, "||");
                    pos += 2;
                    return currentToken;
                } else {
                    currentToken = new Token(TokenType.OR, "|");
                    pos++;
                    return currentToken;
                }
            }
            if (Character.isLetter(c)) {
                StringBuilder buffer = new StringBuilder();
                while (pos < input.length() && Character.isLetter(c)) {
                    buffer.append(c);
                    pos++;
                    c = input.charAt(pos);
                }
                String keyword = buffer.toString();
                if (keyword.equals("int") || keyword.equals("class") || keyword.equals("new") || keyword.equals("delete") || keyword.equals("typeid") || keyword.equals("template") || keyword.equals("namespace") || keyword.equals("using")) {
                    currentToken = new Token(TokenType.KEYWORD, keyword);
                } else {
                    currentToken = new Token(TokenType.IDENTIFIER, keyword);
                }
                return currentToken;
            }
            if (Character.isDigit(c)) {
                StringBuilder buffer = new StringBuilder();
                while (pos < input.length() && Character.isDigit(c)) {
                    buffer.append(c);
                    pos++;
                    c = input.charAt(pos);
                }
                currentToken = new Token(TokenType.NUMBER, buffer.toString());
                return currentToken;
            }
            error("unexpected character");
        }
        currentToken = new Token(TokenType.EOF, "EOF");
        return currentToken;
    }

    private void error(String message) {
        System.out.println("Error: " + message);
    }
}