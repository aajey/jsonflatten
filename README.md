# jsonflatten

## Overview
The application lets user's flatten the JSON object. The current version removes the nested json objects and performs flattening by adding delimiter. The application uses dot (.) as delimiter when combining the neseted fields. The delimiter can be customized and any character or string can be used for delimiting. The application is written using Java 11 and maven for building. The tests are written in Junit5. The flattening functionality is written in file FlattenJsonIgnoreArrayImpl.java. The test coverage for this class is almost 100 percent. The constructor has two options. One is default which uses dot(.) as delimiter and other constructor takes the delimiter as one of the parameterms. This will enable user's to change the delimiter.

An example of JSON flattening is shown below:

Input
```json
{
    "a": 1,
    "b": true,
    "c": {
        "d": 3,
        "e": "test"
    }
}
```

Output
```json
{
    "a": 1,
    "b": true,
    "c.d": 3,
    "c.e": "test"
}
```

**Note**: The application does not tackle arrays. It merely copies the if there are any arrays in JSON input.

## Prerequisites
Softwares required to run the application:
1. Maven. (https://maven.apache.org/download.cgi)
2. Java 11.

## Steps to run the code.
1. Download the code into the local machine.
2. Extract the zip file.
3. Go the folder extracted folder from command prompt.(Note: your folder should have pom.xml file)

4. run the below maven commands.

**mvn package**
  
The output looks as below: 
 ![Alt text](https://github.com/aajey/jsonflatten/blob/main/mvnpackage.PNG)
 
 
 5. Running flattening JSON.
 
 **java -cp target\jsonflatten-1.0.0.jar com.jsonflatten.App**


   The application covers multiple ways of inputting data. 
   1. String.
   2. Json file.
The sceenshot below shows how the output looks like:
![Alt text](https://github.com/aajey/jsonflatten/blob/main/running%20app.PNG)

 
 
 
 

 
