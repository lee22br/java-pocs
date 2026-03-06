package org.example.factory;

public class Compiler {
    private Instruction instruction;
    private Register register;

    public Compiler(CPUFactory factory) {
        this.instruction = factory.createInstruction();
        this.register = factory.createRegister();
    }

    public void compile() {
        System.out.println("Targeting register: " + register.getName());
        instruction.emit();
    }

    public static void main(String[] args) {
        //for Arm
        CPUFactory targetCPU = new ARMFactory();

        Compiler myCompiler = new Compiler(targetCPU);
        myCompiler.compile();

        //for x86
        CPUFactory targetCPU2 = new X86Factory();
        myCompiler = new Compiler(targetCPU2);
        myCompiler.compile();
    }
}
