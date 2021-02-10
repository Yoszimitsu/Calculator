# Calculator Task

## Task Description 

Instructions comprise of a keyword and a number that are separated by a space per
line. Instructions are loaded from file and results are output to the screen. Any number
of Instructions can be specified. Instructions can be any binary operators of your choice
(e.g., add, divide, subtract, multiply etc). The instructions will ignore mathematical
precedence. The last instruction should be “apply” and a number (e.g., “apply 3”). The
calculator is then initialised with that number and the previous instructions are applied
to that number.

##### Examples of the calculator lifecycle might be:

##### Example 1
Input from file  
add 2  
multiply 3  
apply 10  
Output 36  
Explanation 10 + 2 * 3 = 36
##### Example 2
Input from file  
multiply 3  
add 2  
apply 10  
Output 32  
Explanation 10 * 3 + 2 = 32  
##### Example 3
Input from file apply 1  
Output 1  


## Implementation 

The CalculationService class contains main implementation. The calculate method takes 2 parameters: **filePath** and **withPrcedence** (which defines if a calculation will be executed with math operations precedence or not).  
For example:  
- withPrecedence    
`2 + 2 * 2 = 6`  
- withoutPrecedence   
`2 + 2 * 2 = 8`  

The Calculator service allows use 5 InstructionTypes (math operations):  
- Add  
- Subtract  
- Divide  
- Multiply  
- Apply  

The last InstructionType ("Apply") finishes an equal. 
As a result of calculation is returned Dobule type, rounded to 4 places. 

##### Error handling  
CalculatorService may returns 3 custom exceptions: 
- AnalyzeInstructionException - appear if an error is thrown in analyze instruction line.
- UnknownInstructionException - appear if any InstructionType is founded in instruction line.
- EmptyFileException - appear if file is empty. 

##### Technology stack:  
`
java,   
maven, 
junit4,  
checkstyle,  
pmd, 
`



