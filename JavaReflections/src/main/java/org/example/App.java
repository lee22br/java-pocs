package org.example;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgs = {
        "-XX:-TieredCompilation",
        "-Xms16g"
})
public class App 
{
    @Param({ "16", "64" })
    int arraySize;
    Class<?>[] arrayClass;
    Person[] arrayPerson;
    private static final Map<String, Class> cacheClass = new HashMap<>();
    private static final Map<String, Field> cacheField = new HashMap<>();

    @Setup(Level.Trial)
    public void setup() {
        arrayClass = new Class[arraySize];
        arrayPerson = new Person[arraySize];
    }


    @Benchmark
    public void reflections() throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < arraySize; i++) {
            arrayClass[i] = Person.class;
            Object person = arrayClass[i].newInstance();
            Field nameField = arrayClass[i].getDeclaredField("name");
            nameField.set(person,"Test"+i);
            Field ageField = arrayClass[i].getDeclaredField("age");
            ageField.set(person,i);
        }
    }

    @Benchmark
    public void instances() throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        for (int i = 0; i < arraySize; i++) {
            arrayPerson[i] = new Person();
            arrayPerson[i].setName("Test"+i);
            arrayPerson[i].setAge(i);
        }
    }

    @Benchmark
    public void reflecTionsClassCached() throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        for (int i = 0; i < arraySize; i++) {
            arrayClass[i] = getClassCached(Person.class, "Person");
            Object person = arrayClass[i].newInstance();
            Field nameField = getFieldCached(Person.class, "name");
            nameField.set(person,"Test"+i);
            Field ageField = getFieldCached(Person.class, "age");
            ageField.set(person,i);
        }
    }

    @Benchmark
    public void reflectionHandleMethod() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle getterNameMH = lookup.findGetter(
                Person.class,
                "name",
                String.class
        );
        MethodHandle getterAgeMH = lookup.findGetter(
                Person.class,
                "age",
                int.class
        );
        MethodHandle setterNameMH = lookup.findSetter(
                Person.class,
                "name",
                String.class
        );
        MethodHandle setterAgeMH = lookup.findSetter(
                Person.class,
                "age",
                int.class
        );
        for (int i = 0; i < arraySize; i++) {
            Object person = arrayClass[i].newInstance();
            String name = (String) getterNameMH.invoke((Person)person);
            setterNameMH.invoke((Person)person, "Test"+i);
            int age = (Integer)getterAgeMH.invoke((Person)person);
            setterAgeMH.invoke((Person)person,i);
        }
    }

    private static Class getClassCached(Class<?> classReflec, String classKey)  {
        Class classRet = cacheClass.get(classKey);
        if (null == classRet) {
            classRet = classReflec;
            cacheClass.put(classKey,classRet);
        }
        return  classRet;
    }

    private static Field getFieldCached(Class<?> classReflec, String fieldKey) throws NoSuchFieldException {
        Field fieldRet =cacheField.get(fieldKey);
        if (null == fieldRet) {
            fieldRet = classReflec.getDeclaredField(fieldKey);
            cacheField.put(fieldKey,fieldRet);
        }
        return fieldRet;
    }

    public static void main(String[] args) throws Throwable {
        Options opt = new OptionsBuilder()
                .include(App.class.getSimpleName())
                .warmupIterations(0)
                .measurementIterations(1)
                .forks(1)
                .threads(1)
                .build();
        new Runner(opt).run();
    }
}
