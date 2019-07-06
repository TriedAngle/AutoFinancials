# AutoFinancials [![Build Status](https://travis-ci.com/TriedAngle/AutoFinancials.svg?branch=master)](https://travis-ci.com/TriedAngle/AutoFinancials)  [![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

## Table of Contents

- Installation
    - Prerequisites
        - Java
            - Windows
            - OSX
            - Ubuntu
            - Arch
            
        - Libraries
            - Adding Libraries (IntelliJ & Eclipse)
            - List
    - Configure Database
    - Configure Application
- Usage
    - coming soon
- Contributing

## Installation

### Prerequisites

#### Java

The project and some of its libraries use openjdk11, although I think it would work with jdk 8, 9 and 10 it is not tested nor recommended to do so.

##### Windows:
coming soon

##### Max OSX:
coming soon

##### Ubuntu:
```bash
$ sudo apt update && apt-get upgrade
$ sudo apt install software-properties-common
$ sudo add-apt-repository ppa:linuxuprising/java
$ sudo apt install oracle-java11-installer
```

##### Arch:
```bash
$ sudo pacman -S jre-11-openjdk
$ sudo archlinux-java status

Available Java environments:
    java-7-openjdk (default)
    java-8-openjdk/jre
    java-11-openjdk/jre
    
$ archlinux-java-set java-11-openjdk/jre
```

#### Libraries:
Adding libraries to your project \
the parts of each library that have to be added, can be found on their documentary

##### Adding Libraries

IntelliJ-IDEA:
```
open project -> File -> Project Structure -> Project Structure -> Modules -> +
```

Eclipse:
Coming soon

Libraries: (Link + Versions will follow soon)
- JavaFX-11
- OpenCSV
- PostgreSQL
- org.json

### Configure Database

coming soon

### Configure Application

1. Rename *UserData.json.sample* to *UserData.json* under "AutoFinancials/src/net/strobl/data/json" and add your 
   postgres server ip address and your login credentials. The following is an example on what it should look like.
    ```JSON
    {
       "username": "Sebastian",
       "password": "vErysEcUrePasSwORD!1!",
       "url": "jdbc:postgresql://strobl.net:5432/NotAValidDataBase"
    } 
    ```
2. launch application
3. Some other setup options will be coming soon

## Usage
A guide will be coming soon

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
Please make sure to update tests as appropriate.
A small project which automates the financial work for my schools organisation team
