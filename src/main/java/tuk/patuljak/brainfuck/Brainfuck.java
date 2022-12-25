package tuk.patuljak.brainfuck;

public class Brainfuck {
    static void main(final String[] programs) {
        for(var program : programs) {
            int ptr = 0,
                openBrackets,
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
                            openBrackets = 1;

                            while(openBrackets > 0) {
                                currentInstruction = instructions[++instructionIndex];

                                openBrackets += (currentInstruction == 91)
                                    ? 1
                                    : (currentInstruction == 93)
                                        ? -1
                                        : 0;
                            }

                            ++instructionIndex;
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
}
