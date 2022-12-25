package tuk.patuljak.brainfuck;

public class Brainfuck {
    static void main(final String[] programs) {
        for(var program : programs) {
            int ptr = 0,
                skippedLoops = 0,
                lastLoopStart = 0,
                instructionIndex = 0;
            var memory = new int[100];
            var loopStarts = new int[10];
            var instructions = program.toCharArray();

            while(instructionIndex < instructions.length) {
                var currentInstruction = instructions[instructionIndex++];

                if(skippedLoops > 0)
                    skippedLoops += (currentInstruction == 91)
                        ? 1
                        : (currentInstruction == 93)
                        ? -1
                        : 0;
                else {
                    if(currentInstruction == 43)
                        ++memory[ptr];

                    if(currentInstruction == 45)
                        --memory[ptr];

                    if(currentInstruction == 60)
                        --ptr;

                    if(currentInstruction == 62)
                        ++ptr;

                    if(currentInstruction == 46)
                        System.out.print((char) memory[ptr]);

                    if(currentInstruction == 93)
                        if(memory[ptr] != 0) {
                            instructionIndex = loopStarts[lastLoopStart];
                        } else {
                            --lastLoopStart;
                        }

                    if(currentInstruction == 91)
                        if(memory[ptr] != 0) {
                            loopStarts[++lastLoopStart] = instructionIndex;
                        } else {
                            ++skippedLoops;
                        }
                }
            }
        }
    }
}
