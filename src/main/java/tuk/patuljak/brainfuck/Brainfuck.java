package tuk.patuljak.brainfuck;

public class Brainfuck {
    static void main(String[] programs) {
        for(var program : programs) {
            char ptr = 0,
                helper = 0,
                skippedLoops = 0,
                lastLoopStart = 0,
                ninetyOne = 91,
                instructionIndex = 0,
                currentInstruction;

            for(
                char[]  memory = new char[ninetyOne],
                        instructions = program.toCharArray();
                instructionIndex < instructions.length;
                currentInstruction = instructions[instructionIndex++],
                helper = (skippedLoops > 0)
                    ? skippedLoops += (currentInstruction == ninetyOne)
                        ? 1
                        : (currentInstruction > ninetyOne)
                            ? -1
                            : 0
                    : (currentInstruction < 44)
                        ? ++memory[ptr]
                        : (currentInstruction < 46)
                            ? --memory[ptr]
                            : (currentInstruction == 60)
                                ? --ptr
                                : (currentInstruction == 62)
                                    ? ++ptr
                                    :(currentInstruction > ninetyOne)
                                        ? (memory[ptr] != 0)
                                            ? instructionIndex = memory[ninetyOne - lastLoopStart]
                                            : --lastLoopStart
                                        : (currentInstruction == ninetyOne)
                                            ? (memory[ptr] != 0)
                                                ? memory[ninetyOne - ++lastLoopStart] = instructionIndex
                                                : ++skippedLoops
                                            : helper,
                System.out.print(
                    (currentInstruction == 46)
                        ? memory[ptr]
                        : "")
            );
        }
    }
}
