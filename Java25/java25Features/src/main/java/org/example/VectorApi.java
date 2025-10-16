package org.example;

import jdk.incubator.vector.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgs = {
        "-Xms16g",
        "--enable-preview",
        "-XX:-UseSuperWord",
        "--add-modules=jdk.incubator.vector"
})
public class VectorApi {

    @Param({ "32", "64", "128", "512"})
    int arraySize;

    float[] a ;
    float[] b ;
    float[] c ;
    @Setup(Level.Trial)
    public void setup() {
        Random random = new Random();
        a = new float[arraySize];
        b = new float[arraySize];
         c = new float[arraySize];

        for (int i = 0; i < arraySize; i++) {
            a[i] = random.nextFloat();
            b[i] = random.nextFloat();
        }
    }


    private Object scalarComputation(float[] a, float[] b, float[] c) {
        for (int i = 0; i < a.length; i++) {
            c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f;
            //System.out.println("Result: " + c[i]);
        }
        return null;
    }

    static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

    private Object vectorComputation(float[] a, float[] b, float[] c) {
        int i = 0;
        int upperBound = SPECIES.loopBound(a.length);
        for (; i < upperBound; i += SPECIES.length()) {
            // FloatVector va, vb, vc;
            var va = FloatVector.fromArray(SPECIES, a, i);
            var vb = FloatVector.fromArray(SPECIES, b, i);
            var vc = va.mul(va)
                    .add(vb.mul(vb))
                    .neg();
            vc.intoArray(c, i);
        }
        return null;
    }

    @Benchmark
    public void scalar(Blackhole bh) {
        bh.consume(scalarComputation(a,b,c));
    }
    @Benchmark
    public void vectorial(Blackhole bh) {
        bh.consume(vectorComputation(a,b,c));
    }

    void main() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(VectorApi.class.getSimpleName())
                .warmupIterations(0)
                .measurementIterations(1)
                .forks(1)
                .threads(1)
                .build();
        new Runner(opt).run();
    }
}
