# Java Reflections Costs

POC for shows how cost is to use Java Reflection 

## Simple Benchmark
Array Class instantiate by Reflection
Array Person instantiate normally


## Results
| Benchmark | (arraySize) | Mode | Cnt | Score | Error | Units |
| :--- | ---: | :--- | :--- | ---: | :--- | :--- |
| App.instances | 16 | avgt | (N/A) | 295,402 | (N/A) | ns/op |
| App.instances | 64 | avgt | (N/A) | 1108,998 | (N/A) | ns/op |
| App.instances | 512 | avgt | (N/A) | 9443,315 | (N/A) | ns/op |
| App.reflecTionsClassCached | 16 | avgt | (N/A) | 982,395 | (N/A) | ns/op |
| App.reflecTionsClassCached | 64 | avgt | (N/A) | 3909,215 | (N/A) | ns/op |
| App.reflecTionsClassCached | 512 | avgt | (N/A) | 32864,175 | (N/A) | ns/op |
| App.reflectionMethodHandle | 16 | avgt | (N/A) | 2310,995 | (N/A) | ns/op |
| App.reflectionMethodHandle | 64 | avgt | (N/A) | 4127,677 | (N/A) | ns/op |
| App.reflectionMethodHandle | 512 | avgt | (N/A) | 300006,031 | (N/A) | ns/op |
| App.reflections | 16 | avgt | (N/A) | 11328,465 | (N/A) | ns/op |
| App.reflections | 64 | avgt | (N/A) | 43438,087 | (N/A) | ns/op |
| App.reflections | 512 | avgt | (N/A) | 373495,214 | (N/A) | ns/op |