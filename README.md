# sales-report-poc
This poc is writed with ***Java*** to monitoring a folder with a file with specificed layout to process and generate a output file with some data from source file.

### Usage
* To execute this project is necessary create some folders in your %HOMEPATH%, in Linux can be in /home/username and in Windows in /users/username/
* The Folder that need be created is the folder ***data*** and inside this folder create other two folders with respective names ***in*** and ***out***
* To execute project can be created a file with any name of your choice, but the content need repect the current layout:
  ```001ç1234567891234çPedroç50000
  001ç3245678865434çPauloç40000.99
  002ç2345675434544345çJose da SilvaçRural
  002ç2345675433444345çEduardo PereiraçRural
  003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
  003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo
* The output file will contains some resume informations about source file
