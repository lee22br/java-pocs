package org.example.factory;

class X86Factory implements CPUFactory {
    public Instruction createInstruction() { return new X86Instruction(); }
    public Register createRegister() { return new X86Register(); }
}

class ARMFactory implements CPUFactory {
    public Instruction createInstruction() { return new ARMInstruction(); }
    public Register createRegister() { return new ARMRegister(); }
}
