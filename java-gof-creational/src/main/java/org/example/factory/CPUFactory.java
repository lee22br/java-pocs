package org.example.factory;

interface CPUFactory {

    Instruction createInstruction();
    Register createRegister();
}