package org.example.factory;

// --- x86 Implementation ---
class X86Instruction implements Instruction {
    public void emit() {
        System.out.println("EMIT: MOV EAX, 1 (x86 OpCode)");
    }
}

class X86Register implements Register {
    public String getName() {
        return "EAX / EBX";
    }
}

// --- ARM Implementation ---
class ARMInstruction implements Instruction {
    public void emit() {
        System.out.println("EMIT: MOV R0, #1 (ARM OpCode)");
    }
}

class ARMRegister implements Register {
    public String getName() {
        return "R0 / R1";
    }
}
