package org.example.factory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompilerTest {

    @Test
    @DisplayName("Generate x86 specific components when X86Factory is used")
    void testX86CompilationFamily() {
        CPUFactory x86Factory = new X86Factory();

        Instruction instruction = x86Factory.createInstruction();
        Register register = x86Factory.createRegister();

        assertInstanceOf(X86Instruction.class, instruction, "Instruction should be x86 type");
        assertInstanceOf(X86Register.class, register, "Register should be x86 type");
        assertEquals("EAX / EBX", register.getName());
    }

    @Test
    @DisplayName("Generate ARM specific components when ARMFactory is used")
    void testARMCompilationFamily() {
        CPUFactory armFactory = new ARMFactory();

        Instruction instruction = armFactory.createInstruction();
        Register register = armFactory.createRegister();

        assertInstanceOf(ARMInstruction.class, instruction, "Instruction should be ARM type");
        assertInstanceOf(ARMRegister.class, register, "Register should be ARM type");
        assertEquals("R0 / R1", register.getName());
    }

    @Test
    @DisplayName("Compiler use correctly with any factory")
    void testCompilerInitialization() {
        CPUFactory factory = new ARMFactory();
        Compiler compiler = new Compiler(factory);

        assertNotNull(compiler, "Compiler instance should be created");
        assertDoesNotThrow(() -> compiler.compile());
    }

    @Test
    void testCompilerCallsEmit() {
        CPUFactory mockFactory = mock(CPUFactory.class);
        Instruction mockInstruction = mock(Instruction.class);
        Register mockRegister = mock(Register.class);

        when(mockFactory.createInstruction()).thenReturn(mockInstruction);
        when(mockFactory.createRegister()).thenReturn(mockRegister);

        Compiler compiler = new Compiler(mockFactory);
        compiler.compile();

        verify(mockInstruction, times(1)).emit();
    }
}
