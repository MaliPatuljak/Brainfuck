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
                    case 43 -> ++memory[ptr];
                    case 45 -> --memory[ptr];
                    case 62 -> ++ptr;
                    case 60 -> --ptr;
                    case 46 -> System.out.print((char) memory[ptr]);
                    case 91 -> {
                        if(memory[ptr] != 0) {
                            loopStarts[++lastLoopStart] = instructionIndex;
                        } else {
                            instructionIndex = closingBracketIndexOf(instructions, instructionIndex) + 1;
                        }
                    }
                    case 93 -> {
                        if(memory[ptr] != 0) {
                            instructionIndex = loopStarts[lastLoopStart];
                        } else {
                            --lastLoopStart;
                        }
                    }
                }
            }
        }
    }

    static int closingBracketIndexOf(
        final char[] text,
        final int openBracketIndex
    ) {
        var openBrackets = 1;
        var currentIndex = openBracketIndex;

        while(openBrackets > 0 && ++currentIndex < text.length) {
            var currentCharacter = text[currentIndex];

            switch (currentCharacter) {
                case 91 -> ++openBrackets;
                case 93 -> --openBrackets;
            }
        }

        return currentIndex;
    }
}
