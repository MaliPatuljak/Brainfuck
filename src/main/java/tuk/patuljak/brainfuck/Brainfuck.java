package tuk.patuljak.brainfuck;

public class Brainfuck {
    static void main(final String[] programs) {
        for(var program : programs) {
            int ptr = 0,
                lastLoopStart = 0,
                instructionIndex = 0;
            var memory = new int[100];
            var loopStarts = new int[10];
            var instructions = program.toCharArray();

            while(instructionIndex < instructions.length) {
                var currentInstruction = instructions[instructionIndex++];

                switch (currentInstruction) {
                    case '+' -> ++memory[ptr];
                    case '-' -> --memory[ptr];
                    case '>' -> ++ptr;
                    case '<' -> --ptr;
                    case '.' -> System.out.print((char) memory[ptr]);
                    case '[' -> {
                        // Check loop condition
                        if(memory[ptr] != 0) {
                            loopStarts[++lastLoopStart] = instructionIndex;
                        } else {
                            // Skip loop
                            instructionIndex = closingBracketIndexOf(instructions, instructionIndex) + 1;
                        }
                    }
                    case ']' -> {
                        // Check loop condition
                        if(memory[ptr] != 0) {
                            instructionIndex = loopStarts[lastLoopStart];
                        } else {
                            // Leave loop
                            --lastLoopStart;
                        }
                    }
                }
            }
        }
    }

    static int closingBracketIndexOf(char[] text, int openBracketIndex) {
        var openBrackets = 1;
        var currentIndex = openBracketIndex;

        while(openBrackets > 0 && ++currentIndex < text.length) {
            var currentCharacter = text[currentIndex];

            switch (currentCharacter) {
                case '[' -> ++openBrackets;
                case ']' -> --openBrackets;
            }
        }

        return currentIndex;
    }
}
