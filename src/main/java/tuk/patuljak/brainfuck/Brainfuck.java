package tuk.patuljak.brainfuck;

public class Brainfuck {
    static void main(final String[] programs) {
        for(var program : programs) {
            int ptr = 0,
                helper = 0,
                skippedLoops = 0,
                lastLoopStart = 0,
                ninetyOne = 91,
                instructionIndex = 0;
            var memory = new int[ninetyOne];
            var loopStarts = memory.clone();

            for(var instructions = program.toCharArray(); instructionIndex < instructions.length;) {
                var currentInstruction = instructions[instructionIndex++];

                helper = (skippedLoops > 0)
                    ? skippedLoops += (currentInstruction == ninetyOne)
                        ? 1
                        : (currentInstruction > ninetyOne)
                            ? -1
                            : 0
                    : (currentInstruction == 43)
                        ? ++memory[ptr]
                        : (currentInstruction == 45)
                            ? --memory[ptr]
                            : (currentInstruction == 60)
                                ? --ptr
                                : (currentInstruction == 62)
                                    ? ++ptr
                                    :(currentInstruction > ninetyOne)
                                        ? (memory[ptr] != 0)
                                            ? instructionIndex = loopStarts[lastLoopStart]
                                            : --lastLoopStart
                                        : (currentInstruction == ninetyOne)
                                            ? (memory[ptr] != 0)
                                                ? loopStarts[++lastLoopStart] = instructionIndex
                                                : ++skippedLoops
                                            : helper;

                System.out.print(
                    (currentInstruction == 46)
                        ? (char) memory[ptr]
                        : "");
            }
        }
    }
}
