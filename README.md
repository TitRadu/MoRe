# MoRe
MoRe is an open source project, a tool that rename and sort files by extension.

## Technologies
I use Kotlin as programming language to build this tool.

## Installing

### From Release
To install MoRe from Github, download meco.zip from the [latest_release](https://github.com/TitRadu/MoRe/releases/tag/v1.0)

The contains from the archive are:

`MoRe.jar` - the executable jar file

Or you can download source code from release.

### From Code
Clone the repository or download code from [here](https://github.com/TitRadu/MoRe)

Run `mvn clean install` in "/MoRe" to obtain the executable jar file. If you are in Windows, run this command from the IDE in which you use Maven. 

## Installing auxiliars.
You must have installed jdk.

### For Windows

#### jdk 11  on Windows
[jdk11](https://www.oracle.com/ro/java/technologies/javase-jdk11-downloads.html)

### For Linux
 
#### jdk 11  on Ubuntu 
 ```bash
apt update
apt upgrade
apt install openjdk-11-jdk
 ```
 
## Running the tool
You should have jar of application MoRe.jar which was created at Installing step.

Now we can running the tool using the following commands:
 ```bash
java -jar MoRe.jar AbsolutePathOfDirectoryWhichYouWantToProcess
 ```
or
 ```bash
java -jar MoRe.jar AbsolutePathOfDirectoryWhichYouWantToProcess -option
 ```

In the case of the first command, the directory gives as parameter will suffer two changes: rename and sort files by extension.

In the case of the second command, the directory gives as parameter will suffer as follows:
1. if value of second parameter is "-r", files will be renamed by extension;
2. if value of second parameter is "-m", files will be sorted by extension;

## Results
In order to understand what the tool does, we must define what the renaming or sorting of files represents.
Renaming is based on extension, starting with "file1.extension" to "filen.extension" where n is
the number of files with the extension "extension".
Also, sorting is based on extension. For each extension, a directory is created with its name and files
corresponding to the extension will be moved to the corresponding directory.

## Contribute
I am close to collaborations.

## Contact
Contact me for additional help:
radutit@yahoo.com

## License 
[MIT](https://choosealicense.com/licenses/mit/)