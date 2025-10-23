# Java Reflections Costs

POC for shows how cost is to use Java Reflection 

## Simple Benchmark
Array Class instantiate by Reflection
Array Person instantiate normally


## Results
| Benchmark | (arraySize) | Mode | Cnt |   Score   | Error | Units |
| :--- | :--- | :--- | :--- |---------:| --- | :--- |
| App.instances | 8 | avgt | (N/A) |  136,675  | (N/A) | ns/op |
| App.instances | 16 | avgt | (N/A) |  288,489  | (N/A) | ns/op |
| App.reflections | 8 | avgt | (N/A) | 5797,649  | (N/A) | ns/op |
| App.reflections | 16 | avgt | (N/A) | 11853,550 | (N/A) | ns/op |