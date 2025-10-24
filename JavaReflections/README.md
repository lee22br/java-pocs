# Java Reflections Costs

POC for shows how cost is to use Java Reflection 

## Simple Benchmark
Array Class instantiate by Reflection
Array Person instantiate normally


## Results
| Benchmark | (arraySize) | Mode | Cnt |      Score | Error | Units |
| :--- | :--- | :--- | :--- |-----------:| :--- | :--- |
| App.instances | 16 | avgt | (N/A) |    275,452 | (N/A) | ns/op |
| App.instances | 64 | avgt | (N/A) |   1084,038 | (N/A) | ns/op |
| App.instances | 512 | avgt | (N/A) |   9185,122 | (N/A) | ns/op |
| App.reflecTionsClassCached | 16 | avgt | (N/A) |    981,613 | (N/A) | ns/op |
| App.reflecTionsClassCached | 64 | avgt | (N/A) |   3916,465 | (N/A) | ns/op |
| App.reflecTionsClassCached | 512 | avgt | (N/A) |  32843,650 | (N/A) | ns/op |
| App.reflections | 16 | avgt | (N/A) |  11511,815 | (N/A) | ns/op |
| App.reflections | 64 | avgt | (N/A) |  45126,259 | (N/A) | ns/op |
| App.reflections | 512 | avgt | (N/A) | 364681,292 | (N/A) | ns/op |