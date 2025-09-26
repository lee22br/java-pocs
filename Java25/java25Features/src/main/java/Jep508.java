import jdk.incubator.vector.*;

public class Jep508 {

    void scalarComputation(float[] a, float[] b, float[] c) {
        for (int i = 0; i < a.length; i++) {
            c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f;
            System.out.println("Result: " + c[i]);
        }

    }

    static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

    void vectorComputation(float[] a, float[] b, float[] c) {
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
        for (; i < a.length; i++) {
            c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f;
            System.out.println("Result SPECIES: " + c[i]);
        }
    }
    void main(){
        float[] a = {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, };
        float[] b = {2.0f, 3.0f, 4.0f, 5.0f, 6.0f, };
        float[] c = new float[a.length];
        this.scalarComputation(a, b, c);
        this.vectorComputation(a, b, c);
    }
}
